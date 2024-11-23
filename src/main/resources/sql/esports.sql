DROP DATABASE IF EXISTS esports;
CREATE DATABASE esports;

USE esports;

-- Create tables
CREATE TABLE companies (
    company_id INT PRIMARY KEY,
    company VARCHAR(50)
);

CREATE TABLE teams(
    team VARCHAR(50) PRIMARY KEY,
    captain VARCHAR(50),
    country VARCHAR(50),
    region  VARCHAR(50),
    status VARCHAR(50)
);

CREATE TABLE players (
    player_id VARCHAR(50) PRIMARY KEY, 
    last_name VARCHAR(50), 
    first_name VARCHAR(50),
    age INTEGER, 
    country VARCHAR(50),
    current_team VARCHAR(50), 
    status VARCHAR(10), 
    FOREIGN KEY (current_team) REFERENCES teams(team)
);


CREATE TABLE playerhistory (
    history_id INT PRIMARY KEY,
    player_id VARCHAR(50),
    old_team VARCHAR (50),
    left_old_team DATE,
    new_team VARCHAR (50),
    joined_new_team DATE,
    FOREIGN KEY(player_id) REFERENCES players(player_id),
    FOREIGN KEY(old_team) REFERENCES teams(team),
    FOREIGN KEY(new_team) REFERENCES teams(team)
);

CREATE TABLE sponsorhistory(
    history_id INT PRIMARY KEY,
    sponsor_id INT,
    team VARCHAR(50),
    contract_amount DECIMAL,
    contract_start DATE,
    contract_end DATE,
    FOREIGN KEY (sponsor_id) REFERENCES companies(company_id),
    FOREIGN KEY (team) REFERENCES teams(team)
);

CREATE TABLE teamhistory(
    history_id INT PRIMARY KEY,
    team VARCHAR(50),
    creation_date DATE,
    disband_date DATE,
    FOREIGN KEY(team) REFERENCES teams(team)
);

CREATE TABLE teamperformancehistory(
    history_id INT PRIMARY KEY,
    team VARCHAR(50),
    match_date DATE,
    result VARCHAR (10),
    winnings DECIMAL,
    FOREIGN KEY(team) REFERENCES teams(team)
);

CREATE TABLE teamsponsor (
    sponsor_id INT,
    team VARCHAR(50), 
    contract_amount DECIMAL, 
    contract_start DATE, 
    contract_end DATE,
    PRIMARY KEY (sponsor_id, team),
    FOREIGN KEY (sponsor_id) REFERENCES companies(company_id),
    FOREIGN KEY (team) REFERENCES teams(team)
);

CREATE TABLE teamstats(
    team VARCHAR(50),
    total_winnings DECIMAL,
    favored_map VARCHAR (20),
    wins INT,
    losses INT,
    FOREIGN KEY(team) REFERENCES teams(team)
);