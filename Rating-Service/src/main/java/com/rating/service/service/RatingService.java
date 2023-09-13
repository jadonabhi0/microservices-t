package com.rating.service.service;/*
    @author jadon
*/

import com.rating.service.entitie.Rating;

import java.util.List;

public interface RatingService {

    // create rating
    Rating createRating(Rating rating);

    //get all ratings
    List<Rating> getAllRating();

    // get ratings by userId
    List<Rating> getRatingByUserId(String userId);

    // get ratings by hotelId
    List<Rating> getRatingByHotelId(String hotelId);

}
