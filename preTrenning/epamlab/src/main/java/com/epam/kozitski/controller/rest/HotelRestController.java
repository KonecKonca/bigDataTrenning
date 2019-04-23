package com.epam.kozitski.controller.rest;

import com.epam.kozitski.domain.Hotel;
import com.epam.kozitski.domain.util.Hotels;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hotel/")
public class HotelRestController {

    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Hotel> getHotel(@PathVariable("id") Long id){
        Hotel hotel = Hotels.createDefaultHotel();

        return new ResponseEntity<>(hotel, HttpStatus.OK);
    }

}
