package kozitski.data.task2.runner;

import kozitski.data.task2.domain.analyse.*;
import kozitski.data.task2.domain.dto.input.DateRange;
import kozitski.data.task2.domain.dto.input.OutcomeWithDate;
import kozitski.data.task2.domain.dto.input.StringDataRange;
import kozitski.data.task2.service.analyser.ServiceDbAnalyser;
import kozitski.data.task2.util.CommonConstant;
import kozitski.data.task2.util.FileOutputWriter;
import kozitski.data.task2.util.input.MonthTransformer;
import kozitski.data.task2.util.input.DayTransformer;
import kozitski.data.task2.util.input.OutcomeTransformer;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class SelectArgHandler {

    private ServiceDbAnalyser serviceDbAnalyser;
    private FileOutputWriter fileOutputWriter;

    private MonthTransformer monthTransformer;
    private DayTransformer dateTransformer;
    private OutcomeTransformer outcomeTransformer;

    @Autowired
    public void setServiceDbAnalyser(ServiceDbAnalyser serviceDbAnalyser) {
        this.serviceDbAnalyser = serviceDbAnalyser;
    }

    @Autowired
    public void setMonthTransformer(MonthTransformer monthTransformer) {
        this.monthTransformer = monthTransformer;
    }

    @Autowired
    public void setDateTransformer(DayTransformer dateTransformer) {
        this.dateTransformer = dateTransformer;
    }

    @Autowired
    public void setFileOutputWriter(FileOutputWriter fileOutputWriter) {
        this.fileOutputWriter = fileOutputWriter;
    }

    @Autowired
    public void setOutcomeTransformer(OutcomeTransformer outcomeTransformer) {
        this.outcomeTransformer = outcomeTransformer;
    }

    public boolean handleTerminated(String shortName, String fullName, String argument){
        boolean isTerminated = false;

        if(ArgType.SEARCH_MOST_DANGEROUS.getShortName().equalsIgnoreCase(shortName)
                || ArgType.SEARCH_MOST_DANGEROUS.getFullName().equalsIgnoreCase(fullName)){
            DateRange dateRange = monthTransformer.toDateRangeDefaultFormat(argument);

            List<AnalisedStreet> analisedStreets = serviceDbAnalyser.performAnalisedStreets(dateRange.getFromDate(), dateRange.getToDate());
            fileOutputWriter.writeOutput(analisedStreets, CommonConstant.OUTPUT_ANALYZE1 + CommonConstant.OUTPUT_SEPARATOR + argument);

            isTerminated = true;
        }

        if(ArgType.SEARCH_AVERAGE_CRIME_VOLUME.getShortName().equalsIgnoreCase(shortName)
                || ArgType.SEARCH_AVERAGE_CRIME_VOLUME.getFullName().equalsIgnoreCase(fullName)){
            DateRange dateRange = monthTransformer.toDateRangeDefaultFormat(argument);

            List<CrimeVolume> crimeVolumes = serviceDbAnalyser.performCrimeVolume(dateRange.getFromDate(), dateRange.getToDate());
            fileOutputWriter.writeOutput(crimeVolumes, CommonConstant.OUTPUT_ANALYZE2 + CommonConstant.OUTPUT_SEPARATOR + argument);

            isTerminated = true;
        }

        if(ArgType.SEARCH_CRIME_BY_OUTCOME_STATUS.getShortName().equalsIgnoreCase(shortName)
                || ArgType.SEARCH_CRIME_BY_OUTCOME_STATUS.getFullName().equalsIgnoreCase(fullName)){
            OutcomeWithDate outcomeWithDate = outcomeTransformer.toDateRangeDefaultFormat(argument);

            List<CrimeWithOutcomeStatus> crimeWithOutcomeStatuses = serviceDbAnalyser.performCrimeWithOutcomeStatus(
                    outcomeWithDate.getFromDate(), outcomeWithDate.getToDate(), outcomeWithDate.getOutcome());
            crimeWithOutcomeStatuses.forEach(e -> e.setOutcomeCategory(outcomeWithDate.getOutcome()));
            fileOutputWriter.writeOutput(crimeWithOutcomeStatuses, CommonConstant.OUTPUT_ANALYZE3 + CommonConstant.OUTPUT_SEPARATOR + argument);

            isTerminated = true;
        }

        if(ArgType.SEARCH_STOP_STATISTIC.getShortName().equalsIgnoreCase(shortName)
                || ArgType.SEARCH_STOP_STATISTIC.getFullName().equalsIgnoreCase(fullName)){
            String[] dates = argument.split(CommonConstant.DATE_SEPARATOR);

            List<StatisticByEthnicity> statisticByEthnicities = serviceDbAnalyser.performStatisticByEthnity(
                    dates[NumberUtils.BYTE_ZERO].trim(), dates[NumberUtils.BYTE_ONE].trim());
            fileOutputWriter.writeOutput(statisticByEthnicities, CommonConstant.OUTPUT_ANALYZE4 + CommonConstant.OUTPUT_SEPARATOR + argument);

            isTerminated = true;
        }

        if(ArgType.SEARCH_STREET_LEVEL_SHAPSHOT.getShortName().equalsIgnoreCase(shortName)
                || ArgType.SEARCH_STREET_LEVEL_SHAPSHOT.getFullName().equalsIgnoreCase(fullName)){
            StringDataRange dateRange = dateTransformer.toDateRangeDefaultFormat(argument);

            List<SnapshotOfStreetLevel> snapshotOfStreetLevel =
                    serviceDbAnalyser.performShapshotOfStreetLevel(dateRange.getFromDate(), dateRange.getToDate());
            fileOutputWriter.writeOutput(snapshotOfStreetLevel,
                    CommonConstant.OUTPUT_ANALYZE5 + CommonConstant.OUTPUT_SEPARATOR + argument);

            isTerminated = true;
        }

        if(ArgType.SEARCH_STOP_AND_CRIME_CORRELATION.getShortName().equalsIgnoreCase(shortName)
                || ArgType.SEARCH_STOP_AND_CRIME_CORRELATION.getFullName().equalsIgnoreCase(fullName)){
            String[] dates = argument.split(CommonConstant.DATE_SEPARATOR);

            List<StopAndSearchCorrelation> stopAndSearchCorrelations =
                    serviceDbAnalyser.performStopAndSearchCorrelation(dates[NumberUtils.BYTE_ZERO].trim(), dates[NumberUtils.BYTE_ONE].trim());

            fileOutputWriter.writeOutput(stopAndSearchCorrelations,
                    CommonConstant.OUTPUT_ANALYZE6 + CommonConstant.OUTPUT_SEPARATOR + argument);

            isTerminated = true;
        }

        return isTerminated;
    }

}
