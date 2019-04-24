package kozitski.data.converter.runner;

import kozitski.data.converter.io.writer.AbstractAvroWriter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * The type Application executor.
 */
@Component
@Slf4j
public class ApplicationExecutor {

    private List<AbstractAvroWriter> writerList;

    /**
     * Sets writer list.
     *
     * @param writerList the writer list
     */
    @Autowired
    public void setWriterList(List<AbstractAvroWriter> writerList) {
        this.writerList = writerList;
    }

    /**
     * Run application.
     */
    public void runApplication(){
        log.info("Application was running...");

        writerList.forEach(AbstractAvroWriter::writeRecord);

        log.info("...Application stopped work");
    }

}
