-- Création de la base de données
CREATE DATABASE IF NOT EXISTS `test`;
USE `test`;

-- Création de la table classes
CREATE TABLE IF NOT EXISTS `classes` (
                                         `class_id` int NOT NULL AUTO_INCREMENT,
                                         `class_name` varchar(50) NOT NULL,
    `class_level` varchar(50) NOT NULL,
    PRIMARY KEY (`class_id`)
    ) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Création de la table students
CREATE TABLE IF NOT EXISTS `students` (
                                          `student_id` int NOT NULL AUTO_INCREMENT,
                                          `first_name` varchar(50) NOT NULL,
    `last_name` varchar(50) NOT NULL,
    `date_of_birth` date NOT NULL,
    `class_id` int DEFAULT NULL,
    `photo_url` varchar(255) DEFAULT NULL,
    PRIMARY KEY (`student_id`),
    KEY `class_id` (`class_id`),
    CONSTRAINT `students_ibfk_1` FOREIGN KEY (`class_id`) REFERENCES `classes` (`class_id`)
    ) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Création de la table annual_grades
CREATE TABLE IF NOT EXISTS `annual_grades` (
    `annual_grade_id` int NOT NULL AUTO_INCREMENT,
                                               `student_id` int NOT NULL,
                                               `year` int NOT NULL,
                                               `final_grade` decimal(5,2) DEFAULT NULL,
    PRIMARY KEY (`annual_grade_id`),
    KEY `student_id` (`student_id`),
    CONSTRAINT `annual_grades_ibfk_1` FOREIGN KEY (`student_id`) REFERENCES `students` (`student_id`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Création de la table education_levels
CREATE TABLE IF NOT EXISTS `education_levels` (
                                                  `education_level_id` int NOT NULL AUTO_INCREMENT,
                                                  `level_name` varchar(100) NOT NULL,
    `student_id` int NOT NULL,
    PRIMARY KEY (`education_level_id`),
    UNIQUE KEY `student_id` (`student_id`),
    CONSTRAINT `education_levels_ibfk_1` FOREIGN KEY (`student_id`) REFERENCES `students` (`student_id`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE IF NOT EXISTS `modules` (
                                         `module_id` int NOT NULL AUTO_INCREMENT,
                                         `module_name` varchar(100) NOT NULL,
    `class_id` int DEFAULT NULL,
    PRIMARY KEY (`module_id`),
    KEY `class_id` (`class_id`),
    CONSTRAINT `modules_ibfk_1` FOREIGN KEY (`class_id`) REFERENCES `classes` (`class_id`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Création de la table subjects
CREATE TABLE IF NOT EXISTS `subjects` (
                                          `subject_id` int NOT NULL AUTO_INCREMENT,
                                          `subject_name` varchar(100) NOT NULL,
    `class_id` int DEFAULT NULL,
    PRIMARY KEY (`subject_id`),
    KEY `class_id` (`class_id`),
    CONSTRAINT `subjects_ibfk_1` FOREIGN KEY (`class_id`) REFERENCES `classes` (`class_id`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Création de la table exams
CREATE TABLE IF NOT EXISTS `exams` (
                                       `exam_id` int NOT NULL AUTO_INCREMENT,
                                       `module_id` int NOT NULL,
                                       `exam_name` varchar(100) NOT NULL,
    `exam_date` date NOT NULL,
    PRIMARY KEY (`exam_id`),
    KEY `module_id` (`module_id`),
    CONSTRAINT `exams_ibfk_1` FOREIGN KEY (`module_id`) REFERENCES `modules` (`module_id`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Création de la table exam_grades
CREATE TABLE IF NOT EXISTS `exam_grades` (
                                             `exam_grade_id` int NOT NULL AUTO_INCREMENT,
                                             `exam_id` int NOT NULL,
                                             `student_id` int NOT NULL,
                                             `grade` decimal(5,2) DEFAULT NULL,
    PRIMARY KEY (`exam_grade_id`),
    KEY `exam_id` (`exam_id`),
    KEY `student_id` (`student_id`),
    CONSTRAINT `exam_grades_ibfk_1` FOREIGN KEY (`exam_id`) REFERENCES `exams` (`exam_id`),
    CONSTRAINT `exam_grades_ibfk_2` FOREIGN KEY (`student_id`) REFERENCES `students` (`student_id`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Création de la table grades
CREATE TABLE IF NOT EXISTS `grades` (
                                        `grade_id` int NOT NULL AUTO_INCREMENT,
                                        `student_id` int NOT NULL,
                                        `subject_id` int NOT NULL,
                                        `score` decimal(5,2) DEFAULT NULL,
    PRIMARY KEY (`grade_id`),
    KEY `student_id` (`student_id`),
    KEY `subject_id` (`subject_id`),
    CONSTRAINT `grades_ibfk_1` FOREIGN KEY (`student_id`) REFERENCES `students` (`student_id`),
    CONSTRAINT `grades_ibfk_2` FOREIGN KEY (`subject_id`) REFERENCES `subjects` (`subject_id`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;



-- Création de la table roles
CREATE TABLE IF NOT EXISTS `roles` (
                                       `role_id` int NOT NULL AUTO_INCREMENT,
                                       `role_name` varchar(50) NOT NULL,
    PRIMARY KEY (`role_id`),
    UNIQUE KEY `role_name` (`role_name`)
    ) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Création de la table semester_grades
CREATE TABLE IF NOT EXISTS `semester_grades` (
                                                 `semester_grade_id` int NOT NULL AUTO_INCREMENT,
                                                 `student_id` int NOT NULL,
                                                 `semester` varchar(50) NOT NULL,
    `year` int NOT NULL,
    `final_grade` decimal(5,2) DEFAULT NULL,
    PRIMARY KEY (`semester_grade_id`),
    KEY `student_id` (`student_id`),
    CONSTRAINT `semester_grades_ibfk_1` FOREIGN KEY (`student_id`) REFERENCES `students` (`student_id`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;



-- Création de la table users
CREATE TABLE IF NOT EXISTS `users` (
                                       `user_id` int NOT NULL AUTO_INCREMENT,
                                       `username` varchar(50) NOT NULL,
    `password` varchar(255) NOT NULL,
    `role_id` int DEFAULT NULL,
    PRIMARY KEY (`user_id`),
    UNIQUE KEY `username` (`username`),
    KEY `role_id` (`role_id`),
    CONSTRAINT `users_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `roles` (`role_id`)
    ) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

