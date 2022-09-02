DROP DATABASE IF EXISTS it_team;

CREATE TABLE `levels` (
                          `id` int NOT NULL AUTO_INCREMENT,
                          `level` varchar(45) NOT NULL,
                          PRIMARY KEY (`id`),
                          KEY `level_grades_idx` (`level`)
);
INSERT INTO `levels` VALUES (1,'junior'),
                            (2,'middle'),
                            (3,'senior'),
                            (4,'manager');

CREATE TABLE `positions` (
                             `id` int NOT NULL AUTO_INCREMENT,
                             `position` varchar(45) NOT NULL,
                             PRIMARY KEY (`id`)
);
INSERT INTO `positions` VALUES (1,'Java'),
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

CREATE TABLE `roles` (
                         `id` int NOT NULL AUTO_INCREMENT,
                         `role_types` varchar(45) NOT NULL,
                         PRIMARY KEY (`id`),
                         UNIQUE KEY `role_types_UNIQUE` (`role_types`)
);
INSERT INTO `roles` VALUES (1,'ADMIN'),
                           (2,'EMPLOYEE'),
                           (3,'CUSTOMER');

CREATE TABLE `status_project` (
                                  `id` int NOT NULL AUTO_INCREMENT,
                                  `status` varchar(45) NOT NULL,
                                  PRIMARY KEY (`id`)
);
INSERT INTO `status_project` VALUES (1,'NEW'),
                                    (2,'PREPARED'),
                                    (3,'ACTIVE'),
                                    (4,'CANCELLED'),
                                    (5,'FINISHED');

CREATE TABLE `status_user` (
                               `id` int NOT NULL AUTO_INCREMENT,
                               `status` varchar(45) NOT NULL,
                               PRIMARY KEY (`id`)
);
INSERT INTO `status_user` VALUES (1,'ACTIVE'),
                                 (2,'BLOCKED');

CREATE TABLE `users` (
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
INSERT INTO `users` VALUES (1,'Харрисон','Форд',3,'harrisonf@tut.by','12345',1),
                           (2,'Джонни','Депп',3,'djonnyd@tut.by','12345',1),
                           (3,'Леонардо','ДиКаприо',3,'leonardod@tut.by','12345',1),
                           (4,'Киану','Ривз',3,'kianor@tut.by','12345',1),
                           (5,'Уилл','Смит',3,'wills@tut.by','12345',1),
                           (6,'Брэд','Питт',3,'bredp@tut.by','12345',1),
                           (7,'Джим','Керри',3,'jimk@tut.by','12345',1),
                           (8,'Мэттью','Макконехи',3,'metyum@tut.by','12345',1),
                           (9,'Морган','Фримен',3,'morganf@tut.by','12345',1),
                           (10,'Крис','Эванс',3,'krise@tut.by','12345',1),
                           (11,'Сэмюэл','ЛеройДжексон',3,'semuell@tut.by','12345',1),
                           (12,'Роберт','ДеНиро',3,'robertd@tut.by','12345',1),
                           (13,'Том','Хэнкс',3,'tomh@tut.by','12345',1),
                           (14,'Марк','Уолберг',3,'marku@tut.by','12345',1),
                           (15,'Джейк','Джилленхол',3,'jaked@tut.by','12345',1),
                           (16,'Брэдли','Купер',3,'bradlik@tut.by','12345',1),
                           (17,'Вин','Дизель',3,'vind@tut.by','12345',1),
                           (18,'Вуди','Харрельсон',3,'vuddih@tut.by','12345',1),
                           (19,'Дензел','Вашингтон',3,'denzelv@tut.by','12345',1),
                           (20,'Аль','Пачино',3,'alpachino@tut.by','12345',1),
                           (21,'Том','Круз',3,'tomk@tut.by','12345',1),
                           (22,'Брюс','Уиллис',3,'bryusw@tut.by','12345',1),
                           (23,'Тоби','Магуайр',3,'tobym@tut.by','12345',1),
                           (24,'Николас','Кейдж',3,'nikolask@tut.by','12345',1),
                           (25,'Джеймс','Франко',3,'jamesf@tut.by','12345',1);
