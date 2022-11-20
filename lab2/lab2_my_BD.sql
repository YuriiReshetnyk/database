
-- 1 запит ()
SELECT name
FROM course
WHERE topic_id IN (SELECT id FROM topic WHERE name = 'Підготовка до ЗНО')
ORDER BY price DESC;

-- 2 запит
SELECT name
FROM course
WHERE duration < 10
ORDER BY start_time DESC;

-- 3 запит
SELECT password
FROM user_private
WHERE email = 'yura.reshetnyk295@gmail.com';

-- 4 запит
SELECT name, text, video, additional_information
FROM module
WHERE course_id = 1
ORDER BY order_position;

-- 5 запит
SELECT name, introduction
FROM test
WHERE course_id = 1
ORDER BY order_position;

-- 6 запит
SELECT question, photo
FROM question
WHERE test_id = 1;

-- 7 запит
SELECT `option`
FROM answer_options
WHERE question_id = 2;

-- 8 запит
SELECT `option`
FROM answer_options
WHERE question_id = 2 AND is_answer = 1;

-- 9 запит 
SELECT full_name, photo, author_information
FROM author
WHERE id IN (SELECT author_id FROM course_author WHERE course_id = 1);

-- 10 запит
SELECT name
FROM course
WHERE id IN (SELECT course_id 
			FROM user_course
            WHERE user_id IN (SELECT id FROM user 
							WHERE user_name = 'yura295'));


