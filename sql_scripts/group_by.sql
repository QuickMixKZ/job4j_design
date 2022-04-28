INSERT INTO
	devices(name, price)
VALUES
	('phone', 7000),
	('tablet', 8000),
	('laptop', 12000),
	('watches', 3000);
	
INSERT INTO
	people(name)
VALUES
	('Mikhail'),
	('Alexandr'),
	('Lev');
	
INSERT INTO
	devices_people(device_id, people_id)
VALUES
	(4, 1),
	(1, 2),
	(2, 2),
	(3, 3),
	(4, 3);
	
SELECT
	AVG(price)
FROM
	devices

SELECT
	p.name,
	AVG(d.price)
FROM
	devices_people AS dp
	JOIN people AS p ON dp.people_id = p.id
	JOIN devices AS d ON dp.device_id = d.id
GROUP BY
	p.name

SELECT
	p.name,
	AVG(d.price)
FROM
	devices_people AS dp
	JOIN people AS p ON dp.people_id = p.id
	JOIN devices AS d ON dp.device_id = d.id
GROUP BY
	p.name
HAVING
	AVG(d.price) > 5000

