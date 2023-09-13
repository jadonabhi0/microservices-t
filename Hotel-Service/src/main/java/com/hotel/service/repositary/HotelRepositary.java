package com.hotel.service.repositary;/*
    @author jadon
*/

import com.hotel.service.entities.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelRepositary extends JpaRepository<Hotel, String> {
}
