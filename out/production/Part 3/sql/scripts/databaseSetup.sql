CREATE TABLE venue (
	venue_id int,
	address varchar2(100) NOT NULL,
	rooms int,
	seats int,
	num_rinks int,
	PRIMARY KEY (venue_id)
);

/* INSERT INTO branch VALUES (1, "ABC", "123 varchar2ming Ave", "Vancouver", "6041234567");*/
INSERT INTO venue VALUES (1, "800 Griffiths Way, Vancouver, BC V6B 6G1", 10,18910, 1);

CREATE TABLE game (
	game_id int,
	team_1_score int,
	team_2_score int,
	PRIMARY KEY (game_id)
);

CREATE TABLE regulates_game_at (
	game_id int,
	ref_id int,
    venue_id int,
    date_and_time varchar2(100),
    PRIMARY KEY (game_id, ref_id),
    FOREIGN KEY (game_id) REFERENCES game,
    FOREIGN KEY (ref_id) REFERENCES referee,
    FOREIGN KEY (venue_id) REFERENCES venue
);

CREATE TABLE referee (
	ref_id int,
	name varchar2(100),
	role varchar2(100),
	PRIMARY KEY (ref_id)
);

CREATE TABLE team (
	team_id int,
	name varchar2(100),
	org_id int,
	PRIMARY KEY (team_id),
	FOREIGN KEY (org_id) REFERENCES organization
);

CREATE TABLE competes_in (
	team_id int NOT NULL,
	game_id int,
	PRIMARY KEY (team_id),
	PRIMARY KEY (game_id),
	FOREIGN KEY (team_id) REFERENCES team,
	FOREIGN KEY (game_id) REFERENCES game
);

CREATE TABLE organization (
	org_id int,
	name varchar2(100),
	city varchar2(100),
	PRIMARY KEY (org_id)
);

CREATE TABLE coach (
	coach_id int,
	name varchar2(100),
	team_id int NOT NULL,
	PRIMARY KEY (coach_id),
	FOREIGN KEY (team_id) REFERENCES team
);

CREATE TABLE coach_since (
	coach_since varchar2(100),
	team_id int NOT NULL,
	PRIMARY KEY (team_id),
	FOREIGN KEY (team_id) REFERENCES team
);

CREATE TABLE goalie (
	player_id int,
	name varchar2(100),
	num int,
	plays_in_since varchar2(100),
	team_id int NOT NULL,
	PRIMARY KEY (player_id),
	FOREIGN KEY (team_id) REFERENCES team
);

CREATE TABLE forward (
	player_id int,
	name varchar2(20),
	num int,
	plays_in_since varchar2(100),
	team_id int NOT NULL,
	PRIMARY KEY (player_id),
	FOREIGN KEY (team_id) REFERENCES team
);

CREATE TABLE defense (
	player_id int,
	name varchar2(20),
	num int,
	plays_in_since varchar2(100),
	team_id int NOT NULL,
	PRIMARY KEY (player_id),
	FOREIGN KEY (team_id) REFERENCES team
);

