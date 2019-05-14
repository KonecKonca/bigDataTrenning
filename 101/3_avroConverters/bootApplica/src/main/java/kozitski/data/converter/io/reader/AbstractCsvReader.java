package kozitski.data.converter.io.reader;

import kozitski.data.converter.runner.arg.ApplicationProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

/**
 * The type Abstract csv reader.
 *
 * @param <T> the type parameter
 */
@Slf4j
public abstract class AbstractCsvReader<T> {

    private ApplicationProperties applicationProperties;

    @Autowired
    public void setApplicationProperties(ApplicationProperties applicationProperties) {
        this.applicationProperties = applicationProperties;
    }

    @Setter
    private int batchSize;
    private int cursor;
    @Getter private boolean hasMore = true;
    private BufferedReader br;

    /**
     * Instantiates a new Abstract csv reader.
     */
    public AbstractCsvReader() { }

    /**
        Call this method is necessary before using this object!!
    */
    public void init(){
        Path pt = new Path(applicationProperties.getInputPath());
        batchSize = applicationProperties.getBatchSize();

        try {
            FileSystem fs = FileSystem.get(new Configuration());
            br = new BufferedReader(new InputStreamReader(fs.open(pt)));
        }
        catch (IOException e){
            log.error("File wasn't red", e);
        }
    }

    /**
     * Has more boolean.
     *
     * @return the boolean
     */
    public boolean hasMore() {
        return hasMore;
    }

    /**
     * Read part list.
     *
     * @return the list
     */
    public List<T> readPart(){
        List<T> result = new LinkedList<>();

        try{
            String line;
            line = br.readLine();

            int writeCounter = NumberUtils.INTEGER_ZERO;
            while (line != null && cursor++ < batchSize){
                line = br.readLine();
                result.add(parse(line));
                log.info("Line which was red: " + writeCounter++);
            }

            if(line == null){
                hasMore = false;
            }

            cursor = NumberUtils.INTEGER_ZERO;
        }
        catch(Exception e){
            log.error("poblems during file reading", e);
        }

        return result;
    }

    /**
     * Parse t.
     *
     * @param line the line
     * @return the t
     */
    public abstract T parse(String line);

}
