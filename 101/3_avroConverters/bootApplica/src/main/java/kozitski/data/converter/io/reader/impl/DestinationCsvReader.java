package kozitski.data.converter.io.reader.impl;

import kozitski.data.converter.dto.DestinationDTO;
import kozitski.data.converter.io.parser.impl.DestinationParser;
import kozitski.data.converter.io.reader.AbstractCsvReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * The type Destination csv reader.
 */
@Component
public class DestinationCsvReader extends AbstractCsvReader<DestinationDTO> {

    private DestinationParser destinationParser;

    /**
     * Sets destination parser.
     *
     * @param destinationParser the destination parser
     */
    @Autowired
    public void setDestinationParser(DestinationParser destinationParser) {
        this.destinationParser = destinationParser;
    }

    @Override
    public DestinationDTO parse(String line) {
        return destinationParser.parseLine(line);
    }

}
