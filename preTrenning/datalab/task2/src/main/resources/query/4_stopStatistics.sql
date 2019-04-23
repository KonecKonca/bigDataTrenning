WITH
  allGroups AS (
    SELECT
      to_char(s.datetime, 'YYYY-MM') AS month,
      s.officer_defined_ethnicity    AS ethnicity,
      count(s.stop_id) AS totalOutcomeCount
    FROM stops s
    GROUP BY month, ethnicity
  ),
  arrestOutcome AS (
    SELECT
      to_char(s.datetime, 'YYYY-MM') AS month,
      s.officer_defined_ethnicity AS ethnicity,
      count(s.stop_id) AS withArrestOutcomeCount
    FROM stops s
    WHERE s.outcome='Arrest'
    GROUP BY month, ethnicity
  ),
  noFutherOutcome AS
    (
      SELECT
        to_char(s.datetime, 'YYYY-MM') AS month,
        s.officer_defined_ethnicity AS ethnicity,
        count(s.stop_id) AS withNoFatherOutcomeCount
      FROM stops s
      WHERE s.outcome='A no further action disposal'
      GROUP BY month, ethnicity
    ),
  otherOutcome AS (
    SELECT
      to_char(s.datetime, 'YYYY-MM') AS month,
      s.officer_defined_ethnicity AS ethnicity,
      count(s.stop_id) AS otherOutcomeCount
    FROM stops s
    WHERE s.outcome !='A no further action disposal' AND s.outcome !='Arrest'
    GROUP BY month, ethnicity
  ),
  maxRates AS (SELECT
                 sub.month,
                 sub.ethnicity,
                 max(count) maxCount
               FROM (
                      SELECT
                        to_char(s.datetime, 'YYYY-MM') AS month,
                        s.officer_defined_ethnicity AS ethnicity,
                        s.object_of_search AS popularOblectOfSearch,
                        count(s.object_of_search) count
                      FROM stops s
                      WHERE object_of_search NOTNULL AND officer_defined_ethnicity NOTNULL
                      GROUP BY month, ethnicity, popularOblectOfSearch
                    ) AS sub
               GROUP BY sub.month, sub.ethnicity
  ),
  allRates AS (SELECT
                 to_char(s.datetime, 'YYYY-MM') AS month,
                 s.officer_defined_ethnicity AS ethnicity,
                 s.object_of_search AS popularOblectOfSearch,
                 count(s.object_of_search) count
               FROM stops s
               WHERE object_of_search NOTNULL AND officer_defined_ethnicity NOTNULL
               GROUP BY month, ethnicity, popularOblectOfSearch
  ),
  mostPopularObjects AS (
    SELECT
      allRates.month,
      allRates.ethnicity,
      allRates.popularOblectOfSearch
    FROM maxRates
           INNER JOIN allRates
                      ON allRates.month = maxRates.month AND allRates.ethnicity = maxRates.ethnicity
    WHERE allRates.count = maxRates.maxCount
  )

SELECT
  all1.ethnicity AS officerDefinedEthnicity,
  all1.month AS month,
  all1.totalOutcomeCount AS totalNumberOccurrences,
  a1.withArrestOutcomeCount AS rateWithOutcomeArrest,
  nf1.withNoFatherOutcomeCount AS rateWithoutFurtherActionDisposal,
  ot1.otherOutcomeCount AS rateOtherOutcomes,
  pos1.popularOblectOfSearch AS popularObjectOfSearch
FROM allGroups AS all1
       FULL OUTER JOIN arrestOutcome AS a1 ON a1.ethnicity = all1.ethnicity AND all1.month = a1.month
       FULL OUTER JOIN noFutherOutcome AS nf1 ON all1.ethnicity = nf1.ethnicity AND all1.month = nf1.month
       FULL OUTER JOIN otherOutcome AS ot1 ON all1.ethnicity = ot1.ethnicity AND all1.month = ot1.month
       INNER JOIN mostPopularObjects AS pos1 ON all1.ethnicity = pos1.ethnicity AND all1.month = pos1.month
WHERE all1.ethnicity NOTNULL AND all1.month >= ? AND all1.month <= ?;