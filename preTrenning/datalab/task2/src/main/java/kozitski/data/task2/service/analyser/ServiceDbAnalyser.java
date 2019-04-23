package kozitski.data.task2.service.analyser;

import kozitski.data.task2.dao.AnalyserDao;
import kozitski.data.task2.domain.analyse.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class ServiceDbAnalyser implements Analyser{

    private AnalyserDao analyserDao;

    @Autowired
    public void setAnalyserDao(AnalyserDao analyserDao) {
        this.analyserDao = analyserDao;
    }

    @Override
    public List<AnalisedStreet> performAnalisedStreets(long fromMonth, long toMonth) {
        List<AnalisedStreet> analisedStreets = new ArrayList<>();

        try {
            analisedStreets = analyserDao.searchMostDangerousStreet(fromMonth, toMonth);
        }
        catch (EmptyResultDataAccessException e){
            log.warn("Were not founding any records", e);
        }

        return analisedStreets;
    }

    @Override
    public List<CrimeVolume> performCrimeVolume(long fromMonth, long toMonth) {
        List<CrimeVolume> crimeVolumes = new ArrayList<>();

        try {
            crimeVolumes = analyserDao.searchAverageCrimeVolume(fromMonth, toMonth);
        }
        catch (EmptyResultDataAccessException e) {
            log.warn("Were not founding any records", e);
        }

        return crimeVolumes;
    }

    @Override
    public List<CrimeWithOutcomeStatus> performCrimeWithOutcomeStatus(long fromMonth, long toMonth, String outcomeCategory) {
        List<CrimeWithOutcomeStatus> crimeWithOutcomeStatuses = new ArrayList<>();

        try {
            crimeWithOutcomeStatuses = analyserDao.searchCrimesByOutcomeStatus(fromMonth, toMonth, outcomeCategory);
        }
        catch (EmptyResultDataAccessException e) {
            log.warn("Were not founding any records", e);
        }

        return crimeWithOutcomeStatuses;
    }

    @Override
    public List<StatisticByEthnicity> performStatisticByEthnity(String fromMonth, String toMonth) {
        List<StatisticByEthnicity> statisticByEthnicity = new ArrayList<>();

        try {
            statisticByEthnicity.addAll(analyserDao.searchStatisticByEthnicity(fromMonth, toMonth));
        }
        catch (EmptyResultDataAccessException e){
            log.warn("Were not founding any records", e);
        }

        return statisticByEthnicity;
    }

    @Override
    public List<SnapshotOfStreetLevel> performShapshotOfStreetLevel(String fromMonth, String toMonth) {
        List<SnapshotOfStreetLevel> snapshot = new ArrayList<>();

        try {
            snapshot.addAll(analyserDao.generateSnapshot(fromMonth, toMonth));
        }
        catch (EmptyResultDataAccessException e){
            log.warn("Were not founding any records", e);
        }

        return snapshot;
    }

    @Override
    public List<StopAndSearchCorrelation> performStopAndSearchCorrelation(String fromMonth, String toMonth) {
        List<StopAndSearchCorrelation> stopAndSearchCorrelations = new ArrayList<>();

        try {
            stopAndSearchCorrelations = analyserDao.searchStopAndSearchCorrelations(fromMonth, toMonth);
        }
        catch (EmptyResultDataAccessException e){
            log.warn("Were not founding any records", e);
        }

        return stopAndSearchCorrelations;
    }

}
