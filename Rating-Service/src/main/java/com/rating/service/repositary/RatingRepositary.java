package com.rating.service.repositary;/*
    @author jadon
*/

import ch.qos.logback.classic.pattern.LineSeparatorConverter;
import com.rating.service.entitie.Rating;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface RatingRepositary extends MongoRepository<Rating, String> {

    // find all ratings by userId
    List<Rating> findByUserId(String userId);

    // find all ratings by hotelId
    List<Rating> findByHotelId(String hotelId);

}
