package kozitski.data.task2.util.parser;

import kozitski.data.task2.domain.LondonStation;

import java.util.List;

public interface LondonStationParser {

    List<LondonStation> parseLondonStation(String inputFilePath);

}
