INSERT INTO Client (name)
VALUES
('Ivan'),
('Oleg'),
('Sveta'),
('Olena'),
('Mark'),
('Sasha'),
('Anton'),
('Oksana'),
('Marta'),
('Eva');


INSERT INTO Planet (id, name)
VALUES
('E10', 'Earth'),
('M20', 'Mars'),
('V30', 'Venus'),
('M40', 'Mercury'),
('S50', 'Saturn');

INSERT INTO Ticket
(created_at, client_id, from_planet_id, to_planet_id) VALUES
 ('2010-01-10 00:05:01', 1, 'E10', 'M20'),
 ('2011-02-11 23:15:02', 2, 'M20', 'V30'),
 ('2012-03-12 22:25:03', 3, 'V30', 'S50'),
 ('2013-04-13 21:35:04', 4, 'S50', 'M40'),
 ('2014-05-14 20:45:05', 5, 'M40', 'E10'),
 ('2015-06-15 19:55:06', 6, 'E10', 'S50'),
 ('2016-07-16 18:10:07', 7, 'S50', 'M40'),
 ('2017-08-17 17:20:08', 8, 'M40', 'V30'),
 ('2018-09-18 16:30:09', 9, 'V30', 'M20'),
 ('2019-10-19 15:40:10', 10, 'M20', 'E10');

