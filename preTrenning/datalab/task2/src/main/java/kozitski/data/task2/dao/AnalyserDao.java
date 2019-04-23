package kozitski.data.task2.dao;

import kozitski.data.task2.domain.analyse.*;
import kozitski.data.task2.util.SqlLoader;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Slf4j
public class AnalyserDao {

    @Value("classpath:query/1_mostDangerousStreets.sql")
    private Resource mostDangerousStreetsSql;

    @Value("classpath:query/2_averageCrimesVolume.sql")
    private Resource averageCrimesVolumeSql;

    @Value("classpath:query/3_crimeByOutcomeStatus.sql")
    private Resource crimeByOutcomeStatusSql;

    @Value("classpath:query/4_stopStatistics.sql")
    private Resource stopStatisticsSql;

    @Value("classpath:query/5_snapshotStreetLevel.sql")
    private Resource snapshotStreetLevelSql;

    @Value("classpath:query/6_stopAndCrimeCorrelation.sql")
    private Resource stopAndCrimeCorrelationSql;

    private SqlLoader sqlLoader;
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setSqlLoader(SqlLoader sqlLoader) {
        this.sqlLoader = sqlLoader;
    }

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<AnalisedStreet> searchMostDangerousStreet(long fromMonth, long toMonth){
        return jdbcTemplate.query(
                sqlLoader.returnSql(mostDangerousStreetsSql),
                new BeanPropertyRowMapper<>(AnalisedStreet.class),
                fromMonth, toMonth);
    }

    public List<CrimeVolume> searchAverageCrimeVolume(long fromMonth, long toMonth){
        return jdbcTemplate.query(
                sqlLoader.returnSql(averageCrimesVolumeSql),
                new BeanPropertyRowMapper<>(CrimeVolume.class),
                fromMonth, toMonth);
    }

    public List<CrimeWithOutcomeStatus> searchCrimesByOutcomeStatus(long fromMonth, long toMonth, String outcomeStatus){
        return jdbcTemplate.query(
                sqlLoader.returnSql(crimeByOutcomeStatusSql),
                new BeanPropertyRowMapper<>(CrimeWithOutcomeStatus.class),
                fromMonth, toMonth, outcomeStatus);
    }

    public List<StatisticByEthnicity> searchStatisticByEthnicity(String fromMonth, String toMonth){
        return jdbcTemplate.query(
                sqlLoader.returnSql(stopStatisticsSql),
                new BeanPropertyRowMapper<>(StatisticByEthnicity.class),
                fromMonth, toMonth
                );
    }

    public List<SnapshotOfStreetLevel> generateSnapshot(String fromMonth, String toMonth){
        return jdbcTemplate.query(
                sqlLoader.returnSql(snapshotStreetLevelSql),
                new BeanPropertyRowMapper<>(SnapshotOfStreetLevel.class),
                fromMonth,  toMonth);
    }

    public List<StopAndSearchCorrelation> searchStopAndSearchCorrelations(String fromMonth, String toMonth){
        return jdbcTemplate.query(
                sqlLoader.returnSql(stopAndCrimeCorrelationSql),
                new BeanPropertyRowMapper<>(StopAndSearchCorrelation.class),
                fromMonth, toMonth);
    }

}
