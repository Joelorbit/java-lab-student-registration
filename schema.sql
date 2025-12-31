-- 1. Create the database
CREATE DATABASE student_db;

-- 2. Switch to that database
USE student_db;

-- 3. Create the table exactly as per your lab PDF
CREATE TABLE students (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR
(100) NOT NULL,
    email VARCHAR
(100) NOT NULL UNIQUE,
    year INT NOT NULL
);