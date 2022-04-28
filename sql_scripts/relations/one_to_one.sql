CREATE TABLE engine(
	engine_id SERIAL PRIMARY KEY,
	brand VARCHAR(50),
	horsepower int
);

CREATE TABLE car(
	car_id SERIAL PRIMARY KEY,
	brand VARCHAR(50),
	engine_id int REFERENCES engine(engine_id)
)
