package kozitski.data.converter.io.reader.impl;

import kozitski.data.converter.dto.SampleSubmissionDTO;
import kozitski.data.converter.io.parser.impl.SampleSubmissionParser;
import kozitski.data.converter.io.reader.AbstractCsvReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * The type Sample submission csv reader.
 */
@Component
public class SampleSubmissionCsvReader extends AbstractCsvReader<SampleSubmissionDTO> {

    private SampleSubmissionParser sampleSubmissionParser;

    /**
     * Sets sample submission parser.
     *
     * @param sampleSubmissionParser the sample submission parser
     */
    @Autowired
    public void setSampleSubmissionParser(SampleSubmissionParser sampleSubmissionParser) {
        this.sampleSubmissionParser = sampleSubmissionParser;
    }

    @Override
    public SampleSubmissionDTO parse(String line) {
        return sampleSubmissionParser.parseLine(line);
    }


}
