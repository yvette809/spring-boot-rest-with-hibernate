CREATE DATABASE IF NOT EXISTS map;
USE map;

DROP TABLE IF EXISTS category;
CREATE TABLE category (
                          id INT AUTO_INCREMENT ,
                          name VARCHAR(50) NOT NULL,
                          symbol VARCHAR(50) NOT NULL,
                          description VARCHAR(255) NOT NULL,
                          PRIMARY KEY (id)
);

INSERT INTO category (name, symbol, description)
VALUES ('Tiko', '@', 'It is very hot');

CREATE TABLE place (
                       id INT AUTO_INCREMENT ,
                       name VARCHAR(50) NOT NULL,
                       category_id INT NOT NULL,
                       user_id INT NOT NULL,
                       status ENUM('private', 'public') NOT NULL DEFAULT 'public',
                       updated DATETIME,
                       created DATETIME,
                       description VARCHAR(255) NOT NULL,
                       coordinates POINT NOT NULL,
                       PRIMARY KEY (id),
                       FOREIGN KEY (category_id) REFERENCES category(id)
) ENGINE=InnoDB;

INSERT INTO place (name, category_id, user_id, status, updated, created, description, coordinates)
VALUES ('Stockholm', 1, 123,'private', NOW(), NOW(), 'A very clean city', POINT(10.12345, 20.56789));


SELECT id, name, ST_AsText(coordinates) AS coordinates_text FROM place;

