package com.hotel.service.implementation;/*
    @author jadon
*/

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hotel.service.entities.Hotel;
import com.hotel.service.exception.ResourceNotFoundException;
import com.hotel.service.repositary.HotelRepositary;
import com.hotel.service.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class HotelServiceImplementation implements HotelService{

    @Autowired
    private HotelRepositary hotelRepositary;

    @Override
    public Hotel getHotel(String hotelId) {
       return this.hotelRepositary.findById(hotelId).orElseThrow(()->new ResourceNotFoundException("Hotel not exist on server with hotel id : "+hotelId));
    }

    @Override
    public List<Hotel> getAllHotels() {
        return this.hotelRepositary.findAll();
    }

    @Override
    public Hotel createHotel(Hotel hotel) {
        hotel.setId(UUID.randomUUID().toString());
        return this.hotelRepositary.save(hotel);
    }

    @Override
    public Hotel updateHotel(Hotel hotel) {
        Hotel hotel1 = this.hotelRepositary.findById(hotel.getId()).orElseThrow(() -> new ResourceNotFoundException("Hotel not exists on server ith hotel id : " + hotel.getId()));
        hotel1.setAbout(hotel.getAbout());
        hotel1.setLocation(hotel.getLocation());
        hotel1.setName(hotel.getName());
        Hotel save = this.hotelRepositary.save(hotel1);
        return save;
    }

    @Override
    public void deleteHotel(Hotel hotel) {
        Hotel hotel1 = this.hotelRepositary.findById(hotel.getId()).orElseThrow(() -> new ResourceNotFoundException("Hotel not exists on server ith hotel id : " + hotel.getId()));
        this.hotelRepositary.delete(hotel1);
    }
}
