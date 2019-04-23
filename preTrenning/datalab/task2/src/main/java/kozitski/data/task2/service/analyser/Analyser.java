package kozitski.data.task2.service.analyser;

import kozitski.data.task2.domain.analyse.*;

import java.util.List;

public interface Analyser {

    List<AnalisedStreet> performAnalisedStreets(long fromMonth, long toMonth);

    List<CrimeVolume> performCrimeVolume(long fromMonth, long toMonth);

    List<CrimeWithOutcomeStatus> performCrimeWithOutcomeStatus(long fromMonth, long toMonth, String outcomeCategory);

    List<StatisticByEthnicity> performStatisticByEthnity(String fromMonth, String toMonth);

    List<SnapshotOfStreetLevel> performShapshotOfStreetLevel(String fromMonth, String toMonth);

    List<StopAndSearchCorrelation> performStopAndSearchCorrelation(String fromMonth, String toMonth);

}
