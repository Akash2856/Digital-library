package org.elibrary.application.Service;

import jakarta.validation.constraints.Null;
import org.elibrary.application.dto.TransactionRequest;
import org.elibrary.application.enums.UserStatus;
import org.elibrary.application.enums.UserType;
import org.elibrary.application.exceptions.TransactionException;
import org.elibrary.application.model.Book;
import org.elibrary.application.model.Transaction;
import org.elibrary.application.model.User;
import org.elibrary.application.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TransactionService {
    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    UserService userService;

    @Autowired
    BookService bookService;
    public Transaction issueBook(TransactionRequest transactionRequest){

        //check user
        User user = fetchUser(transactionRequest);
        Book book=fetchBook(transactionRequest);
        Transaction transaction= Transaction.builder()
                .book(book)
                .user(user)
                .transactionId(UUID.randomUUID().toString().substring(0,10))
                .settlementAmount(-book.getSecurityAmount())
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
        if(user.getUserStatus()== UserStatus.BLOCKED){
            throw new TransactionException("user is blocked");
        }
        return user;
    }
    private Book fetchBook(TransactionRequest transactionRequest){
        Book book=bookService.getBookByBookNo(transactionRequest.getBookNo());
        if(book==null){
            throw new TransactionException("Book does not exist in the liberary");
        }
        if(book.getUser()!= null){
            throw new TransactionException("book already issued");
        }
        return book;
    }
}
