CREATE TABLE venue (
	venue_id int,
	address varchar2(100) NOT NULL,
	rooms int,
	seats int,
	num_rinks int,
	PRIMARY KEY (venue_id)
);

CREATE TABLE game (
	game_id int,
	team1_score int,
	team2_score int,
	PRIMARY KEY (game_id)
);

CREATE TABLE organization (
      org_id int,
      name varchar2(100),
      city varchar2(100),
      PRIMARY KEY (org_id)
);

CREATE TABLE referee (
     ref_id int,
     name varchar2(100),
     role varchar2(100),
     PRIMARY KEY (ref_id)
);

CREATE TABLE regulates_game_at (
	game_id int,
	ref_id int,
    venue_id int,
    date_and_time varchar2(100),
    PRIMARY KEY (game_id),
    FOREIGN KEY (game_id) REFERENCES game,
    FOREIGN KEY (ref_id) REFERENCES referee,
    FOREIGN KEY (venue_id) REFERENCES venue
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
	PRIMARY KEY (team_id, game_id),
	FOREIGN KEY (team_id) REFERENCES team,
	FOREIGN KEY (game_id) REFERENCES game
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

INSERT INTO venue VALUES(1, '800 Griffiths Way, Vancouver, BC V6B 6G1', 10, 18910, 1);

INSERT INTO game VALUES(1, 5, 3);

INSERT INTO organization VALUES(1, 'Vancouver ORG', 'Vancouver');

INSERT INTO referee VALUES(1, 'Zebra', 'Referee');
INSERT INTO referee VALUES(2, 'Frank', 'Linesman');
INSERT INTO referee VALUES(3, 'Carl', 'Linesman');

INSERT INTO regulates_game_at VALUES(1, 1, 1, 'March 25, 2022');
INSERT INTO regulates_game_at VALUES(1, 2, 1, 'March 25, 2022');
INSERT INTO regulates_game_at VALUES(1, 3, 1, 'March 25, 2022');

INSERT INTO team VALUES(1, 'Bulldogs', 1);
INSERT INTO team VALUES(2, 'Canucks', 1);

INSERT INTO competes_in VALUES(1, 1);
INSERT INTO competes_in VALUES(2, 1);

INSERT INTO coach VALUES(1, 'Richard', 1);
INSERT INTO coach VALUES(2, 'John', 2);

INSERT INTO coach_since VALUES('March 20, 2022', 1);
INSERT INTO coach_since VALUES('March 21, 2022', 2);

INSERT INTO goalie VALUES(1, 'Mark', 1, 'March 20, 2022', 1);
INSERT INTO goalie VALUES(7, 'Mark', 1, 'March 20, 2022', 2);


INSERT INTO forward VALUES(2, 'Mark', 2, 'March 20, 2022', 1);
INSERT INTO forward VALUES(3, 'Bob', 3, 'March 20, 2022', 1);
INSERT INTO forward VALUES(4, 'David', 4, 'March 20, 2022', 1);

INSERT INTO forward VALUES(8, 'Alex', 2, 'March 20, 2022', 2);
INSERT INTO forward VALUES(9, 'Robert', 3, 'March 20, 2022', 2);
INSERT INTO forward VALUES(10, 'Ethan', 4, 'March 20, 2022', 2);

INSERT INTO defense VALUES(5, 'Steven', 5, 'March 20, 2022', 1);
INSERT INTO defense VALUES(6, 'Sam', 6, 'March 20, 2022', 1);

INSERT INTO defense VALUES(11, 'Adam', 5, 'March 20, 2022', 2);
INSERT INTO defense VALUES(12, 'Nicholas', 6, 'March 20, 2022', 2);