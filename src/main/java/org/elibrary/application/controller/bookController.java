package org.elibrary.application.controller;

import jakarta.validation.Valid;
import org.elibrary.application.Service.BookService;
import org.elibrary.application.dto.AddBookRequest;
import org.elibrary.application.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/book")
public class  bookController {

    @Autowired
    BookService bookService;

    @PostMapping("/add")
    public ResponseEntity<Book> addBook(@Valid @RequestBody  AddBookRequest addBookRequest){
        Book saveBook=bookService.addbook(addBookRequest);
        return new ResponseEntity<>(saveBook, HttpStatus.CREATED);
    }
}
