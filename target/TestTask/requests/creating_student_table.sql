CREATE TABLE students(
    id SERIAL PRIMARY KEY,
    lastName CHARACTER VARYING(30),
    firstName CHARACTER VARYING(30),
    patronymic CHARACTER VARYING(40),
    dateBirth date,
    gender CHARACTER VARYING(10),
    class_id INTEGER,
    FOREIGN KEY (class_id) REFERENCES classes(id)
);