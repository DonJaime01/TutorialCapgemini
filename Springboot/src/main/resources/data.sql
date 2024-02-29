INSERT INTO category(name) VALUES ('Eurogames');
INSERT INTO category(name) VALUES ('Ameritrash');
INSERT INTO category(name) VALUES ('Familiar');

INSERT INTO author(name, nationality) VALUES ('Alan R. Moon', 'US');
INSERT INTO author(name, nationality) VALUES ('Vital Lacerda', 'PT');
INSERT INTO author(name, nationality) VALUES ('Simone Luciani', 'IT');
INSERT INTO author(name, nationality) VALUES ('Perepau Llistosella', 'ES');
INSERT INTO author(name, nationality) VALUES ('Michael Kiesling', 'DE');
INSERT INTO author(name, nationality) VALUES ('Phil Walker-Harding', 'US');

INSERT INTO game(title, age, category_id, author_id) VALUES ('On Mars', '14', 1, 2);
INSERT INTO game(title, age, category_id, author_id) VALUES ('Aventureros al tren', '8', 3, 1);
INSERT INTO game(title, age, category_id, author_id) VALUES ('1920: Wall Street', '12', 1, 4);
INSERT INTO game(title, age, category_id, author_id) VALUES ('Barrage', '14', 1, 3);
INSERT INTO game(title, age, category_id, author_id) VALUES ('Los viajes de Marco Polo', '12', 1, 3);
INSERT INTO game(title, age, category_id, author_id) VALUES ('Azul', '8', 3, 5);

INSERT INTO client(name) VALUES ('Alberto');
INSERT INTO client(name) VALUES ('Jorge');
INSERT INTO client(name) VALUES ('Juan');
INSERT INTO client(name) VALUES ('Roberto');

INSERT INTO loan(game_id, client_id, init_date, end_date) VALUES (1, 3, '2024-02-23', '2024-05-12');
INSERT INTO loan(game_id, client_id, init_date, end_date) VALUES (5, 1, '2024-01-13', '2024-01-29');
INSERT INTO loan(game_id, client_id, init_date, end_date) VALUES (6, 3, '2023-11-11', '2024-03-09');
INSERT INTO loan(game_id, client_id, init_date, end_date) VALUES (2, 2, '2024-08-30', '2024-10-01');