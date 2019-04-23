package com.epam.kozitski.runner;

import com.epam.kozitski.domain.Crime;
import com.epam.kozitski.domain.LondonStation;
import com.epam.kozitski.service.CrimeService;
import com.epam.kozitski.util.CommonConstant;
import com.epam.kozitski.util.ProgressBar;
import com.epam.kozitski.util.SaveType;
import com.epam.kozitski.util.parser.LondonStationParser;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URISyntaxException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
@Slf4j
public class ArgHandler {
    private static final String DEFAULT_DATE = "666-666";
    private static final String MONTH_PATTERN = "\\d{4}-\\d{2}";

    private boolean isLoaded = false;
    private boolean isReceived = false;
    private boolean isHelp = false;
    private boolean isClear = false;
    private String saveType = "";

    private CrimeService crimeService;
    @Autowired
    public void setCrimeService(CrimeService crimeService) {
        this.crimeService = crimeService;
    }

    private LondonStationParser londonStationParser;
    @Autowired
    public void setLondonStationParser(LondonStationParser londonStationParser) {
        this.londonStationParser = londonStationParser;
    }

    private ProgressBar progressBar;
    @Autowired
    public void setProgressBar(ProgressBar progressBar) {
        this.progressBar = progressBar;
    }

    private List<LondonStation> londonStations;
    private Set<Crime> allCrimes;

    public ArgHandler() {
        allCrimes = new HashSet<>();
    }

    public void iterate(List<Option> options, Options outputOptions){

        options.forEach(option -> {
            String value = option.getValue();
            String shortName = option.getOpt();
            String fullName = option.getLongOpt();

            if(ArgType.LOAD.getShortName().equalsIgnoreCase(shortName) || ArgType.LOAD.getFullName().equalsIgnoreCase(fullName)){
                loadInputData(value);
                isLoaded = true;
            }

            // TODO: temporary for speed
            if(ArgType.GET.getShortName().equalsIgnoreCase(shortName) || ArgType.GET.getFullName().equalsIgnoreCase(fullName)){
                apiIterate(value);
                isReceived = true;
            }

            if(ArgType.SAVE.getShortName().equalsIgnoreCase(shortName) || ArgType.SAVE.getFullName().equalsIgnoreCase(fullName)){
                saveType = value;
            }

            if(ArgType.HELP.getShortName().equalsIgnoreCase(shortName) || ArgType.HELP.getFullName().equalsIgnoreCase(fullName)){
                showHelpMessage(outputOptions);
                isHelp = true;
            }

            if(ArgType.CLEAR_DB.getShortName().equalsIgnoreCase(shortName) || ArgType.CLEAR_DB.getFullName().equalsIgnoreCase(fullName)){
                crimeService.clearDb();
                isClear = true;
            }

            if(ArgType.ALL.getShortName().equalsIgnoreCase(shortName) || ArgType.ALL.getFullName().equalsIgnoreCase(fullName)){
                allActions(option);
            }

        });

        if (isHelp || isClear){
            return;
        }
        if(!isLoaded){
            loadInputData(CommonConstant.CSV_FILE_PATH);
        }
        if(!isReceived){
            apiIterate(DEFAULT_DATE);
        }

        saveResultOfSelection(saveType);
    }

    private void allActions(Option option){
        if(londonStations == null || londonStations.isEmpty()){
            loadInputData(option.getValue());
        }

        if(allCrimes == null || allCrimes.isEmpty()){
            apiIterate(option.getValue());
        }

        saveResultOfSelection(option.getValue());

    }
    private void loadInputData(String value) {
        londonStations = londonStationParser.parseLondonStation(value);
    }
    private void apiIterate(String value) {
//        londonStations.forEach(station -> {
//            getDataFromApi(String.valueOf(station.getLatitude()), String.valueOf(station.getLongitude()), value);
//            progressBar.iterationLog(allCrimes.size(), station.getLatitude(), station.getLongitude());
//        });

        for (int i = 0; i < 7; i++){
            getDataFromApi(String.valueOf(londonStations.get(i).getLatitude()), String.valueOf(londonStations.get(i).getLongitude()), value);
            progressBar.iterationLog(londonStations.size(),
                    String.valueOf(londonStations.get(i).getLatitude()), String.valueOf(londonStations.get(i).getLongitude()));
        }
    }
    private void getDataFromApi(String latitude, String longitude,  String value) {
        if(value!=null && value.matches(MONTH_PATTERN)){
            try {
                allCrimes.addAll(crimeService.getAllCrimesByDate(latitude, longitude, value));
            }
            catch (URISyntaxException e) {
                log.error("Crimes was't received");
            }
        }
        else {
            try {
                allCrimes.addAll(crimeService.getAllCrimes(latitude, longitude));
            }
            catch (URISyntaxException e) {
                log.error("Crimes was't received");
            }
        }
    }
    private void saveResultOfSelection(String value) {

        if(value.equalsIgnoreCase(SaveType.DB.name())){
            crimeService.updateDb(allCrimes);
        }
        else if(value.equalsIgnoreCase(SaveType.TXT.name())){
            crimeService.getTxtCrimesList(allCrimes, CommonConstant.OUTPUT_FILE_NAME);
        }
        else {
            crimeService.getTxtCrimesList(allCrimes, CommonConstant.OUTPUT_FILE_NAME);
        }
    }
    private void showHelpMessage(Options outputOptions) {
        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp("CommandLine parameters", outputOptions);
    }

}
