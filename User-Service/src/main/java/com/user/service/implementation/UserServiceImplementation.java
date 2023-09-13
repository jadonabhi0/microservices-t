package com.user.service.implementation;/*
    @author jadon
*/

import com.user.service.entities.User;
import com.user.service.exceptions.ResourceNotFoundException;
import com.user.service.repositary.UserRepositary;
import com.user.service.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


public class UserServiceImplementation implements UserService {

    @Autowired
    private UserRepositary userRepositary;

    @Override
    public User createUser(User user) {
        User savedUser = this.userRepositary.save(user);
        return savedUser;
    }

    @Override
    public List<User> getAllUser() {
        return this.userRepositary.findAll();
    }

    @Override
    public User getUser(String userId) {
        return this.userRepositary.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User not found whit this userId : "+ userId));

    }

    @Override
    public void deleteUser(String userId) {
        User user = this.userRepositary.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found whit this userId : " + userId));
        this.userRepositary.delete(user);
    }

    @Override
    public User updateUser(User user) {
        User user1 = this.userRepositary.findById(user.getUserId()).orElseThrow(()-> new ResourceNotFoundException("User not found whit this userId : "+ user.getUserId()));
        user1.setAbout(user.getAbout());
        user1.setEmail(user.getEmail());
        user1.setName(user.getName());
        User savedUser1 = this.userRepositary.save(user1);
        return savedUser1;
    }
}
