package com.hotel.service.service;/*
    @author jadon
*/

import com.hotel.service.entities.Hotel;

import java.util.List;

public interface HotelService {

    // get hotel
    Hotel getHotel(String hotelId);

    // get all hotels
    List<Hotel> getAllHotels();

    // create Hotel
    Hotel createHotel(Hotel hotel);

    //update hotel
    Hotel updateHotel(Hotel hotel);

    //delete hotel
    void deleteHotel(Hotel hotel);


}
