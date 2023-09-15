package com.user.service.controller;/*
    @author jadon
*/

import com.user.service.entities.User;
import com.user.service.services.UserService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    Logger logger = LoggerFactory.getLogger(getClass());

    @PostMapping("/add")
    public ResponseEntity<User> createUser(@RequestBody User user){
        User user1 = this.userService.createUser(user);
        return new ResponseEntity<>(user1, HttpStatus.CREATED);
    }

    int retryCount = 0;

    @GetMapping("/get/{userId}")
//    @CircuitBreaker(name = "ratingHotelBreaker", fallbackMethod = "ratingHotelFallback")
    @Retry(name = "hotelRatingRetry", fallbackMethod = "ratingHotelFallback")
    public ResponseEntity<User> getUser(@PathVariable String userId){
        logger.info("Retrying times : {}", retryCount++);
        User user = this.userService.getUser(userId);
        return ResponseEntity.ok(user);
    }

    //fallback method
    public ResponseEntity<User> ratingHotelFallback(String userId, Exception e){
        logger.info("Fallback method executed, some service id down : ", e.getMessage());
        User user = User.builder().userId("123445")
                .about("This is the dummy user")
                .email("dummy@gmail.com")
                .name("Dummy")
                .build();
        return new ResponseEntity<>(user, HttpStatus.OK);
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
