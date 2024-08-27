package org.elibrary.application.controller;

import jakarta.validation.Valid;
import org.elibrary.application.Service.UserService;
import org.elibrary.application.dto.AddUserRequest;
import org.elibrary.application.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class userController {

    @Autowired
    UserService userService;

    @PostMapping("/student")
    public ResponseEntity<?> addStudent(@RequestBody @Valid AddUserRequest addUserRequest){
    User addUser= userService.addStudent(addUserRequest);
    return new ResponseEntity<>(addUser, HttpStatus.OK);
    }

    @PostMapping("/admin")
    public ResponseEntity<User> addAdmin(@RequestBody @Valid AddUserRequest addUserRequest){
        User addUser= userService.addAdmin(addUserRequest);
        return new ResponseEntity<>(addUser, HttpStatus.OK);
    }
}
