insert into countries (id, name, capital)
values          (1, 'Italy', 'Rome'),
                (2, 'Hawaii', 'Honolulu'),
                (3, 'Japan', 'Tokyo'),
                (4, 'USA', 'Washington'),
                (5, 'Kabardino-Balkarian Republic', 'Nalchik'),
                (6, 'Mexico', 'Mexico City'),
                (7, 'Iceland', 'Reykjavik'),
                (8, 'Russia', 'Moscow'),
                (9, 'Tanzania', 'Dodoma'),
                (10, 'Ross Island', 'Ross'),
                (11, 'Indonesia', 'Jakarta'),
                (12, 'Guatemala', 'Guatemala City'),
                (13, 'Ecuador', 'Quito'),
                (14, 'Martinique', 'Fort-de-France');

insert into volcanoes (id, name, elevation, volcano_type, is_active, last_eruption, country_id)
values            (1, 'Mount Vesuvius', 1281, 'STRATOVOLCANO', 'true', '2021-01-01', 1),
                  (2, 'Mauna Loa', 4169, 'SHIELD_VOLCANO', true, '2022-03-15', 2),
                  (3, 'Mount Fuji', 3776, 'STRATOVOLCANO', false, '1707-12-16', 3),
                  (4, 'Mount St. Helens', 2549, 'STRATOVOLCANO', false, '1980-05-18', 4),
                  (5, 'Mount Kilimanjaro', 5895, 'STRATOVOLCANO', true, null, 9),
                  (6, 'Klyuchevskaya Sopka', 4754, 'STRATOVOLCANO', true, '2022-02-28', 8),
                  (7, 'Popocatepetl', 5393, 'STRATOVOLCANO', true, '2023-04-05', 6),
                  (8, 'Mount Elbrus', 5642, 'STRATOVOLCANO', false, null, 5),
                  (9, 'Eyjafjallajokull', 1666, 'STRATOVOLCANO', true, '2010-06-01', 7),
                  (10, 'Mount Erebus', 3794, 'STRATOVOLCANO', true, null, 10),
                  (11, 'Mount Rainier', 4392, 'STRATOVOLCANO', true, null, 4),
                  (12, 'Piton de la Fournaise', 2632, 'SHIELD_VOLCANO', true, '2022-10-05', 12),
                  (13, 'Mount Etna', 3357, 'STRATOVOLCANO', true, '2023-11-11', 1),
                  (14, 'Mount Shasta', 4322, 'STRATOVOLCANO', false, null, 4),
                  (15, 'Mount Ijen', 2799, 'STRATOVOLCANO', true, '2019-01-01', 11),
                  (16, 'Mount Rinjani', 3726, 'STRATOVOLCANO', true, '2016-11-01', 11),
                  (17, 'Mount Merapi', 2884, 'STRATOVOLCANO', true, '2023-12-03', 11),
                  (18, 'Mount Tambora', 2850, 'STRATOVOLCANO', true, '1967-01-10', 11),
                  (19, 'Mount Pelee', 1397, 'STRATOVOLCANO', true, '1932-01-01', 14),
                  (20, 'Pacaya', 2552, 'STRATOVOLCANO', true, '2023-01-08', 12),
                  (21, 'Novarupta', 841, 'STRATOVOLCANO', true, '1912-10-02', 4),
                  (22, 'Cotopaxi', 5897, 'STRATOVOLCANO', true, '2022-04-20', 13),
                  (23, 'Mount Cleveland', 1730, 'STRATOVOLCANO', true, '2017-05-17', 4),
                  (24, 'Santa Maria', 3772, 'STRATOVOLCANO', true, '2021-08-14', 12),
                  (25, 'Mount Aniakchak', 1290, 'CALDERA', true, '1931-06-06', 4),
                  (26, 'Paricutin', 2800, 'CINDER_CONE', false, '2021-02-14', 6),
                  (27, 'Mount Jefferson', 3199, 'STRATOVOLCANO', true, '2022-05-19', 4),
                  (28, 'Wizard Island', 2300, 'CINDER_CONE', true, '2023-02-10', 4),
                  (29, 'Mount Tehama', 3367, 'LAVA_DOME', false, null, 4),
                  (30, 'Mount Scott', 2758, 'CINDER_CONE', false, null, 4),
                  (31, 'Mono-Inyo Craters', 2692, 'LAVA_DOME', true, '1722-11-05', 4),
                  (32, 'Black Butte', 1945, 'CINDER_CONE', true, null, 4),
                  (33, 'Lassen Peak', 3187, 'LAVA_DOME', false, '1921-05-05', 4),
                  (34, 'Stromboli', 924, 'CINDER_CONE', true, '2014-08-03', 1),
                  (35, 'Mount Sinabung', 2460, 'STRATOVOLCANO', true, '2021-02-07', 11),
                  (36, 'Mount Semeru', 3676, 'STRATOVOLCANO', true, '2021-01-16', 11),
                  (37, 'Mount Agung', 3031, 'STRATOVOLCANO', true, '2019-11-26', 11),
                  (38, 'Krakatoa', 813, 'CALDERA', true, '2022-02-03', 11),
                  (39, 'Mount Kelud', 1731, 'STRATOVOLCANO', true, '2014-02-14', 11),
                  (40, 'Mount Bromo', 2329, 'STRATOVOLCANO', true, '2016-03-17', 11);


insert into volcanologists (id, first_name, last_name, salary, age, exploring_from, exploring_volcano_id)
values  (1, 'John', 'Doe', 5000.00, 55, '1987-01-15', 11),
                (2, 'Jane', 'Smith', 5500.00, 60, '1982-05-20', 20),
                (3, 'Michael', 'Johnson', 4800.00, 58, '1984-09-10', 33),
                (4, 'Emily', 'Brown', 5200.00, 56, '1986-11-25', 34),
                (5, 'Daniel', 'Martinez', 5100.00, 57, '1985-03-30', 15),
                (6, 'Sarah', 'Taylor', 4900.00, 49, '1983-07-05', 26),
                (7, 'Matthew', 'Anderson', 5300.00, 54, '1988-02-18', 17),
                (8, 'Jessica', 'Wilson', 5400.00, 33, '2003-06-12', 8),
                (9, 'Christopher', 'Thompson', 4700.00, 32, '2004-10-08', 39),
                (10, 'Amanda', 'Garcia', 4600.00, 31, '2012-04-14', 16),
                (11, 'David', 'Hernandez', 4700.00, 30, '2013-08-20', 21),
                (12, 'Ashley', 'Lopez', 5200.00, 49, '1993-12-25', 12),
                (13, 'James', 'Gonzalez', 5100.00, 28, '2020-01-30', 33),
                (14, 'Jennifer', 'Rodriguez', 5000.00, 27, '2015-07-05', 24),
                (15, 'Ryan', 'Perez', 4900.00, 26, '2020-11-10', 25),
                (16, 'Nicole', 'Sanchez', 4800.00, 25, '2017-03-15', 36),
                (17, 'Kevin', 'Ramirez', 5300.00, 54, '1998-05-20', 13),
                (18, 'Elizabeth', 'Torres', 5200.00, 43, '1999-09-25', 10),
                (19, 'William', 'Flores', 4700.00, 32, '2000-12-30', 31),
                (20, 'Megan', 'Rivera', 5400.00, 41, '2001-02-10', 29),
                (21, 'Jacob', 'Campbell', 4800.00, 53, '1989-11-15', 22),
                (22, 'Olivia', 'Carter', 5500.00, 69, '1993-08-20', 5),
                (23, 'Liam', 'Hill', 5200.00, 71, '1991-06-25', 4),
                (24, 'Emma', 'Yung', 5000.00, 28, '2014-04-30', 4),
                (25, 'Mason', 'Green', 5300.00, 30, '2021-02-05', 8),
                (26, 'Sophia', 'King', 4900.00, 32, '2000-01-10', 16),
                (27, 'Peter', 'Evans', 4700.00, 27, '2022-07-15', 7),
                (28, 'Isabella', 'Scott', 5100.00, 46, '1996-09-20', 1),
                (29, 'Ethan', 'Adams', 4600.00, 25, '2020-11-25', 3),
                (30, 'Amelia', 'Baker', 5400.00, 24, '2019-03-30', 9),
                (31, 'Alexander', 'Cook', 4700.00, 23, '2023-05-05', 38),
                (32, 'Marta', 'Gonzales', 5200.00, 42, '2000-07-10', 6),
                (33, 'Samantha', 'Nelson', 5100.00, 29, '2001-09-15', 17),
                (34, 'Benjamin', 'Mitchell', 5000.00, 74, '1988-10-20', 14),
                (35, 'Chloe', 'Roberts', 4900.00, 49, '1992-12-25', 40),
                (36, 'Dani', 'Leen', 4800.00, 28, '2014-03-30', 40),
                (37, 'Ava', 'Walker', 5300.00, 27, '2018-05-05', 22),
                (38, 'Madison', 'Hall', 5200.00, 66, '1996-07-10', 23),
                (39, 'Patrick', 'Lewis', 4700.00, 55, '1997-09-15', 2),
                (40, 'Abigail', 'Young', 5400.00, 24, '2021-11-20', 18);