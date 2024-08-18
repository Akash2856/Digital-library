package org.elibrary.application.service;

import org.aspectj.util.Reflection;
import org.elibrary.application.Service.BookService;
import org.elibrary.application.Service.TransactionService;
import org.elibrary.application.Service.UserService;
import org.elibrary.application.dto.TransactionRequest;
import org.elibrary.application.enums.UserType;
import org.elibrary.application.exceptions.TransactionException;
import org.elibrary.application.model.Transaction;
import org.elibrary.application.model.User;
import org.elibrary.application.repository.TransactionRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

 @ExtendWith(MockitoExtension.class)
public class TransactionServiceTest {

    @Mock
    UserService userService;

    @Mock
    BookService bookService;

    @Mock
    TransactionRepository transactionRepository;

    @InjectMocks
    TransactionService transactionService;
    @BeforeEach
    public void setup(){
        System.out.println("before Each");
//        transactionService=new TransactionService();
        ReflectionTestUtils.setField(transactionService,"validDays",14);
        ReflectionTestUtils.setField(transactionService,"finePerDays",1);
    }

    @BeforeAll
    public static void abc(){
        System.out.println("before all");
    }
    @Test
    public void calculateFine_WithinValidDays_ReturnsCorrectAmount(){

        Transaction transaction= Transaction.builder().createdOn(new Date()).settlementAmount(-300).build();
        int amount= transactionService.calculateFine(transaction);
        Assertions.assertEquals(-300,amount);
//        Assertions.assertNull(amount);
//        Assertions.assertThrows(TransactionException.class,()->transactionService.calculateFine(transaction));
    }

    @Test
    public  void calculateFine_InvalidDays_ReturnCorrectAmount() throws ParseException {

        Date date= new SimpleDateFormat("yyyy-mm-dd").parse("2024-01-01");
        Transaction transaction= Transaction.builder().createdOn(date).settlementAmount(-300).build();
        int amount= transactionService.calculateFine(transaction);
        Assertions.assertEquals(-86,amount);
    }

    @Test
    public void fetchUser_InvalidStudent_ThrowsException() throws TransactionException{
        User user=User.builder().id(123).userType(UserType.ADMIN).build();
        Mockito.when(userService.fetchUserByEmail("abc@gmail.com")).thenReturn(user);
        TransactionRequest request=TransactionRequest.builder().userEmail("abc@gmail.com").build();
        //transactionService.fetchUser(request);
        Assertions.assertThrows(TransactionException.class,()->transactionService.fetchUser(request));
    }
     @Test
     public void fetchUser_validStudent_ReturnCorrectStudent() throws TransactionException {
         User user = User.builder().id(123).userType(UserType.STUDENT).build();
         Mockito.when(userService.fetchUserByEmail("abc@gmail.com")).thenReturn(user);
         TransactionRequest request = TransactionRequest.builder().userEmail("abc@gmail.com").build();//transaction request use userEmail and BookNo
         // but we use only UserEmail b/c fetchuser only user userEmail
         User returnedUser= transactionService.fetchUser(request);
         Assertions.assertEquals(user,returnedUser);

         //Assertions.assertThrows(TransactionException.class, () -> transactionService.fetchUser(request));
     }
}
