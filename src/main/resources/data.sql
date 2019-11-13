--This is Spring Boot feature - DML - Data Manipulation Language - need be place in data.sql
--to apply that data.sql file has to be place in classpath - resources
-- will be loaded on startup
INSERT INTO category (description) VALUES ('American');
INSERT INTO category (description) VALUES ('Italian');
INSERT INTO category (description) VALUES ('Mexican');
INSERT INTO category (description) VALUES ('Polish');

insert into unit_of_measure (description) values ('Teaspoon');
insert into unit_of_measure (description) values ('Tabespoon');
insert into unit_of_measure (description) values ('Cup');
insert into unit_of_measure (description) values ('Pinch');
insert into unit_of_measure (description) values ('Ounce');