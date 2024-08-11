package org.elibrary.application.controller;


import jakarta.validation.Valid;
import org.elibrary.application.Service.TransactionService;
import org.elibrary.application.dto.TransactionRequest;
import org.elibrary.application.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @PostMapping
    public ResponseEntity<Transaction> issueBook(@RequestBody @Valid TransactionRequest transactionRequest){
        Transaction transaction = transactionService.issueBook(transactionRequest);
        return new ResponseEntity<>(transaction, HttpStatus.OK);
    }
}
