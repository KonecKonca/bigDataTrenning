SELECT
  streetId,
  streetName,
  crimesCount,
  round((100 * crimesCount)::numeric / totalCrimesCount, 6) AS percentage
FROM
  (SELECT
     s.street_id AS streetId,
     s.name AS streetName,
     count(c.crime_id) AS crimesCount
   FROM crimes c INNER JOIN locations l on c.location_id = l.location_id
                 INNER JOIN streets s on l.street_id = s.street_id
                 INNER JOIN outcomestatuses o on c.outcome_status_id = o.status_id
   WHERE c.month > ? AND c.month < ? AND o.category = ?
   GROUP BY streetId, streetName
  ) AS sub
    CROSS JOIN
  (
    SELECT
      count(*) totalCrimesCount
    FROM crimes
    WHERE outcome_status_id NOTNULL
  ) AS totalCount
;

