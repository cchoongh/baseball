INSERT INTO `user`(github_id)
VALUES ("kkk@honux.com"),
       ("jjj@honux.com"),
       ("qqq@honux.com");

INSERT INTO game(id, home_user_id, away_user_id)
VALUES (1, null, null),
       (2, null, null),
       (3, null, null);

INSERT INTO team (id, `name`)
VALUES (1, "hanwha"),
       (2, "doosan"),
       (3, "kia"),
       (4, "samsung"),
       (5, "lotte"),
       (6, "lg");

INSERT INTO game_has_team(team_id, game, game_key, score)
VALUES (1, 1, "AWAY", 0),
       (2, 1, "HOME", 0),
       (3, 2, "AWAY", 0),
       (4, 2, "HOME", 0),
       (5, 3, "AWAY", 0),
       (1, 3, "HOME", 0);

-- INSERT INTO game ();

INSERT INTO player(`name`, team, `uniform_number`, is_pitcher)
VALUES ("김종수", 1, 1, true),
       ("남지민", 1, 2, false),
       ("임준섭", 1, 3, false),
       ("박치국", 2, 4, true),
       ("남호", 2, 5, false),
       ("홍건희", 2, 6, false),
       ("박치국", 3, 7, false),
       ("남호", 3, 8, true),
       ("홍건희", 3, 9, false),
       ("박치국", 4, 10, false),
       ("남호", 4, 11, true),
       ("홍건희", 4, 12, false),
       ("박치국", 5, 13, true),
       ("남호", 5, 14, false),
       ("홍건희", 5, 15, false),
       ("박치국", 6, 16, true),
       ("남호", 6, 17, false),
       ("홍건희", 6, 18, false);

