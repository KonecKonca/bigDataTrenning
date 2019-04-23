package com.epam.kozitski.controller;

import com.epam.kozitski.dao.impl.CountryJdbcTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class TestController {

    @Autowired
    private CountryJdbcTemplate countryJdbcTemplate;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String test(){

        countryJdbcTemplate.searchById(2);

        return "index";
    }

    @RequestMapping(value = "/xxx", method = RequestMethod.GET)
    public String test1(){
        return "countries";
    }

}
