package kozitski.data.task2.runner;

import kozitski.data.task2.domain.Crime;
import kozitski.data.task2.domain.LondonStation;
import kozitski.data.task2.domain.Stop;
import kozitski.data.task2.service.crime.CrimeService;
import kozitski.data.task2.service.stop.StopsService;
import kozitski.data.task2.util.CommonConstant;
import kozitski.data.task2.util.FileOutputWriter;
import kozitski.data.task2.util.progress.ApiProgressBar;
import kozitski.data.task2.util.SaveType;
import kozitski.data.task2.util.parser.LondonStationParser;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
@Slf4j
public class CommonArgHandler {
    private static final String DEFAULT_DATE = "$$$";
    private static final String MONTH_PATTERN = "\\d{4}-\\d{2}";

    private boolean isLoaded = false;
    private boolean isReceived = false;
    private boolean isHelp = false;
    private boolean isClear = false;
    private boolean isTerminated = false;
    private String saveType = "";

    private CrimeService crimeService;
    private StopsService stopsService;
    private LondonStationParser londonStationParser;
    private ApiProgressBar apiProgressBar;
    private FileOutputWriter writer;
    private SelectArgHandler selectArgHandler;

    private List<LondonStation> londonStations;
    private Set<Crime> allCrimes;
    private Set<Stop> allStops;

    @Autowired
    public void setCrimeService(CrimeService crimeService) {
        this.crimeService = crimeService;
    }

    @Autowired
    public void setStopsService(StopsService stopsService) {
        this.stopsService = stopsService;
    }

    @Autowired
    public void setLondonStationParser(LondonStationParser londonStationParser) {
        this.londonStationParser = londonStationParser;
    }

    @Autowired
    public void setApiProgressBar(ApiProgressBar apiProgressBar) {
        this.apiProgressBar = apiProgressBar;
    }

    @Autowired
    public void setWriter(FileOutputWriter writer) {
        this.writer = writer;
    }

    @Autowired
    public void setSelectArgHandler(SelectArgHandler selectArgHandler) {
        this.selectArgHandler = selectArgHandler;
    }

    public CommonArgHandler() {
        allCrimes = new HashSet<>();
        allStops = new HashSet<>();
    }

    public void iterate(List<Option> options, Options outputOptions){

        options.forEach(option -> {
            String argument = option.getValue();
            String shortName = option.getOpt();
            String fullName = option.getLongOpt();

            if(ArgType.LOAD.getShortName().equalsIgnoreCase(shortName) || ArgType.LOAD.getFullName().equalsIgnoreCase(fullName)){
                loadInputData(argument);
                isLoaded = true;
            }

            if(ArgType.GET.getShortName().equalsIgnoreCase(shortName) || ArgType.GET.getFullName().equalsIgnoreCase(fullName)){
                apiIterate(argument);
                isReceived = true;
            }

            if(ArgType.SAVE.getShortName().equalsIgnoreCase(shortName) || ArgType.SAVE.getFullName().equalsIgnoreCase(fullName)){
                saveType = argument;
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

            isTerminated = selectArgHandler.handleTerminated(shortName, fullName, argument);

        });

        if (isHelp || isClear || isTerminated){
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

        if((allCrimes == null || allCrimes.isEmpty()) && (allStops == null || allStops.isEmpty())){
            apiIterate(option.getValue());
        }

        saveResultOfSelection(option.getValue());

    }
    private void loadInputData(String value) {

        londonStations = londonStationParser.parseLondonStation(value);
        apiProgressBar.setTotalNumber(londonStations.size());
    }
    private void apiIterate(String value) {
        londonStations.forEach(station -> {
            getDataFromApi(String.valueOf(station.getLatitude()), String.valueOf(station.getLongitude()), value);
            apiProgressBar.iterationLog(String.valueOf(station.getLatitude()), String.valueOf(station.getLongitude()));
        });
    }
    private void getDataFromApi(String latitude, String longitude,  String value) {
        if(value!=null && value.matches(MONTH_PATTERN)){
            allCrimes.addAll(crimeService.getAllCrimesByDate(latitude, longitude, value));
            allStops.addAll(stopsService.getAllStopsByDate(latitude, longitude, value));
        }
        else {
            allCrimes.addAll(crimeService.getAllCrimes(latitude, longitude));
            allStops.addAll(stopsService.getAllStops(latitude, longitude));
        }
    }
    private void saveResultOfSelection(String value) {

        if(value.equalsIgnoreCase(SaveType.DB.name())){
            crimeService.updateDb(allCrimes);
            stopsService.updateDb(allStops);
        }
        else {
            writer.writeOutput(allCrimes, CommonConstant.OUTPUT_CRIMES_NAME);
            writer.writeOutput(allStops, CommonConstant.OUTPUT_STOPS_NAME);
        }
    }
    private void showHelpMessage(Options outputOptions) {
        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp("CommandLine parameters", outputOptions);
    }

}
