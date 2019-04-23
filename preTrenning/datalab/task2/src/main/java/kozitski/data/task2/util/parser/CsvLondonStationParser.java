package kozitski.data.task2.util.parser;

import com.opencsv.CSVReader;
import kozitski.data.task2.domain.LondonStation;
import kozitski.data.task2.util.CommonConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class CsvLondonStationParser implements LondonStationParser {

    @Override
    public List<LondonStation> parseLondonStation(String inputFilePath) {
        List<LondonStation> list = null;

        File file = new File(System.getProperty("user.dir") + File.separator +  inputFilePath);
        if(!file.exists()){
            file = new File(System.getProperty("user.dir") + File.separator + CommonConstant.CSV_FILE_PATH);
            list = read(file);
        }
        else {
            list = read(file);
        }

        return list;
    }

    private List<LondonStation> read(File file){
        ArrayList<LondonStation> list = new ArrayList<>();

        try (FileReader fileReader = new FileReader(file);
                CSVReader csvReader = new CSVReader(fileReader)){

            csvReader.readNext();
            csvReader.forEach(lines -> {
                LondonStation londonStation = LondonStation
                        .builder()
                        .name(lines[0])
                        .longitude(Double.parseDouble(lines[1]))
                        .latitude(Double.parseDouble(lines[2]))
                        .build();

                list.add(londonStation);
            });
        } catch (IOException e) {
            log.error("Can not parse CSV file", e);
        }

        return list;
    }

}
