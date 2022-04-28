CREATE TABLE handler(
	handler_id SERIAL PRIMARY KEY,
	name VARCHAR(50),
	sex VARCHAR(1)
);

CREATE TABLE dog(
	dog_id SERIAL PRIMARY KEY,
	name VARCHAR(50),
	handler_id int REFERENCES handler(handler_id)
)