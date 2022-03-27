
CREATE TABLE teachers(
    id SERIAL PRIMARY KEY,
    lastName CHARACTER VARYING(30),
    firstName CHARACTER VARYING(30),
    patronymic CHARACTER VARYING(40),
    dateBirth date,
    gender CHARACTER VARYING(30),
    mainSubject CHARACTER VARYING(30)
);