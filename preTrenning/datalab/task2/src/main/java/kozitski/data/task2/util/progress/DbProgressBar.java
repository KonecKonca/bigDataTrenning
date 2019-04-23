package kozitski.data.task2.util.progress;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class DbProgressBar {
    @Setter private long totalNumber;
    @Setter private long counter = 1;

    public void iterationLog(){
        log.info("... stored to db[" + totalNumber + "] " + (counter++ * 100)/totalNumber  + "% elements");
    }

    public void resetCounter(){counter = 0;}

}
