package kozitski.data.converter.io.reader.impl;

import kozitski.data.converter.dto.TrainDTO;
import kozitski.data.converter.io.parser.impl.TrainParser;
import kozitski.data.converter.io.reader.AbstractCsvReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * The type Train csv reader.
 */
@Component
public class TrainCsvReader extends AbstractCsvReader<TrainDTO> {

    private TrainParser trainParser;

    /**
     * Sets train parser.
     *
     * @param trainParser the train parser
     */
    @Autowired
    public void setTrainParser(TrainParser trainParser) {
        this.trainParser = trainParser;
    }

    @Override
    public TrainDTO parse(String line) {
        return trainParser.parseLine(line);
    }


}
