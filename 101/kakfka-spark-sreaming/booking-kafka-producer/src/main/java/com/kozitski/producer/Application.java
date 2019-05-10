package com.kozitski.producer;

import com.kozitski.producer.runner.ProducerApplicationRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * Application runner.
 */
@SpringBootApplication
public class Application implements CommandLineRunner {

    private ConfigurableApplicationContext context;
    private ProducerApplicationRunner applicationRunner;

    /**
     * Sets context.
     *
     * @param context the context
     */
    @Autowired
    public void setContext(ConfigurableApplicationContext context) {
        this.context = context;
    }

    @Autowired
    public void setApplicationRunner(ProducerApplicationRunner applicationRunner) {
        this.applicationRunner = applicationRunner;
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(Application.class);
        app.setBannerMode(Banner.Mode.OFF);
        app.run(args);
    }

    @Override
    public void run(String... args) {
        applicationRunner.start(args);

        context.close();
    }

}
