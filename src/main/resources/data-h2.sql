-- here we added dash '-h2' and that means that it will apply only for H2 database, so only for default profile from
-- appliaction-profile.yml


--This is Spring Boot feature - DML - Data Manipulation Language - need be place in data-h2.sql
--to apply that data-h2.sql file has to be place in classpath - resources
-- will be loaded on startup
INSERT INTO category (description) VALUES ('American');
INSERT INTO category (description) VALUES ('Italian');
INSERT INTO category (description) VALUES ('Mexican');
INSERT INTO category (description) VALUES ('Polish');

insert into unit_of_measure (description) values ('Teaspoon');
insert into unit_of_measure (description) values ('Tablespoon');
insert into unit_of_measure (description) values ('Cup');
insert into unit_of_measure (description) values ('Pinch');
insert into unit_of_measure (description) values ('Ounce');
insert into unit_of_measure (description) values ('Each');
insert into unit_of_measure (description) values ('Dash');
insert into unit_of_measure (description) values ('Pint');