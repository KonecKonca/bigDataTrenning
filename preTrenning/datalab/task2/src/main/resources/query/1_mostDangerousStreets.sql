SELECT DISTINCT
  s.street_id AS streetId,
  s.name AS streetName,
  count(c.crime_id) OVER(PARTITION BY s.street_id) as crimeCount
FROM crimes AS c INNER JOIN locations l on c.location_id = l.location_id
                 INNER JOIN streets s on l.street_id = s.street_id
WHERE c.month > ? AND c.month < ? ORDER BY crimeCount DESC LIMIT 500;