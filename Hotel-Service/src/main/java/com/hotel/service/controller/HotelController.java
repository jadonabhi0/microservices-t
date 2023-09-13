package com.hotel.service.controller;/*
    @author jadon
*/

import com.hotel.service.entities.Hotel;
import com.hotel.service.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotels")
public class HotelController {

    @Autowired
    private HotelService hotelService;

    @GetMapping("/get/{hotelId}")
    public ResponseEntity<Hotel> getHotel(@PathVariable String hotelId){
        Hotel hotel = this.hotelService.getHotel(hotelId);
        return ResponseEntity.ok(hotel);
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<Hotel>> getAllHotel(){
        List<Hotel> allHotels = this.hotelService.getAllHotels();
        return ResponseEntity.ok(allHotels);
    }

    @PostMapping("/add")
    public ResponseEntity<Hotel> createHotel(@RequestBody Hotel hotel){
        Hotel hotel1 = this.hotelService.createHotel(hotel);
        return new ResponseEntity<>(hotel1, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Hotel> updateHotel(@RequestBody Hotel hotel){
        Hotel hotel1 = this.hotelService.updateHotel(hotel);
        return ResponseEntity.ok(hotel1);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteHotel(@RequestBody Hotel hotel){
        this.hotelService.deleteHotel(hotel);
        return ResponseEntity.ok("Hotel deleted successfully !!");
    }

}
