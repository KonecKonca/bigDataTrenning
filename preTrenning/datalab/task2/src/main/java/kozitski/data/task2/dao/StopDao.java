package kozitski.data.task2.dao;

import kozitski.data.task2.domain.OutcomeObject;
import kozitski.data.task2.domain.Stop;
import kozitski.data.task2.util.input.DayTransformer;
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
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Optional;

@Repository
@Slf4j
public class StopDao implements CommonDao<Stop> {
    private static final String DELETE_ALL_STOPS_SQL = "DELETE from stops";
    private static final String DELETE_ALL_STOPS_OUTCOME_OBJECTS = "DELETE from outcome_objects";

    private static final String INSERT_OUTCOME_OBJECT = "INSERT INTO outcome_objects VALUES (:id, :name) " +
            "ON CONFLICT (id, name) DO NOTHING ";
    private static final String INSERT_STOP = "INSERT INTO stops VALUES (DEFAULT, :age_range, :outcome, :involved_person, " +
            ":self_defined_ethnicity, :gender, :legislation, :outcome_linked_to_object_of_search, :datetime, :removal_of_more_than_outer_clothing, " +
            ":outcome_object_id, :location_id, :operation, :officer_defined_ethnicity, :type, :operation_name, :object_of_search) " +
            "ON CONFLICT (age_range, outcome, involved_person, self_defined_ethnicity, gender, legislation, " +
            "datetime, outcome_object_id, location_id, officer_defined_ethnicity, " +
            "type, object_of_search) DO NOTHING ";

    private static final String SEARCH_OUTCOME_OBJECT = "SELECT id FROM outcome_objects WHERE name=?";

    private static final String OUTCOME_OBJECT_ID = "id";
    private static final String OUTCOME_OBJECT_NAME = "name";

    private static final String STOP_AGE_RANGE = "age_range";
    private static final String STOP_OUTCOME = "outcome";
    private static final String STOP_INVOLVED_PERSON = "involved_person";
    private static final String STOP_SELF_DEFINED_ETHNICITY = "self_defined_ethnicity";
    private static final String STOP_GENDER = "gender";
    private static final String STOP_LEGISLATION = "legislation";
    private static final String STOP_OUTCOME_LINKED_TO_OBJECT_OF_SEARCH = "outcome_linked_to_object_of_search";
    private static final String STOP_DATETIME = "datetime";
    private static final String STOP_REMOVAL_OF_MORE_THAN_OUTER_CLOTHING = "removal_of_more_than_outer_clothing";
    private static final String STOP_OUTCOME_OBJECT_ID = "outcome_object_id";
    private static final String STOP_LOCATION_ID = "location_id";
    private static final String STOP_OPERATION = "operation";
    private static final String STOP_OFFICER_DEFINED_ETHNICITY = "officer_defined_ethnicity";
    private static final String STOP_TYPE = "type";
    private static final String STOP_OPERATION_NAME = "operation_name";
    private static final String STOP_OBJECT_OF_SEARCH= "object_of_search";

    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private DbProgressBar progressBar;
    private LocationDao locationDao;
    private DayTransformer stopDateTransformer;

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

    @Autowired
    public void setStopDateTransformer(DayTransformer stopDateTransformer) {
        this.stopDateTransformer = stopDateTransformer;
    }

    @Override
    public void insertAll(Collection<? extends Stop> stops) {
        progressBar.setTotalNumber(stops.size());
        progressBar.resetCounter();

        stops.forEach(crime ->{
            insert(crime);
            progressBar.iterationLog();
        });
    }

    private void insert(Stop stop) {
        locationDao.insertStreet(stop.getLocation().getStreet());
        locationDao.insertLocation(stop.getLocation());
        insertOutcomeObject(stop.getOutcomeObject());

        Optional<String> optionalOutcomeObjectId = findOutcomeObjectId(stop.getOutcomeObject());
        Optional<Long> optionalLocationId = locationDao.findLocationId(stop.getLocation());

        if(optionalOutcomeObjectId.isPresent() && optionalLocationId.isPresent()){
            insertStop(stop, optionalOutcomeObjectId.get().trim(), optionalLocationId.get());
        }
        else if(!optionalOutcomeObjectId.isPresent() && optionalLocationId.isPresent()){
            insertStop(stop, null, optionalLocationId.get());
        }
        else if(optionalOutcomeObjectId.isPresent()){
            insertStop(stop, optionalOutcomeObjectId.get(), null);
        }
        else {
            insertStop(stop, null, null);
        }

    }

    private void insertOutcomeObject(OutcomeObject outcomeObject){
        MapSqlParameterSource statusParams = new MapSqlParameterSource();
        statusParams.addValue(OUTCOME_OBJECT_ID, outcomeObject.getId());
        statusParams.addValue(OUTCOME_OBJECT_NAME, outcomeObject.getName());

        namedParameterJdbcTemplate.update(INSERT_OUTCOME_OBJECT, statusParams);
    }
    private void insertStop(Stop stop, String outcomeObjectId, Long locationId){
        MapSqlParameterSource statusParams = new MapSqlParameterSource();
        statusParams.addValue(STOP_AGE_RANGE, stop.getAgeRange());
        statusParams.addValue(STOP_OUTCOME, stop.getOutcome());
        statusParams.addValue(STOP_INVOLVED_PERSON, stop.isInvolvedPerson());
        statusParams.addValue(STOP_SELF_DEFINED_ETHNICITY, stop.getSelfDefinedEthnicity());
        statusParams.addValue(STOP_GENDER, stop.getGender());
        statusParams.addValue(STOP_LEGISLATION, stop.getLegislation());
        statusParams.addValue(STOP_OUTCOME_LINKED_TO_OBJECT_OF_SEARCH, stop.isOutcomeLinkedToObjectOfSearch());
        statusParams.addValue(STOP_DATETIME, new Timestamp(stopDateTransformer.longConvert(stop.getDatetime())));
        statusParams.addValue(STOP_REMOVAL_OF_MORE_THAN_OUTER_CLOTHING, stop.isRemovalOfMoreThanOuterClothing());
        statusParams.addValue(STOP_OUTCOME_OBJECT_ID, outcomeObjectId);
        statusParams.addValue(STOP_LOCATION_ID, locationId);
        statusParams.addValue(STOP_OPERATION, stop.getOperation());
        statusParams.addValue(STOP_OFFICER_DEFINED_ETHNICITY, stop.getOfficerDefinedEthnicity());
        statusParams.addValue(STOP_TYPE, stop.getType());
        statusParams.addValue(STOP_OPERATION_NAME, stop.getOperationName());
        statusParams.addValue(STOP_OBJECT_OF_SEARCH, stop.getObjectOfSearch());

        namedParameterJdbcTemplate.update(INSERT_STOP, statusParams);
    }

    private Optional<String> findOutcomeObjectId(OutcomeObject outcomeObject){
        return Optional.ofNullable(namedParameterJdbcTemplate.getJdbcOperations().queryForObject(SEARCH_OUTCOME_OBJECT,
                String.class, outcomeObject.getName()));
    }

    @Override
    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    public void clearDb() {
        jdbcTemplate.update(DELETE_ALL_STOPS_SQL);
        jdbcTemplate.update(DELETE_ALL_STOPS_OUTCOME_OBJECTS);
    }

}
