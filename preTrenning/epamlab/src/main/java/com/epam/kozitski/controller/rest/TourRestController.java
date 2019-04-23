package com.epam.kozitski.controller.rest;

import com.epam.kozitski.domain.Tour;
import com.epam.kozitski.domain.util.Tours;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tour/")
public class TourRestController {

    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Tour> getTour(@PathVariable("id") Long id){
        Tour tour = Tours.createDefaultTour();

        return new ResponseEntity<>(tour, HttpStatus.OK);
    }

}
