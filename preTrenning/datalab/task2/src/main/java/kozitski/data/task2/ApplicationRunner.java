package kozitski.data.task2;

import kozitski.data.task2.runner.ApplicationConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class ApplicationRunner implements CommandLineRunner {

    private ApplicationConfigurer configurer;
    private ConfigurableApplicationContext context;

    @Autowired
    public void setConfigurer(ApplicationConfigurer configurer) {
        this.configurer = configurer;
    }

    @Autowired
    public void setContext(ConfigurableApplicationContext context) {
        this.context = context;
    }

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(ApplicationRunner.class);
        app.setBannerMode(Banner.Mode.OFF);
        app.run(args);
    }

    @Override
    public void run(String... args) {
        configurer.run(args);

        context.close();
    }

}