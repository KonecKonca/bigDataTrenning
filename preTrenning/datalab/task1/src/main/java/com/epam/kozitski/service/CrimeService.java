package com.epam.kozitski.service;

import com.epam.kozitski.domain.Crime;

import java.net.URISyntaxException;
import java.util.Collection;
import java.util.List;

public interface CrimeService {

    List<Crime> getAllCrimesByDate(String latitude, String longitude, String date) throws URISyntaxException;
    List<Crime> getAllCrimes(String latitude, String longitude) throws URISyntaxException;
    void clearDb();
    void updateDb(Collection<Crime> crimes);
    void getTxtCrimesList(Collection<Crime> crimes, String filePath);
    
}
