package com.user.service.services;/*
    @author jadon
*/

import com.user.service.entities.User;

import java.util.List;

public interface UserService {

    // create a new user
    User createUser(User user);

    // get All user
    List<User> getAllUser();

    // get user by id
    User getUser(String userId);

    // delete user
    void deleteUser(User user);

    // update User
    User updateUser(User user);

}
