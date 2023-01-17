CREATE TABLE IF NOT EXISTS user (id BIGINT AUTO_INCREMENT PRIMARY KEY, name VARCHAR(255), password VARCHAR(255));

CREATE TABLE IF NOT EXISTS movie (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title varchar(255),
    external_id varchar (50),
    rating real,
    votes real
);

CREATE TABLE IF NOT EXISTS quiz (
     id BIGINT AUTO_INCREMENT PRIMARY KEY,
     movie_id ARRAY
);

CREATE TABLE IF NOT EXISTS control_quiz (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT,
    status VARCHAR(15)
);

CREATE TABLE IF NOT EXISTS quiz_answer (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    control_quiz_id BIGINT,
    quiz_id BIGINT,
    hit varchar (3),
    CONSTRAINT fk_control_quiz_id FOREIGN KEY (control_quiz_id) REFERENCES control_quiz(id),
    CONSTRAINT fk_quiz_answer_id FOREIGN KEY (quiz_id) REFERENCES quiz(id)
);


