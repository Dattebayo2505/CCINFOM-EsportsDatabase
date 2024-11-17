
-- REFERENCE ONLY, DON'T USE THIS FILE
DROP DATABASE IF EXISTS aokana;
CREATE DATABASE aokana;
USE aokana;

CREATE TABLE players (
	id INTEGER,
	first_name VARCHAR(255),
    last_name VARCHAR(255)
);

CREATE TABLE playstyles (
	id INTEGER,
    playstyle_name VARCHAR(255)
);

CREATE TABLE player_playstyles (
	player_id INTEGER,
    playstyle INTEGER
);

CREATE TABLE schools (
	id INTEGER,
    school_name VARCHAR(255)
);

CREATE TABLE schools_players (
	player_id INTEGER,
    school_id INTEGER
);

CREATE TABLE matches (
	p1_id INTEGER,
    p2_id INTEGER,
    p1_score INTEGER,
    p2_score INTEGER,
    match_date DATE
);

INSERT INTO players (`id`, `first_name`, `last_name`) 
VALUES 
(1000, "Hinata", "Masaya"),
(1001, "Asuka", "Kurashina"),
(1002, "Misaki", "Tobisawa"),
(1003, "Mashiro", "Arisaka"),
(1005, "Madoka", "Aoyagi"),
(1006, "Shion", "Aoyagi"),
(2000, "Rika", "Ichinose"),
(2001, "Kazunari", "Shindou"),
(2002, "Reiko", "Satou"),
(3000, "Saki", "Inui"),
(3001, "Irina", "Avalon"),
(4000, "Arika", "Okoze"),
(4001, "Mayu", "Ganeko"),
(5000, "Kasumi", "Kurobochi"),
(6000, "Aoi", "Kagami");

INSERT INTO playstyles (`id`, `playstyle_name`)
VALUES
(0, "All-rounder"),
(1, "Fighter"),
(2, "Speeder");

INSERT INTO player_playstyles (`player_id`, `playstyle`) 
VALUES 
(1000, 0),
(1001, 0),
(1002, 1),
(1003, 2),
(1006, 2),
(2000, 2),
(2001, 0),
(2002, 0),
(3000, 2),
(4000, 2),
(6000, 0);

INSERT INTO schools (`id`, `school_name`) 
VALUES 
(1, "Kunahama Academy"),
(2, "Takafuji Academy"),
(3, "Kairyou Academy"),
(4, "Shitou Suisan"),
(5, "Dougaura Kougyou");

INSERT INTO schools_players (`player_id`, `school_id`) 
VALUES 
(1000, 1),
(1001, 1),
(1002, 1),
(1003, 1),
(1005, 1),
(1006, 1),
(2000, 2),
(2001, 2),
(2002, 2),
(3000, 3),
(3001, 3),
(4000, 4),
(4001, 4),
(5000, 5);

INSERT INTO matches (`p1_id`, `p2_id`, `p1_score`, `p2_score`, `match_date`) 
VALUES 
(1000, 1001, 5, 3, '2024-01-15'),
(1002, 1003, 7, 6, '2024-02-10'),
(2000, 2001, 4, 8, '2024-03-05'),
(3000, 3001, 9, 10, '2024-03-25'),
(4000, 4001, 2, 1, '2024-04-12'),
(1005, 1006, 8, 4, '2024-05-01'),
(2002, 3000, 6, 7, '2024-05-15'),
(1001, 2001, 3, 5, '2024-06-20'),
(3001, 4000, 10, 9, '2024-07-10'),
(5000, 6000, 1, 2, '2024-08-08'),
(1000, 2000, 3, 4, '2024-08-15'),
(1002, 1004, 8, 2, '2024-09-10'),
(2001, 3000, 7, 5, '2024-09-25'),
(3001, 4001, 4, 6, '2024-10-01'),
(4000, 5000, 9, 3, '2024-10-15'),
(1003, 2002, 5, 7, '2024-11-05'),
(1005, 2000, 6, 8, '2024-11-15'),
(6000, 1001, 4, 9, '2024-12-01'),
(1003, 4000, 8, 10, '2024-12-10'),
(2002, 3001, 2, 3, '2025-01-05'),
(1001, 2005, 1, 1, '2025-01-10'),
(3000, 1004, 5, 6, '2025-01-15'),
(1006, 3000, 7, 8, '2025-02-01'),
(4001, 5000, 6, 4, '2025-02-15'),
(1002, 4000, 3, 3, '2025-03-05'),
(2001, 1005, 9, 8, '2025-03-10'),
(3001, 1003, 10, 5, '2025-03-15'),
(5000, 6000, 2, 7, '2025-03-20'),
(1000, 1002, 8, 6, '2025-04-01'),
(2002, 4000, 4, 5, '2025-04-15'),
(1000, 1004, 2, 9, '2025-05-01'),
(2001, 3002, 6, 8, '2025-05-05'),
(1006, 2000, 4, 3, '2025-05-10'),
(2002, 1003, 10, 6, '2025-05-15'),
(3000, 4001, 3, 2, '2025-05-20'),
(5000, 3001, 1, 4, '2025-06-01'),
(6000, 1001, 8, 9, '2025-06-15'),
(2001, 4000, 5, 5, '2025-06-20'),
(3002, 2002, 6, 10, '2025-07-01'),
(1005, 5000, 7, 1, '2025-07-10'),
(4000, 3000, 9, 3, '2025-07-15'),
(6000, 1004, 2, 6, '2025-08-01'),
(1001, 3001, 8, 4, '2025-08-10'),
(2001, 2005, 10, 7, '2025-08-15'),
(3002, 4001, 3, 5, '2025-09-01'),
(5000, 6000, 9, 9, '2025-09-15'),
(1000, 1003, 1, 1, '2025-10-01'),
(2000, 3001, 5, 8, '2025-10-15'),
(3002, 4000, 6, 10, '2025-11-01'),
(1002, 5000, 4, 3, '2025-11-10');
