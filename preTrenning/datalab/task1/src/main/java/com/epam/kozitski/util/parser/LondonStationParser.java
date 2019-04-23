package com.epam.kozitski.util.parser;

import com.epam.kozitski.domain.LondonStation;

import java.util.List;

public interface LondonStationParser {

    List<LondonStation> parseLondonStation(String inputFilePath);

}
