package kozitski.data.converter.io.parser.impl;

import kozitski.data.converter.dto.TestDTO;
import kozitski.data.converter.io.parser.InputLineParser;
import kozitski.data.converter.io.util.ConvertType;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * The type Test parser.
 */
@Component
@Slf4j
public class TestParser implements InputLineParser<TestDTO> {
    @Setter @Getter
    private String delimetr = ",";

    @Override
    public TestDTO parseLine(String line) {
        TestDTO result = null;

        if(line != null && !line.isEmpty()){
            String[] fields = line.split(delimetr);

            /*
            * MAGIC NUMBERS due to dedined order of fields in trhe input file
            */
            result = TestDTO.builder()
                    .id(ConvertType.stringWithNulParse(fields[0]))
                    .dateTime(ConvertType.stringWithNulParse(fields[1]))
                    .siteName(ConvertType.intWithNulParse(fields[2]))
                    .posaContinent(ConvertType.intWithNulParse(fields[3]))
                    .userLocationCountry(ConvertType.intWithNulParse(fields[4]))
                    .userLocationRegion(ConvertType.intWithNulParse(fields[5]))
                    .userLocationCity(ConvertType.intWithNulParse(fields[6]))
                    .origDestinationDistance(ConvertType.doubleWithNulParse(fields[7]))
                    .userId(ConvertType.intWithNulParse(fields[8]))
                    .isMobile(ConvertType.intWithNulParse(fields[9]))
                    .isPackage(ConvertType.intWithNulParse(fields[10]))
                    .channel(ConvertType.intWithNulParse(fields[11]))
                    .srchCi(ConvertType.stringWithNulParse(fields[12]))
                    .srchCo(ConvertType.stringWithNulParse(fields[13]))
                    .srchAdultsCnt(ConvertType.intWithNulParse(fields[14]))
                    .srchChildrenCnt(ConvertType.intWithNulParse(fields[15]))
                    .srchRmCnt(ConvertType.stringWithNulParse(fields[16]))
                    .srchDestinationId(ConvertType.intWithNulParse(fields[17]))
                    .srchDestinationTypeId(ConvertType.intWithNulParse(fields[18]))
                    .hotelContinent(ConvertType.intWithNulParse(fields[19]))
                    .hotelCountry(ConvertType.intWithNulParse(fields[20]))
                    .hotelMarket(ConvertType.intWithNulParse(fields[21]))
                    .build();
        }

        return result;
    }
    
    
}
