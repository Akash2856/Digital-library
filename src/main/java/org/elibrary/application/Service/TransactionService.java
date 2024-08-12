package org.elibrary.application.Service;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Null;
import org.elibrary.application.dto.TransactionRequest;
import org.elibrary.application.enums.TransactionStatus;
import org.elibrary.application.enums.UserStatus;
import org.elibrary.application.enums.UserType;
import org.elibrary.application.exceptions.TransactionException;
import org.elibrary.application.model.Book;
import org.elibrary.application.model.Transaction;
import org.elibrary.application.model.User;
import org.elibrary.application.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class TransactionService {
    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    UserService userService;

    @Autowired
    BookService bookService;

    @Value("${book.maximum.validity}")
    int validDays;

    @Value("${book.fine.per.day}")
    int finePerDays;
    public Transaction issueBook(TransactionRequest transactionRequest){

        //check user
        User user = fetchUser(transactionRequest);
        if(user.getUserStatus()== UserStatus.BLOCKED){
            throw new TransactionException("user is blocked");
        }
        Book book=fetchBook(transactionRequest);
        if(book.getUser()!= null){
            throw new TransactionException("book already issued");
        }
        return issueBook(user,book);
    }


    @Transactional
    private Transaction issueBook(User user,Book book){
        Transaction transaction= Transaction.builder()
                .book(book)
                .user(user)
                .transactionId(UUID.randomUUID().toString().substring(0,10))
                .settlementAmount(-book.getSecurityAmount())
                .transactionStatus(TransactionStatus.ISSUED)
                .build();
        transaction=transactionRepository.save(transaction);
        book.setUser(user);
        bookService.updateBookMetaData(book);
        return transaction;
    }
    private User fetchUser(TransactionRequest transactionRequest){
        User user=userService.fetchUserByEmail(transactionRequest.getUserEmail());

        if(user==null){
            throw new TransactionException("User does not exist in the liberary");
        }
        if(user.getUserType()!= UserType.STUDENT){
            throw new TransactionException("User is not of type student");
        }

        return user;
    }
    private Book fetchBook(TransactionRequest transactionRequest){
        Book book=bookService.getBookByBookNo(transactionRequest.getBookNo());
        if(book==null){
            throw new TransactionException("Book does not exist in the liberary");
        }

        return book;
    }
    public Integer returnBook(TransactionRequest transactionRequest){
        User user= fetchUser(transactionRequest);
        Book book=fetchBook(transactionRequest);
        if(book.getUser()!= user){
            throw new TransactionException("book is issued to other user");
        }
        Transaction transaction= transactionRepository.findByUserEmailAndBookBookNo(transactionRequest.getUserEmail(),
                transactionRequest.getBookNo());
        return returnBook(transaction,book);
    }

    @Transactional
    private Integer returnBook(Transaction transaction,Book book){
        long issueDateInTime= transaction.getCreatedOn().getTime();
        long currentTime= System.currentTimeMillis();

        long timeDifference=currentTime-issueDateInTime;
        long days=TimeUnit.MILLISECONDS.toDays(timeDifference);
        int amount;
        if(days>validDays){
            int fine=(int)(days-validDays)*finePerDays;
//            if(fine>Math.abs(transaction.getSettlementAmount())){
//                    amount=fine-Math.abs(transaction.getSettlementAmount());
//                    //int finalValue=Math.abs(transaction.getSettlementAmount())+amount;
//                    transaction.setSettlementAmount(-fine);
//            }
//            else{
//                amount=fine-Math.abs(transaction.getSettlementAmount());
//                transaction.setSettlementAmount(-fine);
//            }
            amount=fine-Math.abs(transaction.getSettlementAmount());
            transaction.setSettlementAmount(-fine);
            transaction.setTransactionStatus(TransactionStatus.FINED);
        }
        else{
            transaction.setTransactionStatus(TransactionStatus.RETURNED);
            amount=transaction.getSettlementAmount();
            transaction.setSettlementAmount(0);
        }
        transactionRepository.save(transaction);
        book.setUser(null);
        bookService.updateBookMetaData(book);
        return amount;
    }
}
