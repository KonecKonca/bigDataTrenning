WITH crimesCountView AS (
  SELECT c.category,
         c.month date,
         count(*) AS crimesCount
  FROM crimes AS c
  WHERE c.month > ? AND c.month < ?
  GROUP BY c.category, c.month
)
SELECT
  cr.category AS crimeCategory,
  to_char(to_timestamp(date/1000), 'YYYY-MM') AS month,
  cr.crimesCount AS currentMonthCount,
  first_value(crimesCount) OVER previousMonth AS previousMonthCount,
  avg(cr.crimesCount) OVER(PARTITION BY cr.category) AS deltaCount,
  round((((cr.crimesCount - first_value(crimesCount) over previousMonth) * 100)::numeric / first_value(crimesCount) over previousMonth ), 3) AS growRate
FROM crimesCountView AS cr
     WINDOW previousMonth AS (PARTITION BY cr.category ORDER BY date ASC ROWS BETWEEN 1 PRECEDING AND CURRENT ROW);