CREATE TABLE venue (
	venue_id int,
	address char(20),
	rooms int,
	seats int,
	num_rinks int,
	PRIMARY KEY (venue_id)
);

CREATE TABLE game (
	game_id int,
	team_1_score int,
	team_2_score int,
	PRIMARY KEY (game_id)
);

Create TABLE regulates_game_at (
	game_id int,
	ref_id int,
    venue_id int,
    date_and_time char(100),
    PRIMARY KEY (game_id, ref_id),
    FOREIGN KEY (game_id) REFERENCES game,
    FOREIGN KEY (ref_id) REFERENCES referee,
    FOREIGN KEY (venue_id) REFERENCES venue
);

CREATE TABLE referee (
	ref_id int,
	name char(100),
	role char(100),
	PRIMARY KEY (ref_id)
);

CREATE TABLE team (
	team_id int,
	name char(100),
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
	name char(100),
	city char(100),
	PRIMARY KEY (org_id)
);

CREATE TABLE coach (
	coach_id int,
	name char(100),
	team_id int NOT NULL,
	PRIMARY KEY (coach_id),
	FOREIGN KEY (team_id) REFERENCES team
);

CREATE TABLE coach_since (
	coach_since char(100),
	team_id int NOT NULL,
	PRIMARY KEY (team_id),
	FOREIGN KEY (team_id) REFERENCES team
);

CREATE TABLE goalie (
	player_id int,
	name char(100),
	num int,
	plays_in_since char(100),
	team_id int NOT NULL,
	PRIMARY KEY (player_id),
	FOREIGN KEY (team_id) REFERENCES team
);

CREATE TABLE forward (
	player_id int,
	name char(20),
	num int,
	plays_in_since char(100),
	team_id int NOT NULL,
	PRIMARY KEY (player_id),
	FOREIGN KEY (team_id) REFERENCES team
);

CREATE TABLE defense (
	player_id int,
	name char(20),
	num int,
	plays_in_since char(100),
	team_id int NOT NULL,
	PRIMARY KEY (player_id),
	FOREIGN KEY (team_id) REFERENCES team
);