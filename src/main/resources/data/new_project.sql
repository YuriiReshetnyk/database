CREATE DATABASE IF NOT EXISTS reshetnyk;
USE reshetnyk;

DROP TABLE IF EXISTS course_author;
DROP TABLE IF EXISTS answer_option;
DROP TABLE IF EXISTS question;
DROP TABLE IF EXISTS user_progress;
DROP TABLE IF EXISTS test;
DROP TABLE IF EXISTS module;
DROP TABLE IF EXISTS user_course;
DROP TABLE IF EXISTS course;
DROP TABLE IF EXISTS topic;
DROP TABLE IF EXISTS extra_table;
DROP TABLE IF EXISTS `user`;
DROP TABLE IF EXISTS author;

CREATE TABLE topic (
  id INT AUTO_INCREMENT PRIMARY KEY,
  `name` VARCHAR(45) NOT NULL
  );
  
CREATE TABLE author (
  id INT AUTO_INCREMENT PRIMARY KEY,
  full_name VARCHAR(45) NOT NULL,
  photo VARCHAR(255) NOT NULL,
  author_information TEXT NOT NULL
);

CREATE TABLE course (
  id INT AUTO_INCREMENT PRIMARY KEY,
  `name` VARCHAR(128) NOT NULL,
  duration INT NULL,
  price INT NOT NULL,
  introduction TEXT NULL,
  start_time DATE NOT NULL,
  topic_id INT NOT NULL
);

CREATE TABLE `user` (
  id INT AUTO_INCREMENT PRIMARY KEY,
  user_name VARCHAR(30) NOT NULL UNIQUE,
  first_name VARCHAR(45) NOT NULL,
  middle_name VARCHAR(45) NULL,
  last_name VARCHAR(45) NOT NULL,
  email VARCHAR(45) NOT NULL UNIQUE,
  phone_number VARCHAR(45) NOT NULL UNIQUE,
  `password` VARCHAR(45) NOT NULL
  );
  
CREATE TABLE course_author (
  course_id INT NOT NULL,
  author_id INT NOT NULL,
  PRIMARY KEY (`course_id`, `author_id`)
);

CREATE TABLE module (
  id INT AUTO_INCREMENT PRIMARY KEY,
  `name` VARCHAR(255) NOT NULL,
  `text` TEXT NULL,
  video VARCHAR(255) NULL,
  additional_information TEXT NULL,
  order_position INT NOT NULL,
  course_id INT NOT NULL
);

CREATE TABLE test (
  id INT AUTO_INCREMENT PRIMARY KEY,
  `name` VARCHAR(45) NOT NULL,
  introduction TEXT NULL,
  order_position INT NOT NULL,
  course_id INT NOT NULL
);

CREATE TABLE question (
  id INT AUTO_INCREMENT PRIMARY KEY,
  question TEXT NOT NULL,
  photo VARCHAR(45) NULL,
  test_id INT NOT NULL
);

CREATE TABLE answer_option (
  id INT AUTO_INCREMENT PRIMARY KEY,
  `option` TEXT NOT NULL,
  is_answer TINYINT NOT NULL,
  question_id INT NOT NULL
);

CREATE TABLE user_course (
  user_id INT NOT NULL,
  course_id INT NOT NULL,
  PRIMARY KEY (`user_id`, `course_id`)
);

CREATE TABLE user_progress (
  id INT AUTO_INCREMENT PRIMARY KEY,
  begin_timestamp TIMESTAMP NOT NULL,
  end_timestamp TIMESTAMP NOT NULL,
  user_id INT NOT NULL,
  task_id INT NOT NULL
);

CREATE TABLE extra_table(
  id INT AUTO_INCREMENT PRIMARY KEY,
  string_colum VARCHAR(255),
  int_colum INT,
  foreign_user_id INT
);

ALTER TABLE user_progress
	ADD INDEX fk_user_progress_user1_idx (user_id),
    ADD INDEX fk_user_progress_module1_idx (task_id),
    ADD CONSTRAINT fk_user_progress_user1
		FOREIGN KEY (user_id)
        REFERENCES `user` (id),
	ADD CONSTRAINT fk_user_progress_test1
		FOREIGN KEY (task_id)
        REFERENCES test (id);

ALTER TABLE user_course
	ADD INDEX fk_user_has_course_course1_idx (course_id),
    ADD INDEX fk_user_has_course_user1_idx (user_id),
    ADD CONSTRAINT fk_user_has_course_user1
		FOREIGN KEY (user_id)
        REFERENCES `user` (id),
    ADD CONSTRAINT fk_course_has_course_course1
		FOREIGN KEY (course_id)
        REFERENCES course (id);

ALTER TABLE answer_option
	ADD INDEX fk_answer_option_question1_idx (question_id),
    ADD CONSTRAINT fk_answer_option_question1
		FOREIGN KEY (question_id)
        REFERENCES question (id);

ALTER TABLE question
	ADD INDEX fk_question_test1_idx (test_id),
    ADD CONSTRAINT fk_question_test1
		FOREIGN KEY (test_id)
        REFERENCES test (id);

ALTER TABLE test
	ADD INDEX fk_test_course1_idx (course_id),
    ADD CONSTRAINT fk_test_course1
		FOREIGN KEY (course_id)
        REFERENCES course (id);

ALTER TABLE module
	ADD INDEX fk_module_course1_idx (course_id),
    ADD CONSTRAINT fk_module_course1
		FOREIGN KEY (course_id)
        REFERENCES course (id);
        
ALTER TABLE course_author
	ADD INDEX fk_course_has_author_author_idx (author_id),
    ADD INDEX fk_course_has_author_course_idx (course_id),
    ADD CONSTRAINT fk_course_has_author_course
		FOREIGN KEY (course_id)
        REFERENCES course (id),
    ADD CONSTRAINT fk_course_has_author_author
		FOREIGN KEY (author_id)
        REFERENCES author (id);


ALTER TABLE `user`
	ADD UNIQUE INDEX email_UNIQUE (email),
    ADD UNIQUE INDEX phone_number_UNIQUE (phone_number),
	ADD UNIQUE INDEX user_name_UNIQUE (user_name),
    ADD UNIQUE INDEX id_UNIQUE (id);


    
ALTER TABLE course
	ADD INDEX fk_course_topic1_idx (topic_id),
    ADD CONSTRAINT fk_course_topic1
		FOREIGN KEY (topic_id)
        REFERENCES topic (id);