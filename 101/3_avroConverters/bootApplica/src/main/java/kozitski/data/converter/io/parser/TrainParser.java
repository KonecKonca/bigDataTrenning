package kozitski.data.converter.io.parser;

import kozitski.data.converter.dto.TrainDTO;
import kozitski.data.converter.io.util.ConvertType;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class TrainParser implements InputLineParser<TrainDTO> {
    @Setter
    @Getter
    private String delimetr = ",";

    @Override
    public TrainDTO parseLine(String line) {
        TrainDTO result = null;

        if(line != null && !line.isEmpty()){
            String[] fields = line.split(delimetr);

            result = TrainDTO.builder()
                    .dateTime(ConvertType.stringWithNulParse(fields[0]))
                    .siteName(ConvertType.intWithNulParse(fields[1]))
                    .posaContinent(ConvertType.intWithNulParse(fields[2]))
                    .userLocationCountry(ConvertType.intWithNulParse(fields[3]))
                    .userLocationRegion(ConvertType.intWithNulParse(fields[4]))
                    .userLocationCity(ConvertType.intWithNulParse(fields[5]))
                    .origDestinationDistance(ConvertType.doubleWithNulParse(fields[6]))
                    .userId(ConvertType.intWithNulParse(fields[7]))
                    .isMobile(ConvertType.intWithNulParse(fields[8]))
                    .isPackage(ConvertType.intWithNulParse(fields[9]))
                    .channel(ConvertType.intWithNulParse(fields[10]))
                    .srchCi(ConvertType.stringWithNulParse(fields[11]))
                    .srchCo(ConvertType.stringWithNulParse(fields[12]))
                    .srchAdultsCnt(ConvertType.intWithNulParse(fields[13]))
                    .srchChildrenCnt(ConvertType.intWithNulParse(fields[14]))
                    .srchRmCnt(ConvertType.stringWithNulParse(fields[15]))
                    .srchDestinationId(ConvertType.intWithNulParse(fields[16]))
                    .srchDestinationTypeId(ConvertType.intWithNulParse(fields[17]))

                    .isBooking(ConvertType.intWithNulParse(fields[18]))
                    .cnt(ConvertType.longWithNulParse(fields[19]))

                    .hotelContinent(ConvertType.intWithNulParse(fields[20]))
                    .hotelCountry(ConvertType.intWithNulParse(fields[21]))
                    .hotelMarket(ConvertType.intWithNulParse(fields[22]))

                    .hotelCluster(ConvertType.intWithNulParse(fields[23]))

                    .build();
        }

        return result;
    }


}
