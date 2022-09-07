DROP DATABASE IF EXISTS it_team;

CREATE TABLE levels (
                          `id` int NOT NULL AUTO_INCREMENT,
                          `level` varchar(45) NOT NULL,
                          PRIMARY KEY (`id`),
                          KEY `level_grades_idx` (`level`)
);
INSERT INTO levels VALUES (1,'junior'),
                            (2,'middle'),
                            (3,'senior'),
                            (4,'manager');

CREATE TABLE positions (
                             `id` int NOT NULL AUTO_INCREMENT,
                             `position` varchar(45) NOT NULL,
                             PRIMARY KEY (`id`)
);
INSERT INTO positions VALUES (1,'Java'),
                               (2,'HTML/CSS/JS'),
                               (3,'C++'),
                               (4,'C#'),
                               (5,'Python'),
                               (6,'Ruby'),
                               (7,'PHP'),
                               (8,'DBA'),
                               (9,'QA_Automation'),
                               (10,'QA_Manual'),
                               (11,'Business Analyst');

CREATE TABLE roles (
                         `id` int NOT NULL AUTO_INCREMENT,
                         `role_types` varchar(45) NOT NULL,
                         PRIMARY KEY (`id`),
                         UNIQUE KEY `role_types_UNIQUE` (`role_types`)
);
INSERT INTO roles VALUES (1,'ADMIN'),
                           (2,'EMPLOYEE'),
                           (3,'CUSTOMER');

CREATE TABLE status_project (
                                  `id` int NOT NULL AUTO_INCREMENT,
                                  `status` varchar(45) NOT NULL,
                                  PRIMARY KEY (`id`)
);
INSERT INTO status_project VALUES (1,'NEW'),
                                    (2,'PREPARED'),
                                    (3,'ACTIVE'),
                                    (4,'CANCELLED'),
                                    (5,'FINISHED');

CREATE TABLE status_user (
                               `id` int NOT NULL AUTO_INCREMENT,
                               `status` varchar(45) NOT NULL,
                               PRIMARY KEY (`id`)
);
INSERT INTO status_user VALUES (1,'ACTIVE'),
                                 (2,'BLOCKED');

CREATE TABLE users (
                         `id` int NOT NULL AUTO_INCREMENT,
                         `name` varchar(45) NOT NULL,
                         `surname` varchar(45) NOT NULL,
                         `role_id` int NOT NULL,
                         `email` varchar(45) NOT NULL,
                         `password` varchar(45) NOT NULL,
                         `user_status_id` int NOT NULL,
                         PRIMARY KEY (`id`),
                         UNIQUE KEY `email_UNIQUE` (`email`),
                         KEY `role_type_idx` (`role_id`),
                         KEY `status_id_idx` (`user_status_id`),
                         CONSTRAINT `role_id` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`),
                         CONSTRAINT `status_id` FOREIGN KEY (`user_status_id`) REFERENCES `status_user` (`id`)
);
INSERT INTO users (name, surname, role_id, email, password, user_status_id)
values
    ("Ленка","Сидина",1,"sidina@tut.by","12345",1),
    ("Харрисон","Форд",3,"harrisonf@tut.by","12345",1),
    ("Джонни","Депп",3,"djonnyd@tut.by","12345",1),
    ("Леонардо","ДиКаприо",3,"leonardod@tut.by","12345",1),
    ("Киану","Ривз",3,"kianor@tut.by","12345",1),
    ("Уилл","Смит",3,"wills@tut.by","12345",1),
    ("Брэд","Питт",3,"bredp@tut.by","12345",1),
    ("Джим","Керри",3,"jimk@tut.by","12345",1),
    ("Мэттью","Макконехи",3,"metyum@tut.by","12345",1),
    ("Морган","Фримен",3,"morganf@tut.by","12345",1),
    ("Крис","Эванс",3,"krise@tut.by","12345",1),
    ("Сэмюэл","ЛеройДжексон",3,"semuell@tut.by","12345",1),
    ("Роберт","ДеНиро",3,"robertd@tut.by","12345",1),
    ("Том","Хэнкс",3,"tomh@tut.by","12345",1),
    ("Марк","Уолберг",3,"marku@tut.by","12345",1),
    ("Джейк","Джилленхол",3,"jaked@tut.by","12345",1),
    ("Брэдли","Купер",3,"bradlik@tut.by","12345",1),
    ("Вин","Дизель",3,"vind@tut.by","12345",1),
    ("Вуди","Харрельсон",3,"vuddih@tut.by","12345",1),
    ("Дензел","Вашингтон",3,"denzelv@tut.by","12345",1),
    ("Аль","Пачино",3,"alpachino@tut.by","12345",1),
    ("Том","Круз",3,"tomk@tut.by","12345",1),
    ("Брюс","Уиллис",3,"bryusw@tut.by","12345",1),
    ("Тоби","Магуайр",3,"tobym@tut.by","12345",1),
    ("Николас","Кейдж",3,"nikolask@tut.by","12345",1),
    ("Джеймс","Франко",3,"jamesf@tut.by","12345",1),
    ("Шарлиз","Терон",2,"sharlizt@tut.by","12345",1),
    ("Натали","Портман",2,"natalip@tut.by","12345",1),
    ("Марго","Робби",2,"margor@tut.by","12345",1),
    ("Анджелина","Джоли",2,"angelinad@tut.by","12345",1),
    ("Скарлетт","Йоханссон",2,"skarlety@tut.by","12345",1),
    ("Кэйт","Бекинсэйл",2,"kateb@tut.by","12345",1),
    ("Рэйчел","Макадамс",2,"rachelm@tut.by","12345",1),
    ("Пенелопа","Крус",2,"penelopak@tut.by","12345",1),
    ("Хелена","БонэмКартер",2,"helenab@tut.by","12345",1),
    ("Дженнифер","Энистон",2,"jeniffere@tut.by","12345",1),
    ("Кэтрин","ЗетаДжонс",2,"katrinz@tut.by","12345",1),
    ("Джулианна","Мур",2,"juliannam@tut.by","12345",1),
    ("Элизабет","Олсен",2,"elizamethu@tut.by","12345",1),
    ("Эмилия","Кларк",2,"amiliak@tut.by","12345",1),
    ("Сандра","Буллок",2,"sandrab@tut.by","12345",1),
    ("Энн","Хэтэуэй",2,"annh@tut.by","12345",1),
    ("Эмма","Уотсон",2,"emmau@tut.by","12345",1),
    ("Кира","Найтли",2,"kiran@tut.by","12345",1),
    ("Ума","Турман",2,"umat@tut.by","12345",1),
    ("Ева","Грин",2,"evag@tut.by","12345",1),
    ("Аманда","Сейфрид",2,"amandas@tut.by","12345",1),
    ("Мерил","Стрип",2,"merils@tut.by","12345",1),
    ("Кейт","Хадсон",2,"kateh@tut.by","12345",1),
    ("Дженнифер","Коннелли",2,"jenifferk@tut.by","12345",1),
    ("Николь","Кидман",2,"nikolk@tut.by","12345",1);

DROP TABLE IF EXISTS projects;
CREATE TABLE projects (
                            `id` int NOT NULL AUTO_INCREMENT,
                            `name` varchar(45) NOT NULL,
                            `start_date` date NOT NULL,
                            `end_date` date NOT NULL,
                            `project_status_id` int NOT NULL,
                            `requirement_comment` varchar(500) NOT NULL,
                            `customer_id` int NOT NULL,
                            PRIMARY KEY (`id`),
                            UNIQUE KEY `short_title_UNIQUE` (`name`),
                            KEY `project_status_id_idx` (`project_status_id`),
                            KEY `customer_id_idx` (`customer_id`),
                            CONSTRAINT `customer_id` FOREIGN KEY (`customer_id`) REFERENCES `users` (`id`),
                            CONSTRAINT `project_status_id` FOREIGN KEY (`project_status_id`) REFERENCES `status_project` (`id`)
);
INSERT INTO projects (name, start_date, end_date, project_status_id, requirement_comment, customer_id) VALUES
            ('Первый проект','2022-09-01','2022-09-23',2,'Seniors:12, Middles: 1, Juniors: 99; developer: Frontend, C++, C#, Java',2),
            ('Второй проект','2022-10-14','2022-10-22',2,'senior_quantity=2middle_quantity=3junior_quantity=4',2),
            ('Третий проект','2022-11-01','2022-12-14',2,'senior_quantity=6; middle_quantity=9; junior_quantity=1; ',3),
            ('Четвёртый проект','2022-12-01','2022-12-25',3,'C++, Java, Python; senior_quantity=1; middle_quantity=14; junior_quantity=1; ',4),
            ('Пятый проект','2022-10-01','2022-12-31',1,'HTML/CSS/JS, Java, Python, Ruby; senior_quantity=2; middle_quantity=5; junior_quantity=3; ',5),
            ('Шестой проект','2022-09-19','2023-09-20',1,'HTML/CSS/JS, Python; senior_quantity=1; middle_quantity=0; junior_quantity=0; ',6),
            ('Седьмой проект','2022-09-25','2023-02-12',2,'PHP, QA_Automation, BusinessAnalyst; senior_quantity=10; middle_quantity=0; junior_quantity=0; ',7),
            ('Восьмой проект','2023-01-10','2023-12-31',3,'HTML/CSS/JS; senior_quantity=1; middle_quantity=0; junior_quantity=0; ',8),
            ('Девятый проект','2023-01-01','2023-01-31',2,'C++, C#, Java; senior_quantity=2; middle_quantity=1; junior_quantity=1; ',9),
            ('Десятый проект','2022-12-13','2023-07-18',2,'HTML/CSS/JS; senior_quantity=1; middle_quantity=1; junior_quantity=1; ',10),
            ('11-ый проект','2022-07-25','2022-12-26',3,'HTML/CSS/JS; senior_quantity=1; middle_quantity=0; junior_quantity=0; ',11),
            ('12-ый прект','2022-08-11','2022-11-26',2,'HTML/CSS/JS, C++, C#; senior_quantity=1; middle_quantity=0; junior_quantity=1; ',12),
            ('13-ый проект','2022-09-25','2024-07-26',1,'DBA, QA_Automation, QA_Manual; senior_quantity=11; middle_quantity=1; junior_quantity=1; ',13),
            ('14-ый проект','2022-09-01','2024-09-01',1,'HTML/CSS/JS, C++, C#; senior_quantity=11; middle_quantity=1; junior_quantity=1; ',14),
            ('15-ый проект','2022-09-03','2022-12-31',2,'C++, Java, Python; senior_quantity=1; middle_quantity=2; junior_quantity=10; ',15);