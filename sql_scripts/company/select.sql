SELECT
	p.name AS person_name,
	c.name AS company_name
FROM
	person AS p
	LEFT JOIN company AS c ON p.company_id = c.id
WHERE
    p.company_id != 5;

SELECT
	c.name AS company,
	count(p.name) AS workers
FROM
	person as p
	LEFT JOIN company AS c ON p.company_id = c.id
GROUP BY
	c.name
HAVING
	count(p.name) = (SELECT
						count(name)
					FROM
						person
					GROUP BY
						company_id
					ORDER BY
						count(name) DESC
					LIMIT 1);