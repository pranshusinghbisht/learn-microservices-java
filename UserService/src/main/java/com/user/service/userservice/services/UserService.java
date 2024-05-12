package com.user.service.userservice.services;

import com.user.service.userservice.entities.User;

import java.util.List;

public interface UserService {

    // create user
    User saveUser(User user);


    // get all User
    List<User> getAllUser();


    // get user by id
    User getUserById(String id);

    // etc
}
