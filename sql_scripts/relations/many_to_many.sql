CREATE TABLE author(
	author_id SERIAL PRIMARY KEY,
	name VARCHAR(50)
);

CREATE TABLE book(
	book_id SERIAL PRIMARY KEY,
	title VARCHAR(50)
);

CREATE TABLE book_author(
	book_author_id SERIAL PRIMARY KEY,
	author_id INT REFERENCES author(author_id),
	book_id INT REFERENCES book(book_id)
)