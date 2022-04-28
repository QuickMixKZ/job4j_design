CREATE TABLE role(
	role_id SERIAL PRIMARY KEY,
	name VARCHAR(150)
);

CREATE TABLE users(
	user_id SERIAL PRIMARY KEY,
	name VARCHAR(150),
	role_id INT REFERENCES role(role_id)
);

CREATE TABLE rules(
	rule_id SERIAL PRIMARY KEY,
	name VARCHAR(150)
);

CREATE TABLE role_rules(
	role_rules_id SERIAL PRIMARY KEY,
	role_id INT REFERENCES role(role_id),
	rule_id INT REFERENCES rules(rule_id)
);

CREATE TABLE category(
	category_id SERIAL PRIMARY KEY,
	name VARCHAR(150)
);

CREATE TABLE state(
	state_id SERIAL PRIMARY KEY,
	name VARCHAR(150)
);

CREATE TABLE item(
	item_id SERIAL PRIMARY KEY,
	content VARCHAR(150),
	user_id INT REFERENCES users(user_id),
	category_id INT REFERENCES category(category_id),
	state_id INT REFERENCES state(state_id)
);

CREATE TABLE comments(
	comment_id SERIAL PRIMARY KEY,
	content VARCHAR(200),
	item_id INT REFERENCES item(item_id)
);

CREATE TABLE attachs(
	attach_id SERIAL PRIMARY KEY,
	content VARCHAR(200),
	item_id INT REFERENCES item(item_id)
);

