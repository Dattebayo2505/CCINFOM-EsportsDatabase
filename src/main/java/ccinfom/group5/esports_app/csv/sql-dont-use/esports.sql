-- SERVES AS AN ALTERNATIVE OR BASIS

-- Drop the table if it exists
DROP TABLE IF EXISTS players;

-- Create the database
CREATE DATABASE IF NOT EXISTS esports;

-- Use the database
USE esports;

-- Create the table
CREATE TABLE companies (
    id INT PRIMARY KEY,
    company_name VARCHAR(50)
);

CREATE TABLE teams(
    team INT PRIMARY KEY,
    captain VARCHAR(50),
    country VARCHAR(50),
    region  VARCHAR(50),
    status VARCHAR(50)
);

CREATE TABLE players (
    player_id INT PRIMARY KEY, 
    last_name VARCHAR(50), first_name VARCHAR(50),
    age INTEGER, country VARCHAR(50), 
    FOREIGN KEY (current_team) REFERENCES teams(team),
    status VARCHAR(10)
);

CREATE TABLE playerhistory (
    history_id INT PRIMARY KEY,
    FOREIGN KEY(player_id) REFERENCES players(player_id),
    FOREIGN KEY(old_team) REFERENCES teams(team),
    left_old_team DATE,
    FOREIGN KEY(new_team) REFERENCES teams(team),
    joined_new_team DATE
);

CREATE TABLE sponsorhistory(
    history_id INT PRIMARY KEY,
    sponsor_id INT PRIMARY KEY,
    FOREIGN KEY (team_name) REFERENCES teams(team),
    contract_amount FLOAT(6, 2),
    contract_start DATE,
    contract_end DATE
);

CREATE TABLE teamhistory(
    history_id INT PRIMARY,
    FOREIGN KEY(team_id) REFERENCES teams(team),
    creation_date DATE,
    disband_date DATE
);

CREATE TABLE teamperformancehistory(
    history_id INT PRIMARY KEY,
    FOREIGN KEY(team_name) REFERENCES teams(team),
    match_date DATE,
    result VARCHAR (10),
    winnings FLOAT(6, 2)
);

CREATE TABLE teamsponsor(
    sponsor_id INT PRIMARY KEY, 
    FOREIGN KEY(team) REFERENCES teams(team),
    contract_amount FLOAT(6, 2), 
    contract_start DATE, 
    contract_end DATE
);

CREATE TABLE teamstats(
    FOREIGN KEY(team) REFERENCES teams(team),
    total_winnings FLOAT(7, 2),
    favored_map VARCHAR (20),
    wins INT,
    losses INT
);