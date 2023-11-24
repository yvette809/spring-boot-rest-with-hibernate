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

# create places table
DROP TABLE IF EXISTS place;

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
                       FOREIGN KEY (category_id) REFERENCES category(id),
                       FOREIGN KEY (user_id) REFERENCES user(id)
) ENGINE=InnoDB;

INSERT INTO place (name, category_id, user_id, status, updated, created, description, coordinates)
VALUES ('Stockholm', 1, 2,'private', NOW(), NOW(), 'A very clean city', POINT(10.12345, 20.56789));


SELECT id, name, ST_AsText(coordinates) AS coordinates_text FROM place;


# table for users
DROP TABLE IF EXISTS user;
CREATE TABLE user (
                      id INT AUTO_INCREMENT,
                      username varchar(50) NOT NULL ,
                      password varchar(255) NOT NULL ,
                      enabled boolean NOT NULL DEFAULT true,
                      PRIMARY KEY (id)
);

create table authorities (
                             username INT NOT NULL ,
                             authority varchar(50) NOT NULL ,
                             constraint fk_authorities_users foreign key(username) references user(username)
);


INSERT INTO user(username, password)
VALUES
    ('yvette', 'ub021299'),
    ('Favour', 'ub021679'),
    ('Nathan', 'rx021299');


INSERT INTO authorities(username, authority)
VALUES
    ('yvette', 'ROLE_ADMIN'),
    ('Favour', 'ROLE_USER'),
    ('Nathan', 'ROLE_USER')

