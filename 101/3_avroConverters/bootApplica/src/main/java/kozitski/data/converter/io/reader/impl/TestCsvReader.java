package kozitski.data.converter.io.reader.impl;

import kozitski.data.converter.dto.TestDTO;
import kozitski.data.converter.io.parser.impl.TestParser;
import kozitski.data.converter.io.reader.AbstractCsvReader;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * The type Test csv reader.
 */
@Component
public class TestCsvReader extends AbstractCsvReader<TestDTO> {

    private TestParser testParser;

    /**
     * Sets test parser.
     *
     * @param testParser the test parser
     */
    @Autowired
    public void setTestParser(TestParser testParser) {
        this.testParser = testParser;
    }

    @Override
    public TestDTO parse(String line) {
        return testParser.parseLine(line);
    }

}
