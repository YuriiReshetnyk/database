USE reshetnyk;

DROP TRIGGER IF EXISTS before_insert_extra_table;
DROP TRIGGER IF EXISTS before_update_extra_table;
DROP TRIGGER IF EXISTS before_update_user;
DROP TRIGGER IF EXISTS before_delete_user;
DROP TRIGGER IF EXISTS after_delete_topic_min_row_6;
DROP TRIGGER IF EXISTS before_insert_user_check1;
DROP TRIGGER IF EXISTS before_insert_user_check2;


DELIMITER //
CREATE TRIGGER before_insert_extra_table
    BEFORE INSERT
    ON reshetnyk.extra_table
    FOR EACH ROW
BEGIN
    IF(NOT EXISTS(SELECT id FROM reshetnyk.`user` WHERE id = NEW.foreign_user_id))
    THEN SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Error! User with such id does`t exist';
    END IF;
END //


DELIMITER //
CREATE TRIGGER before_update_extra_table
BEFORE UPDATE
ON reshetnyk.extra_table
FOR EACH ROW
BEGIN
IF(NOT EXISTS(SELECT id FROM reshetnyk.`user` WHERE id = NEW.foreign_user_id))
THEN SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Error! User with such id does`t exist';
END IF;
END //


DELIMITER //
CREATE TRIGGER before_update_user
BEFORE UPDATE
ON reshetnyk.`user`
FOR EACH ROW
BEGIN
IF(EXISTS(SELECT foreign_user_id FROM reshetnyk.extra_table WHERE foreign_user_id = OLD.id))
THEN SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Error! Can`t update user row! FK error';
END IF;
END //


DELIMITER //
CREATE TRIGGER before_delete_user
BEFORE DELETE
ON reshetnyk.`user`
FOR EACH ROW
BEGIN
IF(EXISTS(SELECT foreign_user_id FROM reshetnyk.extra_table WHERE foreign_user_id = OLD.id))
THEN SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Error! Can`t delete user row! FK error';
END IF;
END //


DELIMITER //
CREATE TRIGGER after_delete_topic_min_row_6
    AFTER DELETE
    ON reshetnyk.topic
    FOR EACH ROW
BEGIN
    IF((SELECT COUNT(*) FROM reshetnyk.topic) < 6) THEN SIGNAL SQLSTATE '45000'
       SET MESSAGE_TEXT = 'Delete error MIN car';
END IF;
END //


DELIMITER //
CREATE TRIGGER before_insert_user_check1
    BEFORE INSERT
    ON reshetnyk.`user`
    FOR EACH ROW
BEGIN
    IF(NEW.first_name NOT RLIKE 'Svitlana|Petro|Olha|Taras') THEN SIGNAL SQLSTATE '45000'
    SET MESSAGE_TEXT = 'Check error for insert into user';
END IF;
END //


DELIMITER //
CREATE TRIGGER before_insert_user_check2
    BEFORE INSERT
    ON reshetnyk.`user`
    FOR EACH ROW
BEGIN
    IF(NEW.phone_number NOT RLIKE '$00') THEN SIGNAL SQLSTATE '45000'
    SET MESSAGE_TEXT = 'Check error for insert into user';
END IF;
END //
