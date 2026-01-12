DROP TABLE IF EXISTS employee;

CREATE TABLE employee
(
    wwid  INTEGER PRIMARY KEY auto_increment,
    email     VARCHAR(128),
    firstname VARCHAR(128),
    lastname VARCHAR(256)
);

DROP TABLE IF EXISTS equipment;

CREATE TABLE equipment
(
    id  INTEGER PRIMARY KEY auto_increment,
    contact_email VARCHAR(128),
    name VARCHAR(128),
    provider VARCHAR(128)
);