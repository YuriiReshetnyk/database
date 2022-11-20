CREATE DATABASE IF NOT EXISTS reshetnyk;
USE reshetnyk;

DROP TABLE IF EXISTS course_author;
DROP TABLE IF EXISTS answer_options;
DROP TABLE IF EXISTS question;
DROP TABLE IF EXISTS user_progress;
DROP TABLE IF EXISTS test;
DROP TABLE IF EXISTS module;
DROP TABLE IF EXISTS user_course;
DROP TABLE IF EXISTS course;
DROP TABLE IF EXISTS topic;
DROP TABLE IF EXISTS user_private;
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
  user_name VARCHAR(30) NOT NULL,
  first_name VARCHAR(45) NOT NULL,
  middle_name VARCHAR(45) NULL,
  last_name VARCHAR(45) NOT NULL
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

CREATE TABLE answer_options (
  id INT AUTO_INCREMENT PRIMARY KEY,
  `option` TEXT NOT NULL,
  is_answer TINYINT NOT NULL,
  question_id INT NOT NULL
);

CREATE TABLE user_course (
  user_id INT NOT NULL,
  course_id INT NOT NULL,
  start_time TIMESTAMP NOT NULL,
  last_time_visited TIMESTAMP NOT NULL,
  PRIMARY KEY (`user_id`, `course_id`)
);

CREATE TABLE user_private (
  user_id INT PRIMARY KEY,
  email VARCHAR(45) NOT NULL,
  phone_number VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL
);

CREATE TABLE user_progress (
  id INT AUTO_INCREMENT PRIMARY KEY,
  begin_timestamp TIMESTAMP NOT NULL,
  end_timestamp TIMESTAMP NOT NULL,
  user_id INT NOT NULL,
  task_id INT NOT NULL
);

ALTER TABLE user_progress
	ADD INDEX fk_user_progress_user1_idx (user_id),
    ADD INDEX fk_user_progress_module1_idx (task_id),
    ADD CONSTRAINT fk_user_progress_user1
		FOREIGN KEY (user_id)
        REFERENCES `user` (id),
    ADD CONSTRAINT fk_user_progress_module1
		FOREIGN KEY (task_id)
        REFERENCES module (id),
	ADD CONSTRAINT fk_user_progress_test1
		FOREIGN KEY (task_id)
        REFERENCES test (id);

ALTER TABLE user_private
	ADD UNIQUE INDEX email_UNIQUE (email),
    ADD UNIQUE INDEX phone_number_UNIQUE (phone_number),
    ADD UNIQUE INDEX user_id_UNIQUE (user_id),
    ADD CONSTRAINT fk_user_private_user1
		FOREIGN KEY (user_id)
        REFERENCES user (id);

ALTER TABLE user_course
	ADD INDEX fk_user_has_course_course1_idx (course_id),
    ADD INDEX fk_user_has_course_user1_idx (user_id),
    ADD CONSTRAINT fk_user_has_course_user1
		FOREIGN KEY (user_id)
        REFERENCES `user` (id),
    ADD CONSTRAINT fk_course_has_course_course1
		FOREIGN KEY (course_id)
        REFERENCES course (id);

ALTER TABLE answer_options
	ADD INDEX fk_answer_options_question1_idx (question_id),
    ADD CONSTRAINT fk_answer_options_question1
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
	ADD UNIQUE INDEX user_name_UNIQUE (user_name),
    ADD UNIQUE INDEX id_UNIQUE (id);


    
ALTER TABLE course
	ADD INDEX fk_course_topic1_idx (topic_id),
    ADD CONSTRAINT fk_course_topic1
		FOREIGN KEY (topic_id)
        REFERENCES topic (id);

INSERT INTO `topic`(`name`) 
VALUES ('ІТ'),
('Аналіз даних'),
('Англійська мова'),
('Бізнес'),
('Громадська освіта'),
('Гомунітарні науки'),
('Деожслужба'),
('Для освітян'),
('Журналістика'),
('Підготовка до ЗНО'),
('Право'),
('Психологія'),
('Особистий розвиток');


INSERT INTO author(full_name, photo, author_information) 
VALUES ('Нікіта Павлюченко','https://courses.prometheus.org.ua/assets/courseware/v1/cb1f29a116cae131bc6472172b046174/c4x/KPI/Programming101/asset/%D0%B2%D0%B5%D0%BE%D0%B2%D0%B5%D0%BE%D0%B0%D0%B5%D0%BE%D0%B06%D0%BE%D0%B0%D0%BE.jpg','Веб-розробник із досвідом понад 9 років, викладач кафедри Автоматизованих систем обробки інформації та управління Національного технічного університету України \"Київський політехнічний інститут\" із 2011 року.'),
('Анна Бондаренко','https://courses.prometheus.org.ua/assets/courseware/v1/8ca147914f590426a30ed64132a6444e/asset-v1:Prometheus+VW101+2022_T3+type@asset+block/%D0%93%D0%B0%D0%BD%D0%BD%D0%B0-%D0%91%D0%BE%D0%BD%D0%B4%D0%B0%D1%80%D0%B5%D0%BD%D0%BA%D0%BE.jpg','Засновниця та керівниця Української Волонтерської Служби.'),
('Вікторія Зацнова','https://courses.prometheus.org.ua/assets/courseware/v1/58793264fdc451028bfea9043967da27/asset-v1:Prometheus+VW101+2022_T3+type@asset+block/%D0%92%D1%96%D0%BA%D1%82%D0%BE%D1%80%D1%96%D1%8F-%D0%97%D0%B0%D1%86%D0%BD%D0%BE%D0%B2%D0%B0.jpg','Керівниця гуманітарного напрямку Української Волонтерської Служби.'),
('Єлизавета Бончужна','https://courses.prometheus.org.ua/assets/courseware/v1/c86c1e7382861fd38cd2e9e2e5a51776/asset-v1:Prometheus+VW101+2022_T3+type@asset+block/%D0%84%D0%BB%D0%B8%D0%B7%D0%B0%D0%B2%D0%B5%D1%82%D0%B0-%D0%91%D0%BE%D0%BD%D1%87%D1%83%D0%B6%D0%BD%D0%B0.jpg','Проєктна менеджерка Української Волонтерської Служби.'),
('Сергій Поліщук​','https://prometheus.org.ua/wp-content/uploads/2021/05/Sergiy-Polishuk-768x836.jpg','Професійний режисер з 20-річним досвідом роботи на центральних телеканалах України («1+1», «Інтер», «СТБ», «Новий канал», ТРК «Україна»).'),
('Сергій Потапов','https://prometheus.org.ua/wp-content/uploads/2022/05/%D0%9F%D0%BE%D1%82%D0%B0%D0%BF%D0%BE%D0%B2-2.jpg','Понад 15 років управлінського досвіду, 5 з яких — в організаційному розвитку. '),
('Девід Дж. Малан','https://courses.prometheus.org.ua/assets/courseware/v1/bb86330124a33bf185ff49f861f7be5a/asset-v1:Prometheus+CS50+2021_T1+type@asset+block/David_J._Malan.jpg','Професор Гарвардського університету. Наукові інтереси професора Малана – сфера кібербезпеки, цифрові експертизи та ботнети. Він визнаний експерт у сфері дистанційного навчання інформатиці та основам програмування.'),
('Брайан Ю','https://courses.prometheus.org.ua/assets/courseware/v1/85869c21373ec67054db0bee6defb842/asset-v1:Prometheus+CS50+2021_T1+type@asset+block/Brian_Yu.png','Старший викладач комп’ютерних наук Гарвардського університету. Бакалавр комп’ютерних наук та лінгвістики. Працює в команді професора Девіда Малана над всіма курсами з основ програмування.'),
('Олесь Петрів','https://courses.prometheus.org.ua/assets/courseware/v1/aeed6187a63b715d662968de602f74b9/c4x/IRF/ML101/asset/oles_petriv.jpg','Останні 4 роки - інженер по розробці систем машинного навчання в компанії VideoGorillas. Займається розробкою систем computer vision для кіностудій, розпізнаванням образів у відеопотоці, систем моделювання мови за допомогою нейромереж та вирішенням прикладних задач в сфері natural language processing. Навчався в Києво-Могилянській академії (\"програмна інженерія\" та \"фізика\"). Раніше займався розробкою систем аналітики соціальних медіа в компанії SOHO.net.'),
('Антон Чернятевич','https://courses.prometheus.org.ua/assets/courseware/v1/0ff3faf9340abe4779305a020ac0c3b6/c4x/IRF/ML101/asset/2017-04-03_02.07.03_1.jpg','Студент НТУУ «КПІ» («Прикладна математика»). Протягом 2 років працює data scientist\'ом. Займається побудовою моделей машинного навчання на фінансових даних та цікавиться Computer Vision. Був організатором Data Science Hackathon та KPI Vision Hack.'),
('Олександра Шкорінова','https://courses.prometheus.org.ua/assets/courseware/v1/926391d79f8154e2a198277cbf158a5e/c4x/IRF/ML101/asset/2017-04-03_02.02.49_1.jpg','Студентка НТУУ «КПІ» («Прикладна математика»). Займається дослідницькою діяльністю у сфері математичної статистики та фінансової математики з використанням методів машинного навчання.');

INSERT INTO course(`name`, duration, price, introduction, start_time, topic_id) 
VALUES ('Основи програмування',8,0,'Так склалося, що ми живемо в XXI-му столітті, а професія програміста досі є екзотичною та незрозумілою для більшої частини суспільства, подібно до ролі шамана для наших пращурів. Метою масового онлайн-курсу \"Основи програмування\" є, насамперед, розвіяти цей міф і показати всім охочим як працює персональний комп\'ютер і що таке програмування.','2021-09-24',1),
('Фінансовий менеджмент',6,0,'Чому одні країни, підприємства або ж люди стають фінансово успішними, а інші – ні? Якими є загальні принципи управління фінансами, які визначають успіх? Що треба врахувати, маючи бізнес-ідею? Як оцінити ефективність та інвестиційну привабливість бізнесу? Якими є найсучасніші тренди розвитку фінансів?','2022-09-24',4),
('Дизайн-мислення для інновацій',5,0,'Цей курс є освітнім проектом благодійної організації «Фонд Випускників України» (Alumni Fund Ukraine), яка отримала дозвіл від Школи бізнесу Дарден, США, на переклад та запуск їхнього онлайн-курсу \"Design Thinking for Innovation\" на провідній платформі онлайн-курсів в Україні Prometheus.','2022-10-23',4),
('Бізнес-англійська',5,0,'Володіння основами бізнес-англійської сьогодні стає все більш необхідним, якщо ви хочете отримати гарну посаду і плануєте працювати з іноземними клієнтами.','2019-10-23',3),
('Англійська для початківців. Elementary level (A1-A2)',13,0,'Цей курс розроблений для активних та цілеспрямованих людей, які знають наскільки кардинально англійська здатна змінити життя. «Англійська для початківців. Elementary level (A1-A2)» допоможе вам реалізувати свій перший крок до мети швидко та саме так, як буде зручно вам.','2019-01-23',3),
('Протидія та попередження булінгу (цькуванню) в закладах освіти',1,0,'Булінг (цькування) – тривожна тенденція, особливо для сучасного дитячого середовища. За результатами дослідження, проведеного UNICEF у 2017 році, 67% дітей в Україні у віці від 11 до 17 років стикалися з проблемою булінгу (цькування) впродовж останніх трьох місяців, а 24% дітей стали жертвами цього явища.','2019-01-18',8),
('Психологія стресу та способи боротьби з ним',5,1000,'\"Рятуючись від пекучих пісків пустелі, ти можеш встелити її килимами, але надійніше одягти взуття\" - східна мудрість.','2022-12-12',12),
('Українська мова та література. Підготовка до ЗНО',NULL,0,'Курс «Українська мова та література. Підготовка до ЗНО» покликаний допомогти всім охочим вступникам поглибити та закріпити набуті знання із шкільного курсу української мови та літератури.','2022-12-30',10),
('Історія України. Повний курс підготовки до ЗНО',NULL,0,'Цей курс стане у пригоді всім, хто прагне закріпити набуті знання шкільної програми з історії України та підготуватись до ЗНО й ДПА. Курс розбито на теми, які відповідають програмі ЗНО та охоплюють історію України від заселення території до подій сучасності.','2017-12-30',10),
('4 кроки до здорового харчування',NULL,0,'Чому ми їмо? Здається, відповідь на це питання проста – їжа потрібна нам для життя. Але чи їмо ми лише для того, щоб жити? У кожного з нас є улюблена страва, кожен прагне іноді з’їсти те, чого, мабуть, не варто було б. Чи варто? Що ми насправді знаємо про правильне харчування? Купа міфів, безліч легенд та ще більше заборон – саме це складає знання про здорове та правильне харчування. Ми – те, що ми їмо, і настав час розібратись, як же харчуватись правильно. Для цього вам знадобляться лише чотири прості кроки, і їх ви опануєте впродовж нашого курсу!','2017-08-30',13);

INSERT INTO `user`(user_name, first_name, middle_name, last_name) 
VALUES ('yura295','Юрій',NULL,'Решетник'),
('delphin','Устим','Володимир','Бучко'),
('rozhan228','Юрій','Лев','Рожанківський'),
('zavadka_bogdan','Богдан',NULL,'Завадка'),
('sage_of_six_pax','Остап',NULL,'Шийка'),
('the_best_man_alive','Павло','Захар','Турчиняк'),
('white_crow','Ілля',NULL,'Пасірський'),
('black_dog','Дмитро',NULL,'Кучер'),
('red_cat','Демя\'н','Роман','Бурда'),
('gray_rat','Катерина',NULL,'Льовушкіна'),
('purple_eagle','Анна',NULL,'Гринишин'),
('beutiful_girl','Соломія',NULL,'Сірак');

INSERT INTO course_author(course_id, author_id) 
VALUES (1,1),
(1,2),
(1,3),
(1,4),
(1,5),
(2,6),
(2,7),
(2,8),
(2,9),
(2,10),
(2,11);

INSERT INTO module(`name`, `text`, video, additional_information, order_position, course_id) 
VALUES ('Історія розвитку обчислювальної техніки','Вступ.','https://youtu.be/AAbfiV-61kI','Курс \"Основи програмування\" не має жодних обмежень за часом: всі матеріали та завдання залишаються відкритими після завершення публікацій. Таким чином, ви можете виконувати практичні завдання в будь-який зручний для вас час і отримаєте сертифікат незалежно від того, коли завершите навчання.',1,1),
('Алгоритми та алгоритмічні структури','Складання алгоритму отримання закордонного паспорту',NULL,'Наступне завдання не оцінюється і розміщене тут для того, щоб ви мали змогу попрактикуватися в роботі з автоматичним тестувальником завдань.',2,1),
('Продовження знайомства з Python','Програма для обчислення факторіалу','https://youtu.be/dp_hThw3VnI',NULL,3,1),
('Алгоритмічні структури у мові Python','Розгалуження if .. else','https://youtu.be/8BF9NBN4tWI',NULL,5,1),
('Типи даних Python','Прості типи даних','https://youtu.be/P9wPqsWqDvQ',NULL,2,1),
('Модульність в программі','Концепція модульності','https://youtu.be/gjPBSL9mtu0',NULL,1,1),
('Об\'єктно-орієнтоване програмування','Парадигми програмування','https://youtu.be/mwM4CHHIFIQ',NULL,5,1),
('Види алгоритмів','Проблема вибору алгориму','https://youtu.be/3-Cj-cSx2Rw',NULL,7,1),
('Як регулюється вплив сільського господарства на довкілля в Україні та ЄС',NULL,'https://youtu.be/jA0Qa3OKCZs','Законодавство України https://zakon.rada.gov.ua/laws',2,2),
('Зменшення впливу сільського господарства на довкілля і здоров`я: кращі практики',NULL,'https://youtu.be/tHp15YJeFxk','Огляд стану ґрунтів орних земель України - приклад степової та лісостепової зони (матеріал англійською) FAO. 2020. Overview of soil conditions of arable land in Ukraine – Study case for steppe and forest-steppe zones. Budapest. https://doi.org/10.4060/ca7761en Сахара по-українськи: як не допустити опустелювання в Україні? https://rubryka.com/blog/sahara-po-ukrayinsky/ ',1,2);

INSERT INTO `test`(`name`, introduction, order_position, course_id) 
VALUES ('Тест','Будь ласка, зверніть увагу! Це завдання на оцінку, яке буде враховуватися для отримання сертифікату: ви можете здобути до 7 балів за його виконання.',1,1),
('Тест за матеріалами лекції','Будь ласка, зверніть увагу! Це завдання на оцінку, яка буде враховуватися для отримання сертифікату: ви можете здобути до 3 балів за його виконання.',2,2),
('Тест за матеріалами лекції','Будь ласка, зверніть увагу! Це завдання на оцінку, яке буде враховуватися для отримання сертифікату: ви можете здобути до 3 балів за його виконання.',3,3),
('Інструкція з використання','Будь ласка, зверніть увагу! Далі опубліковані завдання на оцінку, яка буде враховуватися для отримання сертифікату. Виконуючи їх, дотримуйтеся наступних вимог. Бали, втрачені за неуважністю, не повертаються.',4,4),
('Інструкція з використання','Будь ласка, зверніть увагу! Далі опубліковані завдання на оцінку, яка буде враховуватися для отримання сертифікату. Виконуючи їх, дотримуйтеся наступних вимог. Бали, втрачені за неуважністю, не повертаються.',5,5),
('Інструкція з використання','Будь ласка, зверніть увагу! Далі опубліковані завдання на оцінку, яка буде враховуватися для отримання сертифікату. Виконуючи їх, дотримуйтеся наступних вимог. Бали, втрачені за неуважністю, не повертаються.',6,6),
('Інструкція з використання','Будь ласка, зверніть увагу! Далі опубліковані завдання на оцінку, яка буде враховуватися для отримання сертифікату. Виконуючи їх, дотримуйтеся наступних вимог. Бали, втрачені за неуважністю, не повертаються.',7,7),
('Інструкція з використання','Будь ласка, зверніть увагу! Далі опубліковані завдання на оцінку, яка буде враховуватися для отримання сертифікату. Виконуючи їх, дотримуйтеся наступних вимог. Бали, втрачені за неуважністю, не повертаються.',7,7),
('Тест',NULL,7,7),
('Тест',NULL,8,8);

INSERT INTO `question`(question, photo, test_id) 
VALUES ('Що таке ЕОМ?',NULL,1),
('Чому людство користується саме десятковою системою числення?',NULL,1),
('нет ответа',NULL,1),
('Що являє собою перфокарта?',NULL,1),
('В чому полягала інноваційність аналітичної машини Чарльза Беббіджа?',NULL,1),
('Кого вважають першим програмістом?',NULL,1),
('Як називалася перша в світі програмована ЕОМ?',NULL,1),
('Де і ким була побудована перша ЕОМ в СРСР?',NULL,1),
('Перший масовий персональний комп\'ютер створено фірмою',NULL,1),
('Яке покоління ЕОМ триває зараз?',NULL,1),
('Яке покоління ЕОМ триває зараз?',NULL,1),
('Скільки буде 256 у трійковій системі числення?',NULL,1),
('Скільки буде 64 у двійковій системі числення?',NULL,1);

INSERT INTO `answer_options`(`option`, is_answer, question_id) 
VALUES ('Електрична обчислювальна машина',0,1),
('Електрична обчислювальна машина',1,1),
('Елемент обчислювання математичний',0,1),
('Елементарний обчислювальний масив',0,1),
('Тому що для підняття блоку Великої піраміди Хеопса необхідно було 10 рабів',1,2),
('Тому що у людини 10 пальців і рахувати зручно до десяти',0,2),
('Тому що це зручно, так як десятки складаються у сотні, сотні у тисячі і т.д.',0,2),
('Тому що мозок людини так само працює у десятковій системі',0,2),
('Антикітерський механізм',0,3),
('Рахівницю',1,3),
('Ткацький верстат Жаккарда',0,3),
('Z1 Конрада Цузе',0,3),
('Пластиковий конверт із гнучким диском всередині',0,4),
('Прямокутний картонний листок із отворами',0,4),
('Паперова стрічка із отворами',0,4),
('Паперова стрічка із числами',1,4),
('Вперше в історії вона вміла заварювати каву замість секретаря',0,5),
('Вона вміла виконувати не лише елементарні арифметичні операції',0,5),
('Вона повинна була вирішувати різні задачі в залежності від програми',1,5),
('Вона використовувала перфокарти в якості джерела даних',0,5);

INSERT INTO `user_course`(user_id, course_id, start_time, last_time_visited) 
VALUES (1,1,'2022-11-17 16:27:00', '2022-11-20 23:21:00'),
(2,2,'2022-11-17 16:27:01', '2022-11-20 23:22:00'),
(3,1,'2022-11-17 16:28:00', '2022-11-20 23:23:00'),
(4,2,'2022-11-17 17:27:00', '2022-11-20 23:24:00'),
(5,2,'2022-11-17 10:56:00', '2022-11-20 23:25:00'),
(6,2,'2022-11-18 16:27:00', '2022-11-20 23:26:00'),
(7,1,'2022-12-17 16:27:00', '2022-11-20 23:27:00'),
(8,1,'2022-11-17 16:37:00', '2022-11-20 23:28:00'),
(9,1,'2021-04-17 15:27:00', '2022-11-20 23:29:00'),
(10,2,'2021-10-17 15:27:00', '2022-11-20 23:30:00'),
(11,1,'2019-11-17 16:27:00', '2022-11-20 23:31:00'),
(12,2,'2022-11-17 16:27:05', '2022-11-20 23:32:00');

INSERT INTO `user_private`(user_id, email, phone_number, `password`) 
VALUES (1, 'yura.reshetnyk295@gmail.com','380739852834','zjgyj6u4h'),
(2, 'bogdan228@gmail.com','380699834843','HDH5ujg'),
(3, 'sageofsixpack@gmail.com','380669734834','GG6jjJ'),
(4, 'white.cat584@gmail.com','380993162808','fdkln432f2'),
(5, 'gldjfdl252@gmail.com','380673242123','fdfw33r2f'),
(6, 'fddggg24422@gmail.com','380974859373','32fdf23f'),
(7, 'dadada232@gmail.com','380664394383','g53g4h65'),
(8, 'momlovee44@gmail.com','380504839438','j76j67'),
(9, 'fdffek33@gmail.com','380739239429','k6k68lk86k'),
(10, 'forkpokrk344@gmail.com','380938583844','k67k'),
(11, 'fdf3434fdf@gmail.com','380638473846','kkhgkt57GHj'),
(12, 'fdfd232dsfd23@gmail.com','380997438463','k46k86l57k');

INSERT INTO `user_progress`(begin_timestamp, end_timestamp, user_id, task_id) 
VALUES ('2022-11-11 10:00:00','2022-11-11 11:00:00',1,1),
('2022-10-21 07:30:00','2022-10-21 09:00:00',4,3),
('2022-10-21 14:30:00','2022-10-21 15:30:00',3,2),
('2022-09-21 07:30:00','2022-09-21 09:30:00',2,8),
('2022-10-20 08:30:00','2022-10-20 09:00:00',5,7),
('2021-06-21 17:30:00','2021-06-21 19:30:00',6,6),
('2022-10-21 07:30:00','2022-10-21 17:30:00',7,5),
('2021-06-21 16:30:00','2021-06-21 18:00:00',8,4),
('2021-06-21 15:30:00','2021-06-21 17:00:00',9,5),
('2022-10-21 07:30:00','2022-10-21 09:30:00',10,6),
('2022-10-21 05:30:00','2022-10-21 08:30:00',11,7),
('2022-11-11 10:00:00','2022-11-11 11:00:00',1,1),
('2022-10-21 07:30:00','2022-10-21 09:00:00',4,3),
('2022-10-21 14:30:00','2022-10-21 15:30:00',3,2),
('2022-09-21 07:30:00','2022-09-21 09:30:00',2,8),
('2022-10-20 08:30:00','2022-10-20 09:00:00',5,7),
('2021-06-21 17:30:00','2021-06-21 19:30:00',6,6),
('2022-10-21 07:30:00','2022-10-21 17:30:00',7,5),
('2021-06-21 16:30:00','2021-06-21 18:00:00',8,4),
('2021-06-21 15:30:00','2021-06-21 17:00:00',9,5),
('2022-10-21 07:30:00','2022-10-21 09:30:00',10,6),
('2022-10-21 05:30:00','2022-10-21 08:30:00',11,7);
