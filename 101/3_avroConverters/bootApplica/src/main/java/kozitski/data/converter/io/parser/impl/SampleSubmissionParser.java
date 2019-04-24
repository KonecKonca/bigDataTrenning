package kozitski.data.converter.io.parser.impl;

import kozitski.data.converter.dto.SampleSubmissionDTO;
import kozitski.data.converter.io.parser.InputLineParser;
import kozitski.data.converter.io.util.ConvertType;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.stereotype.Component;

/**
 * The type Sample submission parser.
 */
@Component
public class SampleSubmissionParser implements InputLineParser<SampleSubmissionDTO> {
    @Setter
    @Getter
    private String delimetr = ",";

    @Override
    public SampleSubmissionDTO parseLine(String line) {
        SampleSubmissionDTO result = null;

        if(line != null && !line.isEmpty()){
            String[] fields = line.split(delimetr);

            result = new SampleSubmissionDTO();
            result.setId(ConvertType.intWithNulParse(fields[NumberUtils.INTEGER_ZERO]));
            result.setHotelCluster(ConvertType.stringWithNulParse(fields[NumberUtils.INTEGER_ONE]));

        }

        return result;
    }


}
