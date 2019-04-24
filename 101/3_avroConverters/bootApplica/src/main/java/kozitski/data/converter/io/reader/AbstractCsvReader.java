package kozitski.data.converter.io.reader;

import kozitski.data.converter.io.IOConstant;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

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
    @Setter
    private int partitionSize = IOConstant.PARTS_SIZE;
    private int cursor;
    @Getter private boolean hasMore = true;
    private BufferedReader br;

    /**
     * Instantiates a new Abstract csv reader.
     */
    public AbstractCsvReader() {
        this(IOConstant.TEST_READ_PATH);
    }

    /**
     * Instantiates a new Abstract csv reader.
     *
     * @param readFilePath the read file path
     */
    public AbstractCsvReader(String readFilePath) {
        Path pt = new Path(readFilePath);

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
            while (line != null && cursor++ < partitionSize ){
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
