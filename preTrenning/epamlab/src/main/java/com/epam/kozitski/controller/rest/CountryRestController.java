package com.epam.kozitski.controller.rest;

import com.epam.kozitski.domain.Country;
import com.epam.kozitski.domain.util.Countries;
import com.epam.kozitski.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/country/")
public class CountryRestController {

    private CountryService countryService;
    @Autowired
    public void setCountryService(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Country> getCountry(@PathVariable("id") Long id){
        Optional<Country> optionalCountry = countryService.searchById(id);

        ResponseEntity<Country> responseEntity;

        if (optionalCountry.isPresent()){
            responseEntity = new ResponseEntity<>(optionalCountry.get(), HttpStatus.ACCEPTED);
        }
        else {
            responseEntity = new ResponseEntity<>(optionalCountry.get(), HttpStatus.NO_CONTENT);
        }

        return responseEntity;
    }

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<Country>> getAllCountries(){
        HttpHeaders headers = new HttpHeaders();

        List<Country> countries = countryService.searchAll();

        return new ResponseEntity<>(countries, headers, HttpStatus.ACCEPTED);
    }

    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ResponseEntity<Country> insertCountry(@RequestParam("name") String name){
        Country country = new Country(Countries.DEFAULT_COUNTRY_ID, name);

        if(name != null && !name.isEmpty()){
            countryService.insert(country);
        }

        return new ResponseEntity<>(country, HttpStatus.ACCEPTED);
    }

    @PutMapping(value = "", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ResponseEntity<Country> updateCountry(
            @RequestParam("id") Long id,
            @RequestParam("name") String name
    ){
        Country country = new Country(id, name);

        if(name != null && !name.isEmpty()){
            countryService.update(country);
        }

        return new ResponseEntity<>(country, HttpStatus.ACCEPTED);
    }

    @DeleteMapping(value = "", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ResponseEntity<Country> deleteCountry( @RequestParam("id") Long id){
        ResponseEntity responseEntity;

        boolean isDeleted = countryService.deleteById(id);

        if(isDeleted){
            responseEntity = new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        else {
            responseEntity = new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        return responseEntity;
    }

}
