package org.elibrary.application.service;

import org.elibrary.application.Service.TransactionService;
import org.elibrary.application.exceptions.TransactionException;
import org.elibrary.application.model.Transaction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Date;

public class TransactionServiceTest {

    @Test
    public void calculateFine_WithinValidDays_ReturnsCorrectAmount(){
        TransactionService transactionService=new TransactionService();
        Transaction transaction= Transaction.builder().createdOn(new Date()).settlementAmount(-300).build();
        int amount= transactionService.calculateFine(transaction);
        Assertions.assertEquals(-400,amount);
//        Assertions.assertNull(amount);
//        Assertions.assertThrows(TransactionException.class,()->transactionService.calculateFine(transaction));
    }
}
