CREATE TABLE type(
	type_id SERIAL PRIMARY KEY,
	name TEXT
);

CREATE TABLE product(
	product_id SERIAL PRIMARY KEY,
	name TEXT,
	type_id INT REFERENCES type(type_id),
	expired_date DATE,
	price INT
);

INSERT INTO
	type(name)
VALUES
	('Сыр'),
	('Молоко'),
	('Полуфабрикат')
	('Сладкое');

INSERT INTO
	product(name, type_id, expired_date, price)
VALUES
	('Пармезан', 1, '2022.11.30', 5000),
	('Моцарелла', 1, '2022.07.22', 4750),
	('Сыр плавленный', 1, '2022.05.18', 3000),
	('Мороженое пломбир', 4, '2023.06.11', 200),
	('Мороженое солнечное', 4, '2022.06.11', 250),
	('Мороженое Три копейки', 4, '2024.06.11', 150),
	('Мороженое Маня', 4, '2022.06.11', 100),
	('Мороженое Ваня', 4, '2020.06.11', 120),
	('Мороженое Стаканчик', 4, '2020.09.06', 80),
	('Мороженое Рожок', 4, '2021.09.06', 95),
	('Мороженое Сливочное', 4, '2023.10.26', 320),
	('Мороженое Ванильное', 4, '2021.09.06', 165),
	('Мороженое Шоколадное', 4, '2023.12.31', 140),
	('Молоко Простаквашино', 2, '2020.09.06', 230),
	('Молоко Вкусное', 2, '2022.08.24', 245),
	('Пельмени', 3, '2024.02.19', 350),
	('Вареники', 3, '2023.04.25', 310);

-- 1. Написать запрос получение всех продуктов с типом "СЫР"
SELECT
	p.name,
	p.expired_date,
	p.price
FROM
	product AS p
	JOIN type AS t ON p.type_id = t.type_id
WHERE
	t.name = 'Сыр';

-- 2. Написать запрос получения всех продуктов, у кого в имени есть слово "мороженое"
SELECT
	name,
	expired_date,
	price
FROM
	product
WHERE
	LOWER(name) LIKE '%мороженое%';

-- 3. Написать запрос, который выводит все продукты, срок годности которых уже истек
SELECT
	name,
	expired_date,
	price
FROM
	product
WHERE
	expired_date < CURRENT_DATE;

-- 4. Написать запрос, который выводит самый дорогой продукт.
SELECT
	name,
	expired_date,
	price
FROM
	product
WHERE
	price = (SELECT MAX(price) FROM product);

-- 5. Написать запрос, который выводит для каждого типа количество продуктов к нему принадлежащих. В виде имя_типа, количество
SELECT
	t.name,
	COUNT(t.name)
FROM
	type AS t
	JOIN product AS p ON t.type_id = p.type_id
GROUP BY
	t.name;

-- 6. Написать запрос получение всех продуктов с типом "СЫР" и "МОЛОКО"
SELECT
	p.name,
	p.expired_date,
	p.price
FROM
	product AS p
	JOIN type AS t ON p.type_id = t.type_id
WHERE
	t.name IN ('Сыр', 'Молоко');

-- 7. Написать запрос, который выводит тип продуктов, которых осталось меньше 10 штук.
SELECT
	t.name,
	COUNT(t.name)
FROM
	type AS t
	JOIN product AS p ON t.type_id = p.type_id
GROUP BY
	t.name
HAVING
	COUNT(t.name) < 10;

-- 8. Вывести все продукты и их тип.
SELECT
	p.name,
	t.name,
	p.expired_date,
	p.price
FROM
	product AS p
	JOIN type AS t ON p.type_id = t.type_id;
	