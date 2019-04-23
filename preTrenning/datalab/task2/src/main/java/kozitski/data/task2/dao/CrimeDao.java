package kozitski.data.task2.dao;

import kozitski.data.task2.domain.Crime;
import kozitski.data.task2.domain.OutcomeStatus;
import kozitski.data.task2.util.progress.DbProgressBar;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.util.Collection;
import java.util.Optional;

@Repository
@Slf4j
public class CrimeDao implements CommonDao<Crime> {
    private static final String DELETE_ALL_SREETS_SQL = "DELETE from streets";
    private static final String DELETE_ALL_LOCATIONS_SQL = "DELETE from locations";
    private static final String DELETE_ALL_STATUSES_SQL = "DELETE from outcomestatuses";
    private static final String DELETE_ALL_CRIMES_SQL = "DELETE from crimes";

    private static final String INSERT_STATUS = "INSERT INTO outcomestatuses VALUES (DEFAULT, :category, :status_date) " +
            "ON CONFLICT (category, status_date) DO NOTHING";
    private static final String INSERT_CRIME = "INSERT INTO crimes VALUES (:crime_id, :location_type, " +
            ":location_id, :context, :outcome_status_id, :persistence_id, :location_subtype, :month, :category) " +
            "ON CONFLICT (category, location_type, context, location_id, location_subtype) DO NOTHING";

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

    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private DbProgressBar progressBar;
    private LocationDao locationDao;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Autowired
    public void setJdbcTemplate(DataSource dataSource) {
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Autowired
    public void setProgressBar(DbProgressBar progressBar) {
        this.progressBar = progressBar;
    }

    @Autowired
    public void setLocationDao(LocationDao locationDao) {
        this.locationDao = locationDao;
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
    public void insertAll(Collection<? extends Crime> crimes) {
        progressBar.setTotalNumber(crimes.size());

        crimes.forEach(crime ->{
            insert(crime);
            progressBar.iterationLog();
        });

    }

    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    public void insert(Crime crime) {
        locationDao.insertStreet(crime.getLocation().getStreet());
        locationDao.insertLocation(crime.getLocation());
        insertOutcomeStatus(crime.getOutcomeStatus());

        Optional<Long> optionalOutcomeStatusId = findOutcomeStatusId(crime.getOutcomeStatus());
        Optional<Long> optionalLocationId = locationDao.findLocationId(crime.getLocation());

        if(optionalLocationId.isPresent() && optionalOutcomeStatusId.isPresent()){
            insertCrime(crime, optionalLocationId.get(), optionalOutcomeStatusId.get());
        }
        else if(!optionalLocationId.isPresent() && optionalOutcomeStatusId.isPresent()){
            insertCrime(crime, null, optionalOutcomeStatusId.get());
        }
        else if(optionalLocationId.isPresent()){
            insertCrime(crime, optionalLocationId.get(), null);
        }
        else {
            insertCrime(crime, null, null);
        }

    }

    private void insertOutcomeStatus(OutcomeStatus outcomeStatus){
        MapSqlParameterSource statusParams = new MapSqlParameterSource();
        statusParams.addValue(STATUS_CATEGORY, outcomeStatus.getCategory());
        statusParams.addValue(STATUS_DATE, outcomeStatus.getDate());

        namedParameterJdbcTemplate.update(INSERT_STATUS, statusParams);
    }
    private void insertCrime(Crime crime, Long locationId, Long statusId){
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

    private Optional<Long> findOutcomeStatusId(OutcomeStatus status){
        return Optional.ofNullable(namedParameterJdbcTemplate.getJdbcOperations().queryForObject(SEARCH_OUTCOME_STATUS,
                Long.class, status.getCategory(), status.getDate()));
    }


}
