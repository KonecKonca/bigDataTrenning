package kozitski.data.task2.util.progress;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ApiProgressBar {
    @Setter private long totalNumber;
    private long counter = 1;

    public void iterationLog(String latitude, String longitude){
        log.info("... Loaded[" + totalNumber + "] " + (counter++ * 100)/totalNumber  + "% " +
                "received elements from location: latitude=" + latitude + " longitude=" + longitude);
    }

}
