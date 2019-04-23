package com.epam.kozitski.service.impl;

import com.epam.kozitski.dao.CountryDao;
import com.epam.kozitski.domain.Country;
import com.epam.kozitski.service.CountryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class CountryServiceImpl implements CountryService {

    private CountryDao countryDao;
    @Autowired
    public void setCountryDao(CountryDao countryDao) {
        this.countryDao = countryDao;
    }

    @Override
    public Optional<Country> searchById(long id) {
        Optional<Country> country = countryDao.searchById(id);

        if(!country.isPresent()){
            log.debug("Country with id " + id + "wasn't founded");
        }

        return country;
    }
    @Override
    public List searchAll() {
        List<Country> countries = countryDao.searchAll();

        if (countries == null || countries.isEmpty()){
            log.debug("Countries are not founded");
        }

        return countries;
    }
    @Override
    public boolean update(Country country) {
        boolean isUpdate = countryDao.update(country);

        if(isUpdate){
            log.debug(country + " updated successfully");
        }

        return isUpdate;
    }
    @Override
    public boolean insert(Country country) {
        boolean isInsert = countryDao.insert(country);

        if(isInsert){
            log.debug(country + " inserted successfully");
        }

        return isInsert;
    }
    @Override
    public boolean deleteById(long id) {
        boolean isDeleted = countryDao.deleteById(id);

        if(isDeleted){
            log.debug("Country with id " + id + "wasn't deleted");
        }

        return isDeleted;
    }

}
