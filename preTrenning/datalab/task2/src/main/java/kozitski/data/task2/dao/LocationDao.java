package kozitski.data.task2.dao;

import kozitski.data.task2.domain.Location;
import kozitski.data.task2.domain.Street;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.Collection;
import java.util.Optional;

@Service
public class LocationDao implements CommonDao<Location> {
    private static final String STREET_ID = "street_id";
    private static final String STREET_NAME = "name";

    private static final String LOCATION_LATITUDE = "latitude";
    private static final String LOCATION_LONGTITUDE = "longtitude";
    private static final String LOCATION_STREET_ID = "street_id";

    private static final String INSERT_STREET = "INSERT INTO streets VALUES (:street_id, :name) " +
            "ON CONFLICT (street_id) DO NOTHING";
    private static final String INSERT_LOCATION = "INSERT INTO locations VALUES (DEFAULT, :latitude, :longtitude, :street_id) " +
            "ON CONFLICT (latitude, longtitude, street_id) DO NOTHING";

    private static final String SEARCH_LOCATION = "SELECT location_id FROM locations WHERE " +
            "latitude=? AND longtitude=? AND street_id=?";

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public void setJdbcTemplate(DataSource dataSource) {
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public void insertAll(Collection<? extends Location> locations) {
        locations.forEach(l -> {
            insertStreet(l.getStreet());
            insertLocation(l);

        });
    }

    public void insertStreet(Street street){
        MapSqlParameterSource streetParams = new MapSqlParameterSource();
        streetParams.addValue(STREET_ID, street.getId());
        streetParams.addValue(STREET_NAME, street.getName());

        namedParameterJdbcTemplate.update(INSERT_STREET, streetParams);
    }
    public void insertLocation(Location location){
        MapSqlParameterSource locationParams = new MapSqlParameterSource();
        locationParams.addValue(LOCATION_LATITUDE, location.getLatitude());
        locationParams.addValue(LOCATION_LONGTITUDE, location.getLongitude());
        locationParams.addValue(LOCATION_STREET_ID, location.getStreet().getId());

        namedParameterJdbcTemplate.update(INSERT_LOCATION, locationParams);
    }

    @Override
    public void clearDb() {
        throw new UnsupportedOperationException();
    }

    public Optional<Long> findLocationId(Location location){
        return Optional.ofNullable(namedParameterJdbcTemplate.getJdbcOperations().queryForObject(SEARCH_LOCATION,
                Long.class, location.getLatitude(), location.getLongitude(), location.getStreet().getId()));
    }

}
