package com.rating.service.implementation;/*
    @author jadon
*/

import com.rating.service.entitie.Rating;
import com.rating.service.repositary.RatingRepositary;
import com.rating.service.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RatingServiceImplementation implements RatingService {

    @Autowired
    private RatingRepositary ratingRepositary;

    @Override
    public Rating createRating(Rating rating) {
        rating.setRatingId(UUID.randomUUID().toString());
        return this.ratingRepositary.save(rating);
    }

    @Override
    public List<Rating> getAllRating() {
        return this.ratingRepositary.findAll();
    }

    @Override
    public List<Rating> getRatingByUserId(String userId) {
        return this.ratingRepositary.findByUserId(userId);
    }

    @Override
    public List<Rating> getRatingByHotelId(String hotelId) {
        return this.ratingRepositary.findByHotelId(hotelId);
    }
}
