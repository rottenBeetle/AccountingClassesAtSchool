CREATE TABLE classes(
     id SERIAL PRIMARY KEY,
     yearOfStudy INTEGER,
     mnemonicCode CHARACTER VARYING(10),
     teacher_id INTEGER,
     FOREIGN KEY (teacher_id) REFERENCES teachers(id)
);