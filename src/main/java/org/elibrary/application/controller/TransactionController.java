package org.elibrary.application.controller;


import jakarta.validation.Valid;
import org.elibrary.application.Service.TransactionService;
import org.elibrary.application.dto.TransactionRequest;
import org.elibrary.application.exceptions.TransactionException;
import org.elibrary.application.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @PostMapping("/issue")
    public ResponseEntity<?> issueBook(@RequestBody @Valid TransactionRequest transactionRequest){
//        Transaction transaction=null;
//        try {
//            transaction = transactionService.issueBook(transactionRequest);
//        }
//        catch (TransactionException transactionException){
//            return new ResponseEntity<>(transactionException.getMessage(), HttpStatus.BAD_REQUEST);
//        }
//        return new ResponseEntity<>(transaction, HttpStatus.OK);
        Transaction transaction = transactionService.issueBook(transactionRequest);

        return new ResponseEntity<>(transaction, HttpStatus.OK);
    }
    @PutMapping("/return")
    public ResponseEntity<Integer> returnBook(@RequestBody @Valid TransactionRequest transactionRequest){
        Integer settlementAmount= transactionService.returnBook(transactionRequest);
        return new ResponseEntity<>(settlementAmount, HttpStatus.OK);
    }


}
