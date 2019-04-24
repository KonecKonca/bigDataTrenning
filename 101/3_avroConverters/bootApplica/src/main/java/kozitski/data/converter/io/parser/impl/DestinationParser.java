package kozitski.data.converter.io.parser.impl;

import kozitski.data.converter.dto.DestinationDTO;
import kozitski.data.converter.io.parser.InputLineParser;
import kozitski.data.converter.io.util.ConvertType;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * The type Destination parser.
 */
@Component
public class DestinationParser implements InputLineParser<DestinationDTO> {
    private static final int D_FIELD_NUMBER = 160;
    @Setter @Getter
    private String delimetr = ",";

    @Override
    public DestinationDTO parseLine(String line) {
        DestinationDTO result = null;

        if(line != null && !line.isEmpty()){
            String[] fields = line.split(delimetr);

            result = new DestinationDTO();

            result.setSrchDestinationId(ConvertType.intWithNulParse(fields[NumberUtils.INTEGER_ZERO]));

            List<Optional<Double>> doubles = new ArrayList<>(D_FIELD_NUMBER);
            for (int i = NumberUtils.INTEGER_ONE; i < fields.length; i++){
                doubles.add(ConvertType.doubleWithNulParse(fields[i]));
            }

            result.setD(doubles);

        }

        return result;
    }


}
