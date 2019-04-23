package kozitski.data.task2.service.crime;

import kozitski.data.task2.dao.CrimeDao;
import kozitski.data.task2.domain.Crime;
import kozitski.data.task2.domain.dto.CrimeDto;
import kozitski.data.task2.util.UriGenerator;
import kozitski.data.task2.util.mapper.crime.CrimeDtoMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class CrimeServiceImpl implements CrimeService{
    private static final String COMMON_CRIME_URL = "https://data.police.uk/api/crimes-street/all-crime";
    private static final int TIME_OUT = 5;

    private CrimeDtoMapper crimeDtoMapper;
    private CrimeDao crimeDao;
    private RestTemplate restTemplate;
    private UriGenerator uriGenerator;

    @Autowired
    public void setCrimeDtoMapper(CrimeDtoMapper crimeDtoMapper) {
        this.crimeDtoMapper = crimeDtoMapper;
    }

    @Autowired
    public void setCrimeDao(CrimeDao crimeDao) {
        this.crimeDao = crimeDao;
    }

    @Autowired
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Autowired
    public void setUriGenerator(UriGenerator uriGenerator) {
        this.uriGenerator = uriGenerator;
    }

    @Override
    public Set<Crime> getAllCrimesByDate(String latitude, String longitude, String date) {
        URI uri = uriGenerator.generateUriByDate(COMMON_CRIME_URL, latitude, longitude, date);

        return getCrimes(uri);
    }
    @Override
    public Set<Crime> getAllCrimes(String latitude, String longitude) {
        URI uri = uriGenerator.generateUri(COMMON_CRIME_URL, latitude, longitude);

        return getCrimes(uri);
    }

    private Set<Crime> getCrimes(URI uri) {
        HashSet<CrimeDto> crimesList = new HashSet<>();

        ResponseEntity<CrimeDto[]> forEntity = restTemplate.getForEntity(uri, CrimeDto[].class);
        HttpStatus statusCode = forEntity.getStatusCode();
        if(statusCode.is4xxClientError() || statusCode.is5xxServerError()){
            try {
                TimeUnit.SECONDS.sleep(TIME_OUT);
            }
            catch (InterruptedException e) {
                log.warn("Sleep exception", e);
            }
            log.warn("4xx or 5xx was received");
            getCrimes(uri);
        }
        else {
            crimesList.addAll(Arrays.asList(Objects.requireNonNull(forEntity.getBody())));
        }

        return crimeDtoMapper.mapToSet(crimesList);
    }

    @Override
    public void clearDb() {
        crimeDao.clearDb();
    }

    @Override
    public void updateDb(Collection<? extends Crime> crimes) {
        try {
            crimeDao.insertAll(crimes);
        }
        catch (Exception e){
            log.error("Can not insert batch of crimes into database", e);
        }
    }

}

















