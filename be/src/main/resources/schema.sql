DROP TABLE IF EXISTS plate_appearance;
DROP TABLE IF EXISTS inning_type;
DROP TABLE IF EXISTS `half_inning`;
DROP TABLE IF EXISTS `pitch_result`;
DROP TABLE IF EXISTS player;
DROP TABLE IF EXISTS `game_has_team`;
DROP TABLE IF EXISTS team;
DROP TABLE IF EXISTS game;
DROP TABLE IF EXISTS `user`;

CREATE TABLE `user`
(
    id int auto_increment,
    github_id varchar(50),
    primary key (id)
);

CREATE TABLE game
(
    id int auto_increment,
    `home_user_id` int,
    `away_user_id` int,
    `is_end` boolean default false not null,
    primary key (id)
);

CREATE TABLE team
(
    id         int         auto_increment,
    user_id int,
    `name`     varchar(50) not null,
    primary key (id),
    foreign key (user_id) references `user` (id)
);

CREATE TABLE `game_has_team`
(
    `id`       int    auto_increment,
    `team_id`  int    not null,
    `game`     int not null,
    `game_key` varchar(50),
    `score`    int,
    primary key (id),
    foreign key (team_id) references team (id),
    foreign key (game) references game (id)
);

CREATE TABLE player
(
    id              int                   auto_increment,
    team            int                   not null,
    team_key        int,
    `name`          varchar(50)           not null,
    `uniform_number` int,
    is_pitcher boolean default false not null,
    primary key (id),
    foreign key (team) references team (id)
);

CREATE TABLE half_inning
(
    `id`          int                auto_increment,
    `game`        int                not null,
    `game_key`    int,
    `inning`      int                   not null,
    `inning_type` varchar(50)           not null,
    `score`       int                   not null,
    `is_end`    boolean default false not null,
    primary key (id),
    foreign key (game) references game (id)
);

CREATE TABLE `pitch_result`
(
    `id`          int                auto_increment,
    `batter_id`        int     not null,
    `batter_name`        varchar(50)      not null,
    `batter_uniform_number`      int                   not null,
    `pitch_result` varchar(50)           not null,
    `is_out`    boolean default false not null,
    `first_base_player`  int not null,
    `second_base_player` int not null,
    `third_base_player` int not null,
    `strike`      int                   not null,
    `ball`      int                   not null,
     `out`       int                   not null,
     `batting_score` int,
     `fielding_score` int,
    primary key (id)
);

CREATE TABLE plate_appearance
(
    id     int not null auto_increment,
    -- player_id int not null,
    player_name varchar(45) not null,
    at_bat int not null,
    hit    int not null,
    out    int not null,
    primary key (id)
    -- foreign key (player_id) references player (id)
);
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
