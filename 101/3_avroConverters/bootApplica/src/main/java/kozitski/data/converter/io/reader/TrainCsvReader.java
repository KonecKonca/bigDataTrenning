package kozitski.data.converter.io.reader;

import kozitski.data.converter.dto.TrainDTO;
import kozitski.data.converter.io.IOConstant;
import kozitski.data.converter.io.parser.TrainParser;
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

@Slf4j
@Component
public class TrainCsvReader {
    @Setter
    @Getter
    private int partitionSize = IOConstant.PARTS_SIZE;
    private int cursor;
    @Getter private boolean hasMore = true;
    private BufferedReader br;

    private TrainParser trainParser;

    @Autowired
    public void setTrainParser(TrainParser trainParser) {
        this.trainParser = trainParser;
    }

    public TrainCsvReader() {
        this(IOConstant.TRAIN_READ_PATH);
    }
    public TrainCsvReader(String readFilePath) {
        Path pt = new Path(readFilePath);

        try {
            FileSystem fs = FileSystem.get(new Configuration());
            br = new BufferedReader(new InputStreamReader(fs.open(pt)));
        }
        catch (IOException e){
            log.error("File wasn't red", e);
        }

    }

    public List<TrainDTO> readPart(){
        List<TrainDTO> result = new LinkedList<>();

        try{
            String line;
            line = br.readLine();

            int writeCounter = 0;
            while (line != null && cursor++ < partitionSize ){
                line = br.readLine();
                result.add(trainParser.parseLine(line));
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
