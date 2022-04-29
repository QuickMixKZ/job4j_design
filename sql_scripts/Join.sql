-- CREATE TABLE departments(
-- 	department_id SERIAL PRIMARY KEY,
-- 	name TEXT
-- );

-- CREATE TABLE employees(
-- 	employee_id SERIAL PRIMARY KEY,
-- 	department_id INT REFERENCES departments(department_id),
-- 	name TEXT
-- );

-- INSERT INTO
-- 	departments(name)
-- VALUES
-- 	('Департамент продаж'),
-- 	('Департамент поддержки'),
-- 	('Департамент маркетинга');

-- INSERT INTO
-- 	employees(department_id, name)
-- VALUES
-- 	(1, 'Ivan'),
-- 	(1, 'Nikolay'),
-- 	(2, 'Mikhail'),
-- 	(2, 'Alexandr'),
-- 	(2, 'Lev');

-- -- LEFT
-- SELECT
-- 	e.name,
-- 	d.name
-- FROM
-- 	employees AS e
-- 	LEFT JOIN departments AS d ON e.department_id = d.department_id;

-- -- RIGHT
-- SELECT
-- 	e.name,
-- 	d.name
-- FROM
-- 	employees AS e
-- 	RIGHT JOIN departments AS d ON d.department_id = e.department_id;
	
-- FULL
-- SELECT
-- 	e.name,
-- 	d.name
-- FROM
-- 	employees AS e
-- 	FULL JOIN departments AS d ON d.department_id = e.department_id;

-- CROSS
-- SELECT
-- 	e.name,
-- 	d.name
-- FROM
-- 	departments AS d
-- 	CROSS JOIN employees AS e;

-- Используя left join найти департаменты, у которых нет работников
-- SELECT
-- 	d.name
-- FROM
-- 	departments AS d
-- 	LEFT JOIN employees AS e ON d.department_id = e.department_id
-- WHERE
-- 	e IS NULL;

-- 4. Используя left и right join написать запросы, которые давали бы одинаковый результат
-- SELECT
-- 	e.name,
-- 	d.name
-- FROM
-- 	employees AS e
-- 	LEFT JOIN departments AS d ON e.department_id = d.department_id;
	
-- SELECT
-- 	e.name,
-- 	d.name
-- FROM
-- 	departments AS d
-- 	RIGHT JOIN employees AS e ON d.department_id = e.department_id;

-- 5. Создать таблицу teens с атрибутами name, gender и заполнить ее.
-- CREATE TABLE teens(
-- 	teen_id SERIAL PRIMARY KEY,
-- 	name TEXT,
-- 	gender VARCHAR(1)
-- );

-- INSERT INTO
-- 	teens(name, gender)
-- VALUES
-- 	('Mikhail', 'M'),
-- 	('Lev', 'M'),
-- 	('Alexandr', 'M'),
-- 	('Anastassiya', 'F'),
-- 	('Svetlana', 'F'),
-- 	('Marina', 'F');

-- Используя cross join составить все возможные разнополые пары
-- SELECT
-- 	m.name,
-- 	f.name
-- FROM 
-- 	teens AS m 
-- 	CROSS JOIN teens AS f
-- WHERE f.gender = 'F' AND m.gender = 'M';


	
