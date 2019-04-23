package kozitski.data.converter.io.reader;

import kozitski.data.converter.dto.TestDTO;
import kozitski.data.converter.io.parser.TestParser;
import kozitski.data.converter.io.IOConstant;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

@Component
@Slf4j
public class TestCsvReader {
    @Setter @Getter
    private int partitionSize = IOConstant.PARTS_SIZE;
    private int cursor;
    @Getter private boolean hasMore = true;
    private BufferedReader br;

    private TestParser testParser;

    @Autowired
    public void setTestParser(TestParser testParser) {
        this.testParser = testParser;
    }

    public TestCsvReader() {
        this(IOConstant.TEST_READ_PATH);
    }
    public TestCsvReader(String readFilePath) {
        Path pt = new Path(readFilePath);

        try {
            FileSystem fs = FileSystem.get(new Configuration());
            br = new BufferedReader(new InputStreamReader(fs.open(pt)));
        }
        catch (IOException e){
            log.error("File wasn't red", e);
        }

    }

    public List<TestDTO> readPart(){
        List<TestDTO> result = new LinkedList<>();

        try{
            String line;
            line = br.readLine();

            int writeCounter = 0;
            while (line != null && cursor++ < partitionSize ){
                line = br.readLine();
                result.add(testParser.parseLine(line));
                log.info("Red line: " + writeCounter++);
            }

            if(line == null){
                hasMore = false;
            }

            cursor = 0;
        }
        catch(Exception e){
            log.error("poblems during file reading", e);
        }

        return result;
    }

}
