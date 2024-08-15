package org.elibrary.application.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.elibrary.application.Service.BookService;
import org.elibrary.application.dto.AddBookRequest;
import org.elibrary.application.enums.BookType;
import org.elibrary.application.exceptions.BookException;
import org.elibrary.application.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/book")
public class  bookController {

    @Autowired
    BookService bookService;

    @PostMapping("/add")
    public ResponseEntity<?> addBook(@Valid @RequestBody  AddBookRequest addBookRequest){
        Book saveBook=bookService.addbook(addBookRequest);
        return new ResponseEntity<>(saveBook, HttpStatus.CREATED);
    }
    @GetMapping("/all")
    public ResponseEntity<List<Book>> getBook(@RequestParam(value="title",required = false) String booktitle,
                                              @RequestParam(value="type",required = false) BookType bookType){
        List<Book> books=bookService.getBooks(booktitle,bookType);
        return new ResponseEntity<>(books,HttpStatus.CREATED);
    }
}
