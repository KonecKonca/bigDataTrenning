package kozitski.data.converter;

import kozitski.data.converter.io.writer.TestAvroWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class ApplicationRunner implements CommandLineRunner {

    private ConfigurableApplicationContext context;
    private TestAvroWriter testAvroWriter;

    @Autowired
    public void setContext(ConfigurableApplicationContext context) {
        this.context = context;
    }

    @Autowired
    public void setTestAvroWriter(TestAvroWriter testAvroWriter) {
        this.testAvroWriter = testAvroWriter;
    }

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(ApplicationRunner.class);
        app.setBannerMode(Banner.Mode.OFF);
        app.run(args);
    }

    @Override
    public void run(String... args) {
        testAvroWriter.writeToAvro();

        context.close();
    }

}