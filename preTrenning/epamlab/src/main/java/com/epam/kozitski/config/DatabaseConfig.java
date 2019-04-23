package com.epam.kozitski.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class DatabaseConfig {
    private static final String DRIVER_NAME = "org.postgresql.Driver";
    private static final String URL = "jdbc:postgresql://localhost:5433/task1";
    private static final String LOGIN_PASS = "postgres";

    @Bean("dataSource")
    public DataSource driverManagerDataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setDriverClassName(DRIVER_NAME);
        dataSource.setUrl(URL);
        dataSource.setUsername(LOGIN_PASS);
        dataSource.setPassword(LOGIN_PASS);

        return dataSource;
    }

}
