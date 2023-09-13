package com.user.service.controller;/*
    @author jadon
*/

import com.user.service.entities.User;
import com.user.service.services.UserService;
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

    @PostMapping("/add")
    public ResponseEntity<User> createUser(@RequestBody User user){
        User user1 = this.userService.createUser(user);
        return new ResponseEntity<>(user1, HttpStatus.CREATED);
    }

    @GetMapping("/get/{userId}")
    public ResponseEntity<User> getUser(@PathVariable String userId){
        User user = this.userService.getUser(userId);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<User>> getAllUser(){
        List<User> allUser = this.userService.getAllUser();
        return ResponseEntity.ok(allUser);
    }

    @PutMapping("/update")
    public ResponseEntity<User> updateUser(@RequestBody User user){
        User user1 = this.userService.updateUser(user);
        return ResponseEntity.ok(user1);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> delete(@RequestBody User user){
        this.userService.deleteUser(user);
        return ResponseEntity.ok("User deleted Successfully !!");
    }

}
