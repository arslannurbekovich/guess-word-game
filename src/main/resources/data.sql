-- creating roles
INSERT INTO role (create_date, update_date, name)
VALUES (now(), null , 'ROLE_ADMIN');

INSERT INTO role (create_date, update_date, name)
VALUES (now(), null, 'ROLE_USER');

--creating admin
INSERT INTO users (create_date, update_date, full_name, password, username, is_deleted)
VALUES (now(), null, 'admin', '$2a$10$TFWhlXm59t/XS5XaOYfBkOuXW/Az7ui03.WtMSbvk6y7quZm9gidu', 'admin', false );
--password: admin

INSERT INTO users (create_date, update_date, full_name, password, username, is_deleted)
VALUES (now(), null, 'Айбек Жапаров', '$2a$10$DtuVqNRIFFoeETILwAdeAe83tFAAr.prOziCU0b5gnynW07HoFhNK', 'Aibek', false );
--password: 12345

INSERT INTO users (create_date, update_date, full_name, password, username, is_deleted)
VALUES (now(), null, 'Арслан Нурбеков', '$2a$10$hNaMZQK2oCv.S9bner9poOsCXUBTP02Gw/7NjgFtSZgNHfok.dABG', 'Arslan', false );
--password: ars

-- giving roles to admin
INSERT INTO users_roles (user_id, role_id) VALUES (1, 1);
INSERT INTO users_roles (user_id, role_id) VALUES (2, 2);
INSERT INTO users_roles (user_id, role_id) VALUES (3, 2);

-- creating questions
INSERT INTO questions (create_date, update_date, answer, name, is_deleted)
VALUES (now(), null, 'Землетрясения', 'Какое стихийное бедствие измеряется по шкале Рихтера?', false );

INSERT INTO questions (create_date, update_date, answer, name, is_deleted)
VALUES (now(), null, 'Меркурий', 'Какая планета находится ближе всего к Солнцу?', false );

INSERT INTO questions (create_date, update_date, answer, name, is_deleted)
VALUES (now(), null, 'Samsung', 'Как называется крупнейшая технологическая компания в Южной Корее?', false );

INSERT INTO questions (create_date, update_date, answer, name, is_deleted)
VALUES (now(), null, 'адыгене', 'Из какого племени Алымбек датка?', false );

INSERT INTO questions (create_date, update_date, answer, name, is_deleted)
VALUES (now(), null, 'Эверест', 'Самая высокая горная вершина мира?', false );

INSERT INTO questions (create_date, update_date, answer, name, is_deleted)
VALUES (now(), null, 'Железо', 'Fe — это символ какого химического элемента?', false );

INSERT INTO questions (create_date, update_date, answer, name, is_deleted)
VALUES (now(), null, 'Microsoft', 'Какую компанию основал Билл Гейтс?', false );
