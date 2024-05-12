package com.user.service.userservice.controllers;

import com.user.service.userservice.entities.User;
import com.user.service.userservice.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;


    // create
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
    User user1 = userService.saveUser(user);
    return ResponseEntity.status(HttpStatus.CREATED).body(user1);
    }

    // get user by id
    @GetMapping("/{userId}")
    public ResponseEntity<User> getSingleUser(@PathVariable String userId) {
        User user1 = userService.getUserById(userId);
        return ResponseEntity.ok(user1);
    }

    // all user list
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> allUser = userService.getAllUser();
        return ResponseEntity.ok(allUser);
    }
}
