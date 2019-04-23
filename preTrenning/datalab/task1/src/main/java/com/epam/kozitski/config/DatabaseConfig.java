package com.epam.kozitski.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class DatabaseConfig {
    private static final String DRIVER_NAME = "org.postgresql.Driver";
    private static final String URL = "jdbc:postgresql://localhost:5432/data1";
    private static final String LOGIN = "postgres";
    private static final String PASSWORD = "root";

    @Bean("dataSource")
    public DataSource dataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setDriverClassName(DRIVER_NAME);
        dataSource.setUrl(URL);
        dataSource.setUsername(LOGIN);
        dataSource.setPassword(PASSWORD);

        return dataSource;
    }

    @Bean()
    public JdbcTemplate getJdbcTemplate(){
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(dataSource());

        return jdbcTemplate;
    }

}
