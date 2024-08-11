package org.elibrary.application.controller;

import org.elibrary.application.Service.AuthorService;
import org.elibrary.application.model.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/author")
public class authorController {

    @Autowired
    AuthorService authorService;

    @GetMapping
    public ResponseEntity<Author> getAuthor(@RequestParam("email") String email){
        Author author=authorService.getAuthorByEmail(email);
        return new ResponseEntity<>(author, HttpStatus.OK);
    }
}
