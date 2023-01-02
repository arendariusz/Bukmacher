INSERT INTO event(id, name, date_time, home_odd, draw_odd, away_odd, home_points, away_points, result) VALUES
(1, 'CocoJambo Warszawa : Jazda Uniejów', '2022-04-16 15:00:00', 1.95, 2.15, 2.55, null, null, null),
(2, 'Real Madryt : Chelsea Londyn', '2022-04-06 21:00:00', 1.7, 2.44, 2.65, 3, 1, 'HOME'),
(3, 'Izolator Boguchwała : Cobra Wężerów', '2023-02-16 15:00:00', 1.22, 2.85, 2.2, null, null, null),
(4, 'Czarni Grzegorzowice - Orzeł Iwanowice', '2023-05-11', 1.67, 2.5, 2.1, null, null, null);


INSERT INTO user(id, username, password, name, last_name, email) VALUES
(1, 'darek', '{noop}asdf', 'Darek', 'Barek', 'db@op.pl' ),
(2, 'monika', '{noop}qwer', 'Monika', 'Ratownika', 'mr@gmail.com'),
(3, 'jozek', '{noop}sss', 'Jozek', 'Wózek', 'jw@onet.pl');

INSERT INTO bet(id, stake, type, event_id, user_id) VALUES
(1, 50.00, 'HOME', 1, 1),
(2, 150.00, 'HOME', 2, 2),
(3, 40.00, 'AWAY', 3, 1),
(4, 25.00, 'HOME', 3, 1);

INSERT INTO user_role(user_id, role) VALUES
(1, 'ROLE_ADMIN'),
(2, 'ROLE_USER'),
(1, 'ROLE_USER'),
(3, 'ROLE_ADMIN'),
(3, 'ROLE_USER');