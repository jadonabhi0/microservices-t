package com.user.service.implementation;/*
    @author jadon
*/

import com.user.service.entities.Hotel;
import com.user.service.entities.Rating;
import com.user.service.entities.User;
import com.user.service.exceptions.ResourceNotFoundException;
import com.user.service.external.service.HotelService;
import com.user.service.repositary.UserRepositary;
import com.user.service.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.logging.Handler;
import java.util.stream.Collectors;


@Service
public class UserServiceImplementation implements UserService {

    Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private UserRepositary userRepositary;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private HotelService hotelService;

    @Override
    public User createUser(User user) {
        user.setUserId(UUID.randomUUID().toString());
        User savedUser = this.userRepositary.save(user);
        return savedUser;
    }

    @Override
    public List<User> getAllUser() {
        return this.userRepositary.findAll();
    }

    @Override
    public User getUser(String userId) {
        User user = this.userRepositary.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found whit this userId : " + userId));
        String url = "http://RATING-SERVICE/rating/user/"+userId;
        Rating[] ratingArray = restTemplate.getForObject(url, Rating[].class);
        List<Rating> ratingList = Arrays.stream(ratingArray).toList();
        List<Rating> collect = ratingList.stream().map(rating -> {

            Hotel hotel = hotelService.getHotel(rating.getHotelId());

            rating.setHotel(hotel);
            return rating;
        }).collect(Collectors.toList());
        user.setRating(collect);
        return user;

    }

    @Override
    public void deleteUser(User user) {
        User user1 = this.userRepositary.findById(user.getUserId()).orElseThrow(() -> new ResourceNotFoundException("User not found whit this userId : " + user.getUserId()));
        this.userRepositary.delete(user1);
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
