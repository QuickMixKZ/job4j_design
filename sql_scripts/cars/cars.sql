create table cars(
	id serial primary key,
	brand text,
	max_speed smallint,
	electro boolean
);

insert into cars (brand, max_speed, electro) 
values('BMW', 320, true);

update cars set max_speed=340, electro=false;

delete from cars;