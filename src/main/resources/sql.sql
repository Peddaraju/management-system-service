CREATE TABLE players (
    email VARCHAR(255) NOT NULL,
    level INT CHECK (level BETWEEN 1 AND 10),
    age INT NOT NULL,
    gender ENUM('male', 'female'),
    PRIMARY KEY (email)
);

CREATE TABLE sports (
    name VARCHAR(255) NOT NULL,
    PRIMARY KEY (name)
);

CREATE TABLE player_sports (
    email VARCHAR(255) NOT NULL,
    sport_name VARCHAR(255) NOT NULL,
    PRIMARY KEY (email, sport_name),
    FOREIGN KEY (email) REFERENCES players(email) ON DELETE CASCADE,
    FOREIGN KEY (sport_name) REFERENCES sports(name) ON DELETE CASCADE
);
