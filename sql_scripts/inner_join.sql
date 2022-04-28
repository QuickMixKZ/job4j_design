CREATE TABLE country(
	country_id SERIAL PRIMARY KEY,
	name TEXT
);

CREATE TABLE person(
	person_id SERIAL PRIMARY KEY,
	name TEXT,
	country_id INT REFERENCES country(country_id)
);

INSERT INTO
	country(name)
VALUES
	('Kazakhstan'),
	('USA');

INSERT INTO
	person(name, country_id)
VALUES
	('Mikhail', 1),
	('Alexandr', 1),
	('Lev', 2);
	
SELECT
	p.name,
	c.name
FROM
	person AS p
	JOIN country AS c ON p.country_id = c.country_id;

SELECT
	p.name,
	c.name
FROM
	person AS p
	JOIN country AS c ON p.country_id = c.country_id
WHERE
	c.name = 'Kazakhstan';

SELECT
	p.name,
	c.name
FROM
	person AS p
	JOIN country AS c ON p.country_id = c.country_id
WHERE
	p.name = 'Lev';