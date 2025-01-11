CREATE DATABASE IF NOT EXISTS eventsMaker_db;

USE eventsMaker_db;

CREATE TABLE IF NOT EXISTS user(
   `id` INT PRIMARY KEY,
    `username` VARCHAR(40) NOT NULL UNIQUE,
    `email` VARCHAR(50) NOT NULL UNIQUE,
    `password` VARCHAR(255) NOT NULL,
    FOREIGN KEY (id) REFERENCES credentials(id),
    constraint `ch_email` check (`email` regexp '[A-Za-z._0-9]+@[A-Za-z.]')
    ) ;

CREATE TABLE IF NOT EXISTS event(
    `id` INT PRIMARY KEY AUTO_INCREMENT,
    `title` VARCHAR(50) NOT NULL,
    `description` VARCHAR(1000) NOT NULL,
    `author_id` INT,
    `price` DECIMAL(19,2) NOT NULL,
    foreign key(`author_id`) references user(id)
    );

CREATE TABLE IF NOT EXISTS booked_event_to_user(
    user_id INT NOT NULL,
    event_id INT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES user(id),
    FOREIGN KEY (event_id) REFERENCES event(id),
    PRIMARY KEY (user_id, event_id)
    );

CREATE TABLE IF NOT EXISTS transactions(
   `id` INT AUTO_iNCREMENT PRIMARY KEY NOT NULL,
   `user_id` INT NOT NULL,
   `transaction` VARCHAR(100) NOT NULL,
    `status` VARCHAR(10) NOT NULL,
    FOREIGN KEY(`user_id`) REFERENCES user(id));