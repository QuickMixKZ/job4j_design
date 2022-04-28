create table fauna (
    id serial primary key,
    name text,
    avg_age int,
    discovery_date date
);

INSERT INTO
	fauna(name, avg_age, discovery_date)
VALUES
	('jellyfish', 15000, '1938.03.05'),
	('catfish', 8000, '1999.12.31'),
	('dog', 22000, '1912.11.15'),
	('cat', 12000, '1963.07.24');

SELECT
	*
FROM
	fauna
WHERE
name LIKE '%fish%';

SELECT
	*
FROM
	fauna
WHERE
	avg_age BETWEEN 10000 AND 21000;

SELECT
	*
FROM
	fauna
WHERE
	EXTRACT(YEAR FROM discovery_date) < 1950;