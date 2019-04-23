package com.epam.kozitski.controller.rest;

import com.epam.kozitski.domain.User;
import com.epam.kozitski.domain.util.Users;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user/")
public class UserRestController {

    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<User> getUser(@PathVariable("id") Long id){
        User user = Users.createDefaultUser();

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

}
