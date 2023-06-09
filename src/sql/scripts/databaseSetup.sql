CREATE TABLE venue (
	venue_id int NOT NULL,
	address varchar2(100) NOT NULL,
	rooms int,
	seats int,
	num_rinks int,
	PRIMARY KEY (venue_id)
);

CREATE TABLE game (
	game_id int NOT NULL,
	team1_score int,
	team2_score int,
	PRIMARY KEY (game_id)
);

CREATE TABLE organization (
    org_id int NOT NULL,
    name varchar2(100),
    city varchar2(100),
    PRIMARY KEY (org_id)
);

CREATE TABLE referee (
     ref_id int NOT NULL,
     name varchar2(100),
     PRIMARY KEY (ref_id)
);

CREATE TABLE regulates_game_at (
	game_id int NOT NULL,
	ref_id int NOT NULL,
    venue_id int NOT NULL,
    date_and_time varchar2(100),
    PRIMARY KEY (game_id),
    FOREIGN KEY (game_id) REFERENCES game,
    FOREIGN KEY (ref_id) REFERENCES referee,
    FOREIGN KEY (venue_id) REFERENCES venue
);

CREATE TABLE team (
    team_id int NOT NULL,
    name varchar2(100),
    org_id int NOT NULL,
    PRIMARY KEY (team_id),
    FOREIGN KEY (org_id) REFERENCES organization
    ON DELETE CASCADE
);

CREATE TABLE competes_in (
	team_id int NOT NULL,
	game_id int NOT NULL,
	PRIMARY KEY (team_id, game_id),
	FOREIGN KEY (team_id) REFERENCES team,
	FOREIGN KEY (game_id) REFERENCES game
);

CREATE TABLE coach (
	coach_id int NOT NULL,
	name varchar2(100),
	team_id int NOT NULL,
	PRIMARY KEY (coach_id),
	FOREIGN KEY (team_id) REFERENCES team
    ON DELETE CASCADE
);

CREATE TABLE coach_since (
	coach_since varchar2(100),
	team_id int NOT NULL,
	PRIMARY KEY (team_id),
	FOREIGN KEY (team_id) REFERENCES team
    ON DELETE CASCADE
);

CREATE TABLE goalie (
	player_id int NOT NULL,
	name varchar2(100),
	num int,
	plays_in_since varchar2(100),
	team_id int NOT NULL,
	PRIMARY KEY (player_id),
	FOREIGN KEY (team_id) REFERENCES team
    ON DELETE CASCADE
);

CREATE TABLE forward (
	player_id int NOT NULL,
	name varchar2(100),
	num int,
	plays_in_since varchar2(100),
	team_id int NOT NULL,
	PRIMARY KEY (player_id),
	FOREIGN KEY (team_id) REFERENCES team
    ON DELETE CASCADE
);

CREATE TABLE defense (
	player_id int NOT NULL,
	name varchar2(100),
	num int,
	plays_in_since varchar2(100),
	team_id int NOT NULL,
	PRIMARY KEY (player_id),
	FOREIGN KEY (team_id) REFERENCES team
    ON DELETE CASCADE
);

INSERT INTO venue VALUES(1, '800 Griffiths Way, Vancouver, BC V6B 6G1', 10, 18910, 1);
INSERT INTO venue VALUES(2, '6159 Curtis St, Burnaby, BC V5B 4X7', 11, 6000, 1);
INSERT INTO venue VALUES(3, '3676 Kensington Ave, Burnaby, BC V5B 4Z6', 10, 8983, 1);

INSERT INTO game VALUES(1, 5, 3);
INSERT INTO game VALUES(2, 1, 2);
INSERT INTO game VALUES(3, 0, 10);
INSERT INTO game VALUES(4, 2, 13);

INSERT INTO organization VALUES(1, 'Vancouver ORG', 'Vancouver');

INSERT INTO referee VALUES(1, 'Zebra');
INSERT INTO referee VALUES(2, 'Tom');
INSERT INTO referee VALUES(3, 'Luke');

INSERT INTO regulates_game_at VALUES(1, 3, 1, 'March 25, 2022');
INSERT INTO regulates_game_at VALUES(2, 2, 2, 'March 30, 2022');
INSERT INTO regulates_game_at VALUES(3, 1, 3, 'March 14, 2022');
INSERT INTO regulates_game_at VALUES(4, 2, 3, 'March 14, 2022');

INSERT INTO team VALUES(1, 'Bulldogs', 1);
INSERT INTO team VALUES(2, 'Canucks', 1);
INSERT INTO team VALUES(3, 'ThunderBirds', 1);

/*Game 1*/
INSERT INTO competes_in VALUES(1, 1);
INSERT INTO competes_in VALUES(2, 1);
/*Game 2*/
INSERT INTO competes_in VALUES(1, 2);
INSERT INTO competes_in VALUES(3, 2);
/*Game 3*/
INSERT INTO competes_in VALUES(1, 3);
INSERT INTO competes_in VALUES(3, 3);
/*Game 3*/
INSERT INTO competes_in VALUES(1, 4);
INSERT INTO competes_in VALUES(2, 4);

INSERT INTO coach VALUES(1, 'Richard', 1);
INSERT INTO coach VALUES(2, 'John', 2);
INSERT INTO coach VALUES(3, 'David', 3);

INSERT INTO coach_since VALUES('March 20, 2022', 1);
INSERT INTO coach_since VALUES('March 21, 2022', 2);
INSERT INTO coach_since VALUES('March 15, 2022', 3);

/* Team 1*/
INSERT INTO goalie VALUES(1, 'Mark', 1, 'March 20, 2022', 1);
INSERT INTO forward VALUES(2, 'Mark', 2, 'March 20, 2022', 1);
INSERT INTO forward VALUES(3, 'Bob', 3, 'March 20, 2022', 1);
INSERT INTO forward VALUES(4, 'David', 4, 'March 20, 2022', 1);
INSERT INTO defense VALUES(5, 'Steven', 5, 'March 20, 2022', 1);
INSERT INTO defense VALUES(6, 'Sam', 6, 'March 20, 2022', 1);


/* Team 2*/
INSERT INTO goalie VALUES(7, 'Marc', 1, 'March 20, 2022', 2);
INSERT INTO forward VALUES(8, 'Alex', 2, 'March 20, 2022', 2);
INSERT INTO forward VALUES(9, 'Robert', 3, 'March 20, 2022', 2);
INSERT INTO forward VALUES(10, 'Ethan', 4, 'March 20, 2022', 2);
INSERT INTO defense VALUES(11, 'Adam', 5, 'March 20, 2022', 2);
INSERT INTO defense VALUES(12, 'Nicholas', 6, 'March 20, 2022', 2);


/* Team 3*/
INSERT INTO goalie VALUES(13, 'Tavin', 1, 'March 20, 2022', 3);
INSERT INTO forward VALUES(14, 'Dylan', 2, 'March 20, 2022', 3);
INSERT INTO forward VALUES(15, 'Ryan', 3, 'March 20, 2022', 3);
INSERT INTO forward VALUES(16, 'Ernest', 4, 'March 20, 2022', 3);
INSERT INTO defense VALUES(17, 'Alexander', 5, 'March 20, 2022', 3);
INSERT INTO defense VALUES(18, 'Nathan', 6, 'March 20, 2022', 3);