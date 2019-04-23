package com.epam.kozitski.config;

import com.epam.kozitski.service.CrimeService;
import com.epam.kozitski.service.CrimeServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@ComponentScan("com.epam.kozitski")
public class ApplicationConfig {

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    @Bean
    public CrimeService crimeService(){
        return new CrimeServiceImpl();
    }

}
