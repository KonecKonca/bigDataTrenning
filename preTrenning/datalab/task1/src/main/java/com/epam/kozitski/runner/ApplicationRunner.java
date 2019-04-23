package com.epam.kozitski.runner;

import com.epam.kozitski.config.ApplicationConfig;
import com.epam.kozitski.service.CrimeService;
import com.epam.kozitski.service.CrimeServiceImpl;
import org.apache.commons.cli.*;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;
import java.util.Properties;

public class ApplicationRunner {

    public static void main(String[] args) throws ParseException {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(ApplicationConfig.class);

        ApplicationConfigurer configurer = applicationContext.getBean("applicationConfigurer", ApplicationConfigurer.class);
        configurer.run(args);
    }

}
