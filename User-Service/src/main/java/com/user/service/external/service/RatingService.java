package com.user.service.external.service;/*
    @author jadon
*/

import com.user.service.entities.Rating;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient(name = "RATING-SERVICE")
public interface RatingService {

    @PostMapping("/rating/add")
    Rating createRating();


    @PutMapping("/rating/update")
    Rating updateRating();
}
