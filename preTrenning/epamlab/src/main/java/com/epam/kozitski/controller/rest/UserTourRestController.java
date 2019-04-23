package com.epam.kozitski.controller.rest;

import com.epam.kozitski.domain.UserTour;
import com.epam.kozitski.domain.util.UserTours;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/userTour/")
public class UserTourRestController {

    @GetMapping(value = "{userId}/{tourId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<UserTour> getUser(
            @PathVariable("userId") Long userId,
            @PathVariable("tourId") Long tourId
    ){
        UserTour userTour = UserTours.createDefaultUserTour();

        return new ResponseEntity<>(userTour, HttpStatus.OK);
    }

}
