package kozitski.data.task2.service.stop;

import kozitski.data.task2.dao.StopDao;
import kozitski.data.task2.domain.Stop;
import kozitski.data.task2.domain.dto.StopDto;
import kozitski.data.task2.util.UriGenerator;
import kozitski.data.task2.util.mapper.stop.StopDtoMapper;
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
public class StopsServiceImpl implements StopsService {
    private static final String COMMON_STOP_URL = "https://data.police.uk/api/stops-street";
    private static final int TIME_OUT = 5;

    private RestTemplate restTemplate;
    private UriGenerator uriGenerator;
    private StopDtoMapper stopDtoMapper;
    private StopDao stopDao;

    @Autowired
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Autowired
    public void setUriGenerator(UriGenerator uriGenerator) {
        this.uriGenerator = uriGenerator;
    }

    @Autowired
    public void setStopDtoMapper(StopDtoMapper stopDtoMapper) {
        this.stopDtoMapper = stopDtoMapper;
    }

    @Autowired
    public void setStopDao(StopDao stopDao) {
        this.stopDao = stopDao;
    }

    @Override
    public Set<Stop> getAllStops(String latitude, String longitude) {
        URI uri = uriGenerator.generateUri(COMMON_STOP_URL, latitude, longitude);
        return getStops(uri);
    }

    @Override
    public Set<Stop> getAllStopsByDate(String latitude, String longitude, String date) {
        URI uri = uriGenerator.generateUriByDate(COMMON_STOP_URL, latitude, longitude, date);
        return getStops(uri);
    }

    private Set<Stop> getStops(URI uri){
        Set<StopDto> stops = new HashSet<>();

        ResponseEntity<StopDto[]> stopsDto = restTemplate.getForEntity(uri, StopDto[].class);
        HttpStatus statusCode = stopsDto.getStatusCode();

        if(statusCode.is4xxClientError() || statusCode.is5xxServerError()){
            try {
                TimeUnit.SECONDS.sleep(TIME_OUT);
            }
            catch (InterruptedException e) {
                log.warn("Sleep exception", e);
            }
            log.warn("4xx or 5xx was received");
            getStops(uri);
        }
        else {
            stops.addAll(Arrays.asList(Objects.requireNonNull(stopsDto.getBody())));
        }

        return stopDtoMapper.mapToSet(stops);
    }

    @Override
    public void clearDb() {
        stopDao.clearDb();
    }

    @Override
    public void updateDb(Collection<? extends Stop> stops) {
        try {
            stopDao.insertAll(stops);
        }
        catch (Exception e){
            log.error("Can not insert batch of stops into database", e);
        }
    }

}
