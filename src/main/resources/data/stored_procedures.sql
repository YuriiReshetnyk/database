USE reshetnyk;

DROP PROCEDURE IF EXISTS insert_into_author;
DROP PROCEDURE IF EXISTS insert_10_into_author;
DROP PROCEDURE IF EXISTS find_max_course_price;
DROP PROCEDURE IF EXISTS dynamic_procedure;

DELIMITER //
CREATE PROCEDURE insert_into_author(
    IN new_full_name VARCHAR(45),
    IN new_photo VARCHAR(255),
    IN new_author_information TEXT
)
BEGIN
    INSERT INTO reshetnyk.author (full_name, photo, author_information)
    VALUES (new_full_name, new_photo, new_author_information);
END //


DELIMITER //
CREATE PROCEDURE insert_10_into_author()
BEGIN
    DECLARE interaction int;
    SET interaction = 1;
    while_cycle : WHILE interaction < 10 DO
        INSERT INTO reshetnyk.author (full_name, photo, author_information)
        VALUES (CONCAT('full_name', '-', interaction),
                CONCAT('photo', '-', interaction),
                CONCAT('author_information', '-', interaction));
        SET interaction = interaction + 1;
    END WHILE;
END //

DELIMITER //
CREATE PROCEDURE find_max_course_price()
BEGIN
    SELECT max_course_price() as max_price;
END //


DELIMITER //
CREATE PROCEDURE dynamic_procedure()

BEGIN
    DECLARE done BOOLEAN DEFAULT False;
    DECLARE string_name VARCHAR(30);
    DECLARE interaction int;
    DECLARE random_number int;

    DECLARE user_cursor CURSOR
        FOR SELECT user_name FROM reshetnyk.`user`;
    DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;
    OPEN user_cursor;

    loop_cycle : LOOP
        FETCH user_cursor INTO string_name;
        IF (done = True) THEN LEAVE loop_cycle;
        END IF;
        SET @dropDB = CONCAT('DROP DATABASE IF EXISTS ', string_name, ';');
        PREPARE query1 FROM @dropDB;
        EXECUTE query1;
        DEALLOCATE PREPARE query1;
        SET @createDB = CONCAT('CREATE DATABASE ', string_name, ';');
        PREPARE query1 FROM @createDB;
        EXECUTE query1;
        DEALLOCATE PREPARE query1;
        SELECT FLOOR(RAND()*(9-1+1))+1 INTO random_number;
        SET interaction = 0;
        WHILE interaction < random_number DO
                SET interaction = interaction + 1;
                SET @createTable = CONCAT('CREATE TABLE ', string_name,'.', string_name, interaction, '(id INT);');
                PREPARE query3 FROM @createTable;
                EXECUTE query3;
                DEALLOCATE PREPARE query3;
            END WHILE;
    END LOOP;
    CLOSE user_cursor;
END //

