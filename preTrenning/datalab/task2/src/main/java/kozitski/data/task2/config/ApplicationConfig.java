package kozitski.data.task2.config;

import kozitski.data.task2.service.crime.CrimeService;
import kozitski.data.task2.service.crime.CrimeServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@ComponentScan("kozitski.data.task2")
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
