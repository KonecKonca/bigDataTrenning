package com.epam.kozitski.dao;

import com.epam.kozitski.domain.Crime;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.util.Collection;
import java.util.Objects;
import java.util.Optional;

@Repository
@Slf4j
public class CrimeDaoImpl implements CrimeDao {
    private static final String DELETE_ALL_SREETS_SQL = "DELETE from streets";
    private static final String DELETE_ALL_LOCATIONS_SQL = "DELETE from locations";
    private static final String DELETE_ALL_STATUSES_SQL = "DELETE from outcomestatuses";
    private static final String DELETE_ALL_CRIMES_SQL = "DELETE from crimes";

    private static final String INSERT_STREET = "INSERT INTO streets VALUES (:street_id, :name)";
    private static final String INSERT_LOCATION = "INSERT INTO locations VALUES (DEFAULT, :latitude, :longtitude, :street_id)";
    private static final String INSERT_STATUS = "INSERT INTO outcomestatuses VALUES (DEFAULT, :category, :status_date)";
    private static final String INSERT_CRIME = "INSERT INTO crimes VALUES (:crime_id, :location_type, " +
            ":location_id, :context, :outcome_status_id, :persistence_id, :location_subtype, :month, :category)";

    private static final String STREET_ID = "street_id";
    private static final String STREET_NAME = "name";

    private static final String LOCATION_LATITUDE = "latitude";
    private static final String LOCATION_LONGTITUDE = "longtitude";
    private static final String LOCATION_STREET_ID = "street_id";

    private static final String STATUS_ID = "status_id";
    private static final String STATUS_CATEGORY = "category";
    private static final String STATUS_DATE = "status_date";

    private static final String CRIME_ID = "crime_id";
    private static final String CRIME_LOCATION_TYPE = "location_type";
    private static final String CRIME_LOCATION_ID = "location_id";
    private static final String CRIME_CONTEXT = "context";
    private static final String CRIME_STATUS_ID = "outcome_status_id";
    private static final String CRIME_PERSISTANCE_ID = "persistence_id";
    private static final String CRIME_LOCATION_SUBTYPE = "location_subtype";
    private static final String CRIME_MONTH = "month";
    private static final String CRIME_CATEGORY = "category";

    private static final String SEARCH_OUTCOME_STATUS = "SELECT status_id FROM outcomestatuses WHERE category=? AND status_date=?";
    private static final String SEARCH_STREET = "SELECT street_id FROM streets WHERE name=? AND street_id=?";
    private static final String SEARCH_LOCATION = "SELECT location_id FROM locations WHERE " +
            "latitude=? AND longtitude=? AND street_id=?";


    private JdbcTemplate jdbcTemplate;
    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    @Autowired
    public void setJdbcTemplate(DataSource dataSource) {
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    @Transactional
    public void clearDb() {
        jdbcTemplate.update(DELETE_ALL_CRIMES_SQL);
        jdbcTemplate.update(DELETE_ALL_STATUSES_SQL);
        jdbcTemplate.update(DELETE_ALL_LOCATIONS_SQL);
        jdbcTemplate.update(DELETE_ALL_SREETS_SQL);
    }

    @Override
    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    public void insertAll(Collection<Crime> crimes) {
        crimes.forEach(this::insert);
    }

    private void insert(Crime crime) {
        long locationId = Integer.MIN_VALUE;
        long outcomeStatusId = Integer.MIN_VALUE;
        long streetId = Integer.MIN_VALUE;

        KeyHolder streetHolder = null;
        try {
            streetHolder = insertStreet(crime);
        }
        catch (DuplicateKeyException e){
            Optional<Long> optionalStreetId = findStreetId(crime.getLocation().getStreet());
            if(optionalStreetId.isPresent()){
                streetId = optionalStreetId.get();
            }
            else {
                log.warn("Street wasn't insert", e);
            }
        }

        KeyHolder locationHolder = null;
        try {
            if(streetId == Integer.MIN_VALUE){
                locationHolder = insertLocation(crime, Objects.requireNonNull(streetHolder.getKey()).longValue());
            }
            else {
                locationHolder = insertLocation(crime, streetId);
            }
        }
        catch (DuplicateKeyException e){
            Optional<Long> optionalLocationId = findLocationId(crime.getLocation());
            if(optionalLocationId.isPresent()){
                locationId = optionalLocationId.get();
            }
            log.warn("Location wasn't insert", e);
        }

        KeyHolder statusHolder = null;
        try {
            statusHolder = insertOutcomeStatus(crime);
        }
        catch (DuplicateKeyException e){
            Optional<Long> optionalId = findOutcomeStatusId(crime.getOutcomeStatus());
            if(optionalId.isPresent()){
                outcomeStatusId = optionalId.get();
            }
            else {
                log.warn("OutcomeStatus wasn't insert", e);
            }
        }

        try{
            if(locationId == Integer.MIN_VALUE && outcomeStatusId == Integer.MIN_VALUE){
                insertCrime(crime, Objects.requireNonNull(locationHolder.getKey()).longValue(),
                        Objects.requireNonNull(statusHolder.getKey()).longValue());
            }
            else if(locationId == Integer.MIN_VALUE && outcomeStatusId != Integer.MIN_VALUE){
                insertCrime(crime, Objects.requireNonNull(locationHolder.getKey()).longValue(),
                        outcomeStatusId);
            }
            else if(locationId != Integer.MIN_VALUE && outcomeStatusId == Integer.MIN_VALUE){
                insertCrime(crime, locationId,
                        Objects.requireNonNull(statusHolder.getKey()).longValue());
            }
            else if (locationId != Integer.MIN_VALUE && outcomeStatusId != Integer.MIN_VALUE){
                insertCrime(crime, locationId, outcomeStatusId);
            }
        }
        catch (DuplicateKeyException e){
            log.warn("Such crime already exist", e);
        }
    }

    private KeyHolder insertStreet(Crime crime){
        MapSqlParameterSource streetParams = new MapSqlParameterSource();
        KeyHolder streetHolder = new GeneratedKeyHolder();
        streetParams.addValue(STREET_ID, crime.getLocation().getStreet().getId());
        streetParams.addValue(STREET_NAME, crime.getLocation().getStreet().getName());
        namedParameterJdbcTemplate.update(INSERT_STREET, streetParams, streetHolder, new String[]{LOCATION_STREET_ID});

        return streetHolder;
    }
    private KeyHolder insertLocation(Crime crime, Long streetId){
        MapSqlParameterSource locationParams = new MapSqlParameterSource();
        KeyHolder locationHolder = new GeneratedKeyHolder();
        locationParams.addValue(LOCATION_LATITUDE, crime.getLocation().getLatitude());
        locationParams.addValue(LOCATION_LONGTITUDE, crime.getLocation().getLongitude());
        locationParams.addValue(LOCATION_STREET_ID, streetId);
        namedParameterJdbcTemplate.update(INSERT_LOCATION, locationParams, locationHolder,  new String[]{CRIME_LOCATION_ID});

        return locationHolder;
    }
    private KeyHolder insertOutcomeStatus(Crime crime){
        MapSqlParameterSource statusParams = new MapSqlParameterSource();
        KeyHolder statusHolder = new GeneratedKeyHolder();
        statusParams.addValue(STATUS_CATEGORY, crime.getOutcomeStatus().getCategory());
        statusParams.addValue(STATUS_DATE, crime.getOutcomeStatus().getDate());
        namedParameterJdbcTemplate.update(INSERT_STATUS, statusParams, statusHolder,  new String[]{STATUS_ID});

        return statusHolder;
    }
    private void insertCrime(Crime crime, long locationId, long statusId){
        MapSqlParameterSource crimesParams = new MapSqlParameterSource();
        crimesParams.addValue(CRIME_ID, crime.getId());
        crimesParams.addValue(CRIME_LOCATION_TYPE, crime.getLocationType());
        crimesParams.addValue(CRIME_LOCATION_ID, locationId);
        crimesParams.addValue(CRIME_CONTEXT, crime.getContext());
        crimesParams.addValue(CRIME_STATUS_ID, statusId);
        crimesParams.addValue(CRIME_PERSISTANCE_ID, crime.getPersistentId());
        crimesParams.addValue(CRIME_LOCATION_SUBTYPE, crime.getLocationSubtype());
        crimesParams.addValue(CRIME_MONTH, crime.getMonth());
        crimesParams.addValue(CRIME_CATEGORY, crime.getCategory());
        namedParameterJdbcTemplate.update(INSERT_CRIME, crimesParams);
    }

    private Optional<Long> findOutcomeStatusId(Crime.OutcomeStatus status){
        return Optional.ofNullable(namedParameterJdbcTemplate.getJdbcOperations().queryForObject(SEARCH_OUTCOME_STATUS,
                Long.class, status.getCategory(), status.getDate()));
    }
    private Optional<Long> findStreetId(Crime.Location.Street street){
        return Optional.ofNullable(namedParameterJdbcTemplate.getJdbcOperations().queryForObject(SEARCH_STREET,
                Long.class, street.getName(), street.getId()));
    }
    private Optional<Long> findLocationId(Crime.Location location){
        return Optional.ofNullable(namedParameterJdbcTemplate.getJdbcOperations().queryForObject(SEARCH_LOCATION,
                Long.class, location.getLatitude(), location.getLongitude(), location.getStreet().getId()));
    }

}
