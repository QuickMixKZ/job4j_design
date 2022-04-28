INSERT INTO 
	role(name, role_id)
VALUES
	('Администратор', 1),
	('Модератор', 2),
	('Пользователь', 3);

INSERT INTO
	users(name, role_id, user_id)
VALUES
	('Михаил', 1, 1),
	('Александр', 2, 2),
	('Лев', 3, 3);
	
INSERT INTO 
	rules(name, rule_id)
VALUES
	('Создание базы', 1),
	('Создание пользователя', 2),
	('Изменение пользователя', 3),
	('Удаление пользователя', 4),
	('Просмотр пользователей', 5);
	
INSERT INTO
	role_rules(role_id, rule_id)
VALUES
	(1, 1),
	(1, 2),
	(1, 3),
	(1, 4),
	(1, 5),
	(2, 3),
	(2, 5),
	(3, 5);
	
INSERT INTO
	category(name, category_id)
VALUES
	('Неважная', 1),
	('Важная', 2),
	('Очень важная', 3);
	
INSERT INTO
	state(name, state_id)
values
	('Принята', 1),
	('В процессе', 2),
	('Завершена', 3);

INSERT INTO
	item(item_id, content, user_id, category_id, state_id)
VALUES
	(1, 'Создать пользователя', 1, 2, 3),
	(2, 'Просмотреть информацию о пользователе', 3, 1, 2),
	(3, 'Изменить данные пользователя', 2, 2, 1);
	
INSERT INTO
	comments(content, item_id)
VALUES
	('Имя: Гость', 1),
	('Новое имя: Посетитель', 3);
	
INSERT INTO
	attachs(content, item_id)
VALUES
	('*addres of user photo*', 1),
	('*addres of user new photo*', 3);