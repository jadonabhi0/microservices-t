package com.rating.service.controller;/*
    @author jadon
*/

import com.rating.service.entitie.Rating;
import com.rating.service.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rating")
public class RatingController {

    @Autowired
    private RatingService ratingService;

    @PostMapping("/add")
    public ResponseEntity<Rating> create(@RequestBody Rating rating){
        Rating rating1 = this.ratingService.createRating(rating);
        return ResponseEntity.status(HttpStatus.CREATED).body(rating1);
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<Rating>> getRatting(){
        List<Rating> allRating = this.ratingService.getAllRating();
        return  ResponseEntity.ok(allRating);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Rating>> getRattingByUserId(@PathVariable  String userId){
        List<Rating> ratingByUserId = this.ratingService.getRatingByUserId(userId);
        return ResponseEntity.ok(ratingByUserId);
    }

    @GetMapping("/hotel/{hotelId}")
    public ResponseEntity<List<Rating>> getRattingByHotelId(@PathVariable  String hotelId){
        List<Rating> ratingByHotelId = this.ratingService.getRatingByHotelId(hotelId);
        return ResponseEntity.ok(ratingByHotelId);
    }

}
