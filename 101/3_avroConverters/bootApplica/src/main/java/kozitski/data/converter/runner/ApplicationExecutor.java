package kozitski.data.converter.runner;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * The type Application executor.
 */
@Component
@Slf4j
public class ApplicationExecutor {

    private WriterRunner writerRunner;

    @Autowired
    public void setWriterRunner(WriterRunner writerRunner) {
        this.writerRunner = writerRunner;
    }

    /**
     * Run application.
     */
    public void runApplication(String ... args){
        log.info("Application was running...");

        writerRunner.writeAll(args);

        log.info("...Application stopped work");
    }

}
