-- SERVES AS AN ALTERNATIVE OR BASIS

-- Create the database
CREATE DATABASE IF NOT EXISTS esports;

-- Use the database
USE esports;

-- Drop the table if it exists
DROP TABLE IF EXISTS players;

-- Create the table
CREATE TABLE players (
    id INT PRIMARY KEY,
    name VARCHAR(50),
    age INT
);

-- Insert rows into the table
INSERT INTO players (id, name, age) VALUES (1, 'Alice', 30);
INSERT INTO players (id, name, age) VALUES (2, 'Guo', 25);
INSERT INTO players (id, name, age) VALUES (3, 'Job', 35);
INSERT INTO players (id, name, age) VALUES (4, 'SK', 28);
INSERT INTO players (id, name, age) VALUES (5, 'Meg', 22);
INSERT INTO players (id, name, age) VALUES (6, 'Keisha', 56);