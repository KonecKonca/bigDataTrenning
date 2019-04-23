package com.epam.kozitski.service;

import com.epam.kozitski.dao.CrimeDao;
import com.epam.kozitski.domain.Crime;
import com.epam.kozitski.dto.CrimeDto;
import com.epam.kozitski.util.mapper.CrimeDtoMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Service
@Slf4j
public class CrimeServiceImpl implements CrimeService{
    private static final String COMMON_URL = "https://data.police.uk/api/crimes-street/all-crime";
    private static final String PARAMETER_DELIMETER = "?";
    private static final String PARAMETER_INIT = "=";
    private static final String PARAMETER_UNION = "&";
    private static final String LAT_PARAMETER = "lat";
    private static final String LNG_PARAMETER = "lng";
    private static final String DATE_PARAMETER = "date";

    private static final String TXT_POSTFIX = ".txt";

    private CrimeDtoMapper crimeDtoMapper;
    @Autowired
    public void setCrimeDtoMapper(CrimeDtoMapper crimeDtoMapper) {
        this.crimeDtoMapper = crimeDtoMapper;
    }

    private CrimeDao crimeDao;
    @Autowired
    public void setCrimeDao(CrimeDao crimeDao) {
        this.crimeDao = crimeDao;
    }

    private RestTemplate restTemplate;
    @Autowired
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public List<Crime> getAllCrimesByDate(String latitude, String longitude, String date) throws URISyntaxException {
        String stringUri = generateUriBYDate(latitude, longitude, date);

        return getCrimes(stringUri);
    }
    @Override
    public List<Crime> getAllCrimes(String latitude, String longitude) throws URISyntaxException {
        String stringUri = generateUri(latitude, longitude);

        return getCrimes(stringUri);
    }

    private List<Crime> getCrimes(String stringUri) throws URISyntaxException {
        URI uri = new URI(stringUri);

        CrimeDto[] crimeDtos = restTemplate.getForObject(uri, CrimeDto[].class);
        ArrayList<CrimeDto> crimesList = new ArrayList<>(Arrays.asList(crimeDtos));

        return crimeDtoMapper.mapToListCrimes(crimesList);
    }
    private String generateUri(String latitude, String longitude){
        StringBuilder stringUri = new StringBuilder(COMMON_URL);
        stringUri.append(PARAMETER_DELIMETER);

        stringUri.append(LAT_PARAMETER);
        stringUri.append(PARAMETER_INIT);
        stringUri.append(latitude);
        stringUri.append(PARAMETER_UNION);

        stringUri.append(LNG_PARAMETER);
        stringUri.append(PARAMETER_INIT);
        stringUri.append(longitude);

        return stringUri.toString();
    }
    private String generateUriBYDate(String latitude, String longitude, String date){
        StringBuilder stringUri = new StringBuilder(generateUri(latitude, longitude));

        stringUri.append(PARAMETER_UNION);

        stringUri.append(DATE_PARAMETER);
        stringUri.append(PARAMETER_INIT);
        stringUri.append(date);

        return stringUri.toString();
    }

    @Override
    public void clearDb() {
        crimeDao.clearDb();
    }

    @Override
    public void updateDb(Collection<Crime> crimes) {
        crimeDao.insertAll(crimes);
    }

    @Override
    public void getTxtCrimesList(Collection<Crime> crimes, String fileName) {
        String currentDir = System.getProperty("user.dir");
        File file = new File( currentDir + File.separator + fileName + TXT_POSTFIX);
        log.info("file stored at directory: " + file.getAbsolutePath());

        try (FileWriter fileWriter = new FileWriter(file)){
            crimes.forEach(crime -> {
                try {
                    fileWriter.write(crime.toString() + "\n");
                } catch (IOException e1) {
                    log.warn("Current crime wasn't written" + crime, e1);
                }
            });
        }
        catch (IOException e) {
            log.warn("Problem during working with file", e);
        }
    }

}
