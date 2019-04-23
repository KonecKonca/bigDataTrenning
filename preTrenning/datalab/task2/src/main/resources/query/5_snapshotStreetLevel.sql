SELECT
  street.street_id AS streetId,
  street.name AS streetName,
  (
    SELECT
      s1.age_range
    FROM stops s1  INNER JOIN locations AS l1 ON s1.location_id = l1.location_id
                   INNER JOIN streets AS str1 ON l1.street_id = str1.street_id
    WHERE str1.name=street.name AND street.street_id= str1.street_id
    GROUP BY s1.age_range
    ORDER BY count(s1.age_range)
    LIMIT 1
  ) AS mostPopularAgeRange,
  (
    SELECT
      s2.gender
    FROM stops s2  INNER JOIN locations AS l2 ON s2.location_id = l2.location_id
                   INNER JOIN streets AS str2 ON l2.street_id = str2.street_id
    WHERE str2.name=street.name AND street.street_id= str2.street_id
    GROUP BY s2.gender
    ORDER BY count(s2.gender)
    LIMIT 1
  ) AS mostPopularGenderRange,
  (
    SELECT
      s3.self_defined_ethnicity
    FROM stops s3  INNER JOIN locations AS l3 ON s3.location_id = l3.location_id
                   INNER JOIN streets AS str3 ON l3.street_id = str3.street_id
    WHERE str3.name=street.name AND street.street_id= str3.street_id
    GROUP BY s3.self_defined_ethnicity
    ORDER BY count(s3.self_defined_ethnicity)
    LIMIT 1
  ) AS mostPopularEthnicity,
  (
    SELECT
      s4.object_of_search
    FROM stops s4  INNER JOIN locations AS l4 ON s4.location_id = l4.location_id
                   INNER JOIN streets AS str4 ON l4.street_id = str4.street_id
    WHERE str4.name=street.name AND street.street_id= str4.street_id
    GROUP BY s4.object_of_search
    ORDER BY count(s4.object_of_search)
    LIMIT 1
  ) AS mostPopularObjectOfSearch,
  (
    SELECT
      s5.outcome
    FROM stops s5  INNER JOIN locations AS l5 ON s5.location_id = l5.location_id
                   INNER JOIN streets AS str5 ON l5.street_id = str5.street_id
    WHERE str5.name=street.name AND street.street_id= str5.street_id
    GROUP BY s5.outcome
    ORDER BY count(s5.outcome)
    LIMIT 1
  ) AS mostPopularOutcome
FROM stops AS stop  INNER JOIN locations AS  location on stop.location_id = location.location_id
                    INNER JOIN streets AS street on location.street_id = street.street_id
WHERE stop.datetime::date > ?::date AND stop.datetime::date < ?::date
GROUP BY street.street_id, street.name;