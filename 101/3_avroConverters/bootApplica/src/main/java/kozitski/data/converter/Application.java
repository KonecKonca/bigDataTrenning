package kozitski.data.converter;

import kozitski.data.converter.runner.ApplicationExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * The type Application runner.
 */
@SpringBootApplication
public class Application implements CommandLineRunner {

    private ConfigurableApplicationContext context;
    private ApplicationExecutor applicationExecutor;

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
    public void setApplicationExecutor(ApplicationExecutor applicationExecutor) {
        this.applicationExecutor = applicationExecutor;
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
    public void run(String ... args) {
        applicationExecutor.runApplication(args);

        context.close();
    }

}