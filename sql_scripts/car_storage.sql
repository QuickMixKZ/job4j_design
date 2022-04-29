-- CREATE TABLE body(
-- 	body_id SERIAL PRIMARY KEY,
-- 	name TEXT
-- );

-- CREATE TABLE engine(
-- 	engine_id SERIAL PRIMARY KEY,
-- 	name TEXT
-- );

-- CREATE TABLE transmission(
-- 	transmission_id SERIAL PRIMARY KEY,
-- 	name TEXT
-- );

-- CREATE TABLE car(
-- 	car_id SERIAL PRIMARY KEY,
-- 	body_id INT REFERENCES body(body_id),
-- 	engine_id INT REFERENCES engine(engine_id),
-- 	transmission_id INT REFERENCES transmission(transmission_id),
-- 	name TEXT
-- );

-- INSERT INTO
-- 	body(name)
-- VALUES
-- 	('Седан'),
-- 	('Универсал'),
-- 	('Кроссовер'),
-- 	('Пикап');

-- INSERT INTO
-- 	engine(name)
-- VALUES
-- 	('Бензиновый'),
-- 	('Дизельный');

-- INSERT INTO
-- 	transmission(name)
-- VALUES
-- 	('Автоматическая'),
-- 	('Механическая'),
-- 	('Гибридная');

-- INSERT INTO
-- 	car(body_id, engine_id, transmission_id, name)
-- VALUES
-- 	(1, 1, 2, 'Mazda 626'),
-- 	(1, 1, 2, 'Honda Accord'),
-- 	(2, 1, NULL, 'Honda Civic'),
-- 	(4, 1, 1, 'Toyota Hilux');

-- SELECT	
-- 	car.name,
-- 	body.name,
-- 	engine.name,
-- 	transmission.name
-- FROM
-- 	car
-- 	LEFT JOIN body ON car.body_id = body.body_id
-- 	LEFT JOIN engine ON car.engine_id = engine.engine_id
-- 	LEFT JOIN transmission ON car.transmission_id = transmission.transmission_id;

-- SELECT
-- 	body.name
-- FROM
-- 	body
-- 	LEFT JOIN car ON car.body_id = body.body_id
-- WHERE car IS NULL;

-- SELECT
-- 	engine.name
-- FROM
-- 	engine
-- 	LEFT JOIN car ON car.engine_id = engine.engine_id
-- WHERE car IS NULL;

-- SELECT
-- 	transmission.name
-- FROM
-- 	transmission
-- 	LEFT JOIN car ON car.transmission_id = transmission.transmission_id
-- WHERE car IS NULL;