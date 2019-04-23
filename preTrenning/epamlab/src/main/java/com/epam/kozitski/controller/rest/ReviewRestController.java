package com.epam.kozitski.controller.rest;

import com.epam.kozitski.domain.Review;
import com.epam.kozitski.domain.util.Reviews;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/review/")
public class ReviewRestController {

    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Review> getReview(@PathVariable("id") Long id){
        Review review = Reviews.createDefaultReview();
        
        return new ResponseEntity<>(review, HttpStatus.OK);
    }

}
