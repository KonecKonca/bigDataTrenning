package com.epam.kozitski.dao.impl;

import com.epam.kozitski.dao.CountryDao;
import com.epam.kozitski.domain.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class CountryJdbcTemplate implements CountryDao {
    private static final String COUNTRY_ID = "country_id";
    private static final String COUNTRY_NAME = "value";
    private static final String TABLE_NAME = "countries";

    private static final String SEARCH_BY_ID_SQL = "SELECT " +
            COUNTRY_ID + ", " + COUNTRY_NAME + " FROM countries WHERE " + COUNTRY_ID + "=?";
    private static final String SEARCH_ALL_SQL = "SELECT " +
            COUNTRY_ID + ", " + COUNTRY_NAME + " FROM countries";
    private static final String UPADTE_BY_ID_SQL = "UPDATE countries SET value =:value where country_id=:country_id";
    private static final String DROP_BY_ID_SQL = "DELETE from countries where country_id=:country_id";

    private NamedParameterJdbcTemplate  jdbcTemplate;
    private SimpleJdbcInsert insertCountry;
    @Autowired
    @Qualifier("dataSource")
    public void setJdbcTemplate(DataSource dataSource) {
        this.insertCountry = new SimpleJdbcInsert(dataSource).withTableName(TABLE_NAME).usingColumns(COUNTRY_NAME);
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public Optional<Country> searchById(long id) {
        return Optional.ofNullable(jdbcTemplate.getJdbcOperations().queryForObject(SEARCH_BY_ID_SQL, new CountryMapper(), id));
    }
    @Override
    public List<Country> searchAll() {
        return jdbcTemplate.query(SEARCH_ALL_SQL, new CountryMapper());
    }
    @Override
    public boolean update(Country country) {

        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue(COUNTRY_ID, country.getId());
        parameters.addValue(COUNTRY_NAME, country.getName());

        return jdbcTemplate.update(UPADTE_BY_ID_SQL, parameters) != 0;
    }
    @Override
    public boolean insert(Country country) {
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue(COUNTRY_NAME, country.getName());

        return insertCountry.execute(parameters) !=0;
    }
    @Override
    public boolean deleteById(long id) {
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue(COUNTRY_ID, id);

        return jdbcTemplate.update(DROP_BY_ID_SQL, parameters) !=0;
    }

    private static final class CountryMapper implements RowMapper<Country> {
        @Override
        public Country mapRow(ResultSet resultSet, int i) {
            Country country;

            try {
                country = new Country(resultSet.getLong(COUNTRY_ID), resultSet.getString(COUNTRY_NAME));
            }
            catch (SQLException e) {
                country = null;
            }

            return country;
        }
    }

}
