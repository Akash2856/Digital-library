package org.elibrary.application.controller;

import lombok.extern.slf4j.Slf4j;
import org.elibrary.application.exceptions.BookException;
import org.elibrary.application.exceptions.TransactionException;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class ControllerException {
    @ExceptionHandler(value= TransactionException.class)//only if exception is TransactionException
    public ResponseEntity<String> takeAction(TransactionException transactionException){
        log.error("transactionException occureded: {}", transactionException);
        return new ResponseEntity<>(transactionException.getMessage(), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(value= BookException.class)//only if exception is BookException
    public ResponseEntity<String> takeAction(BookException BookException){
        log.error("transactionException occureded: {}", BookException);
        return new ResponseEntity<>(BookException.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
