WITH allStreetsAndDates AS(
    SELECT
     s.street_id,
     s.name,
     to_char(to_timestamp(c.month/1000), 'YYYY-MM') AS month
    FROM crimes c INNER JOIN locations l on c.location_id = l.location_id
                INNER JOIN streets s on l.street_id = s.street_id
    GROUP BY s.street_id, s.name, month
  UNION
    SELECT
      str.street_id,
      str.name,
      to_char(s.datetime, 'YYYY-MM') AS month
    FROM stops s INNER JOIN locations l on s.location_id = l.location_id
                 INNER JOIN streets str on l.street_id = str.street_id
    GROUP BY str.street_id, str.name, month
),
crimeDrugs AS (SELECT
  s.street_id,
  s.name,
  to_char(to_timestamp(c.month/1000), 'YYYY-MM') AS month,
  count(c.crime_id) OVER(PARTITION BY c.category, s.street_id) AS crimesDrugCount
FROM crimes c INNER JOIN locations l on c.location_id = l.location_id
              INNER JOIN streets s on l.street_id = s.street_id
WHERE c.category='drugs'
),
stopDrugs AS (SELECT
  str.street_id,
  str.name,
  to_char(s.datetime, 'YYYY-MM') AS month,
  count(s.stop_id) OVER (PARTITION BY s.object_of_search, str.street_id) AS stopsDrugCount
FROM stops s INNER JOIN locations l on s.location_id = l.location_id
             INNER JOIN streets str on l.street_id = str.street_id
WHERE s.outcome = 'Arrest' AND s.object_of_search = 'Controlled drugs'
),
crimeTheft AS (SELECT
  s.street_id,
  s.name,
  to_char(to_timestamp(c.month/1000), 'YYYY-MM') AS month,
  count(c.crime_id) OVER(PARTITION BY c.category, s.street_id) AS crimesDrugCount
FROM crimes c INNER JOIN locations l on c.location_id = l.location_id
              INNER JOIN streets s on l.street_id = s.street_id
WHERE c.category='other-theft'
),
stopTheft AS (SELECT
 str.street_id,
 str.name,
 to_char(s.datetime, 'YYYY-MM') AS month,
 count(s.stop_id) OVER (PARTITION BY s.object_of_search, str.street_id) AS stopsDrugCount
FROM stops s INNER JOIN locations l on s.location_id = l.location_id
            INNER JOIN streets str on l.street_id = str.street_id
WHERE s.outcome = 'Arrest' AND s.object_of_search = 'Stolen goods'
)
SELECT DISTINCT
  allStreetsAndDates.street_id AS streetId,
  allStreetsAndDates.name AS streetName,
  allStreetsAndDates.month AS month,
  crimeDrugs.crimesDrugCount AS drugsCrimesCount,
  stopDrugs.stopsDrugCount AS drugsStopsCount,
  crimeTheft.crimesDrugCount AS theftCrimesCount,
  stopTheft.stopsDrugCount AS theftStopAndSearchCount
FROM allStreetsAndDates
                        RIGHT JOIN crimeDrugs ON allStreetsAndDates.street_id = crimeDrugs.street_id
                                                   AND allStreetsAndDates.name = crimeDrugs.name
                        RIGHT JOIN stopDrugs ON allStreetsAndDates.street_id = stopDrugs.street_id
                                                   AND allStreetsAndDates.name = stopDrugs.name

                        RIGHT JOIN crimeTheft ON allStreetsAndDates.street_id = crimeTheft.street_id
                                                  AND allStreetsAndDates.name = crimeTheft.name
                        RIGHT JOIN stopTheft ON allStreetsAndDates.street_id = stopTheft.street_id
                                                  AND allStreetsAndDates.name = stopTheft.name

WHERE allStreetsAndDates.street_id NOTNULL AND allStreetsAndDates.name NOTNULL
                                           AND allStreetsAndDates.month >= ? AND allStreetsAndDates.month <= ?;