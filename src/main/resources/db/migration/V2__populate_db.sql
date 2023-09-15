INSERT INTO Client (name) VALUES
('Chuck Lorre'),
('Leonard Hofstadter'),
('Sheldon Cooper'),
('Penny Hofstadter'),
('Rajesh Koothrappali'),
('Leslie Winkle'),
('Bernadette Rostenkowski-Wolowitz'),
('Howard Wolowitz'),
('Amy Farrah Fowler'),
('Stuart Bloom');

INSERT INTO Planet (id, name) VALUES
('MERC1', 'Mercury'),
('VEN2', 'Venus'),
('EAR3', 'Earth'),
('MARS4', 'Mars'),
('JUP5', 'Jupiter'),
('SAT6', 'Saturn');

INSERT INTO Ticket (client_id, from_planet_id,  to_planet_id ) VALUES
(1,'EAR3', 'MERC1'),
(2,'EAR3', 'VEN2'),
(3,'EAR3', 'MARS4'),
(4,'VEN2', 'MERC1'),
(5,'MARS4', 'VEN2'),
(6,'VEN2', 'SAT6'),
(7,'JUP5', 'MERC1'),
(8,'MERC1', 'EAR3'),
(9,'SAT6', 'MARS4'),
(10,'SAT6', 'VEN2')
--(11,'MERC1', 'VEN2'),
--(12,'URN7', 'VEN2'),
--(13,'JUP5', 'URN7')
;