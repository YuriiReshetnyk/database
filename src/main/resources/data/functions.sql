USE reshetnyk;

DROP FUNCTION IF EXISTS max_course_price;

DELIMITER //
CREATE FUNCTION max_course_price() RETURNS int
    DETERMINISTIC
BEGIN
    DECLARE max_price int;
    SELECT MAX(price) INTO max_price FROM reshetnyk.course;
    RETURN max_price;
END //
