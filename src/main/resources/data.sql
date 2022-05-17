INSERT INTO event(id, name, date_time, home_odd, draw_odd, away_odd, home_points, away_points, result) VALUES
(1, 'CocoJambo Warszawa : Jazda Uniejów', '2022-04-16 15:00:00', 1.95, 2.15, 2.55, null, null, null),
(2, 'Real Madryt : Chelsea Londyn', '2022-04-06 21:00:00', 1.7, 2.44, 2.65, 3, 1, 'HOME'),
(3, 'Izolator Boguchwała : Cobra Wężerów', '2022-04-04 15:00:00', 1.22, 2.85, 2.2, 2, 1, 'HOME');

INSERT INTO bet(id, stake, type, event_id) VALUES
(1, 50.00, 'HOME', 1),
(2, 150.00, 'HOME', 2),
(3, 40.00, 'AWAY', 3),
(4, 25.00, 'HOME', 3);

INSERT INTO user(id, username, password, name, last_name, email) VALUES
(1, 'darek', '{noop}asdf', 'Darek', 'Barek', 'db@op.pl' ),
(2, 'monika', '{noop}qwer', 'Monika', 'Ratownika', 'mr@gmail.com'),
(3, 'jozek', '{noop}sss', 'Jozek', 'Wózek', 'jw@onet.pl');

INSERT INTO user_role(user_id, role) VALUES
(1, 'ROLE_ADMIN'),
(2, 'ROLE_USER'),
(1, 'ROLE_USER'),
(3, 'ROLE_ADMIN'),
(3, 'ROLE_USER');