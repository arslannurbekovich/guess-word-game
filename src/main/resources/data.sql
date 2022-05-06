-- creating roles
INSERT INTO role (create_date, update_date, name)
VALUES (now(), now(), 'ROLE_ADMIN');

INSERT INTO role (create_date, update_date, name)
VALUES (now(), now(), 'ROLE_USER');

--creating admin
INSERT INTO users (create_date, update_date, full_name, password, username, is_deleted)
VALUES (now(), now(), 'admin', '$2a$10$TFWhlXm59t/XS5XaOYfBkOuXW/Az7ui03.WtMSbvk6y7quZm9gidu', 'admin', false );

INSERT INTO users (create_date, update_date, full_name, password, username, is_deleted)
VALUES (now(), now(), 'Айбек Жапаров', '777777', 'Aibek', false );

INSERT INTO users (create_date, update_date, full_name, password, username, is_deleted)
VALUES (now(), now(), 'Арслан Нурбеков', '$123456', 'Arslan', false );

-- giving roles to admin
INSERT INTO users_roles (user_id, role_id) VALUES (1, 1);
INSERT INTO users_roles (user_id, role_id) VALUES (2, 2);
INSERT INTO users_roles (user_id, role_id) VALUES (3, 2);

-- creating questions
INSERT INTO questions (create_date, update_date, answer, name)
VALUES (now(), now(), 'Землетрясения', 'Какое стихийное бедствие измеряется по шкале Рихтера?');

INSERT INTO questions (create_date, update_date, answer, name)
VALUES (now(), now(), 'Меркурий', 'Какая планета находится ближе всего к Солнцу?');

INSERT INTO questions (create_date, update_date, answer, name)
VALUES (now(), now(), 'Samsung', 'Как называется крупнейшая технологическая компания в Южной Корее?');

INSERT INTO questions (create_date, update_date, answer, name)
VALUES (now(), now(), 'адыгене', 'Из какого племени Алымбек датка?');

INSERT INTO questions (create_date, update_date, answer, name)
VALUES (now(), now(), 'Эверест', 'Самая высокая горная вершина мира?');

INSERT INTO questions (create_date, update_date, answer, name)
VALUES (now(), now(), 'Железо', 'Fe — это символ какого химического элемента?');

INSERT INTO questions (create_date, update_date, answer, name)
VALUES (now(), now(), 'Microsoft', 'Какую компанию основал Билл Гейтс?');
