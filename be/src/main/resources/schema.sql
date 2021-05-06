DROP TABLE IF EXISTS player;
DROP TABLE IF EXISTS `match_has_team`;
DROP TABLE IF EXISTS team;
DROP TABLE IF EXISTS game;
DROP TABLE IF EXISTS `match`;
DROP TABLE IF EXISTS `user`;

CREATE TABLE `user`
(
    email varchar(50) not null,
    primary key (email)
);

CREATE TABLE `match`
(
    id   int not null auto_increment,
    name varchar(50),
    primary key (id)
);

CREATE TABLE game
(
    id int not null auto_increment,
    primary key (id)
);

CREATE TABLE team
(
    id         int         not null auto_increment,
    `name`     varchar(45) not null,
    game_id    int,
    user_email varchar(45),
    primary key (id),
    foreign key (game_id) references game (id),
    foreign key (user_email) references `user` (email)
);

CREATE TABLE `match_has_team`
(
    id        int not null auto_increment,
    team_id   int,
    `match`   int references `match` (id),
    match_key varchar(64),
    primary key (id),
    foreign key (team_id) references team (id)
);

CREATE TABLE player
(
    id         int                   not null auto_increment,
    `name`     varchar(45)           not null,
    team_id    int                   not null,
    is_pitcher boolean default false not null,
    primary key (id),
    foreign key (team_id) references team (id)
);
--
-- CREATE TABLE halfInning
-- (
--     int         id          not null auto_increment,
--     game_id     int         not null,
--     inning      int         not null,
--     inning_type varchar(45) not null,
--     first_base  tinyint(1) default 0 not null,
--     second_base tinyint(1) default 0 not null,
--     third_base  tinyint(1) default 0 not null,
--     score       int         not null,
--     strike      int         not null,
--     ball        int         not null,
--     out         int         not null,
--     isEnd       tinyint(0) not null,
--     batter_id   int         not null,
--     primary key (id),
--     foreign key (game_id) references game (id)
-- );
--
-- CREATE TABLE plate_appearance
-- (
--     id     int not null auto_increment,
--     player int not null,
--     at_bat int not null,
--     hit    int not null,
--     out    int not null,
--     primary key (id)
-- );
--
-- CREATE TABLE plate_appearance_info
-- (
--     id                  int         not null auto_increment,
--     result              int         not null,
--     isOut               tinyint(0) not null,
--     plate_appearance_id int         not null,
--     batter_id           int         not null,
--     inning_index        int         not null,
--     is_top              varchar(45) not null,
--     primary key (id),
--     foreign key (plate_appearance_id) references plate_appearance (id)
-- );
