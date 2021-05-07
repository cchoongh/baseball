INSERT INTO `user`(email)
VALUES ("kkk@honux.com"),
       ("jjj@honux.com"),
       ("qqq@honux.com");

INSERT INTO game(id)
VALUES (1),
       (2),
       (3);

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
       (6, 3, "HOME", 0);

-- INSERT INTO game ();

INSERT INTO player(`name`, team, is_pitcher)
VALUES ("김종수", 1, false),
       ("남지민", 1, false),
       ("임준섭", 1, false),
       ("박치국", 2, false),
       ("남호", 2, false),
       ("홍건희", 2, false),
       ("박치국", 3, false),
       ("남호", 3, false),
       ("홍건희", 3, false),
       ("박치국", 4, false),
       ("남호", 4, false),
       ("홍건희", 4, false),
       ("박치국", 5, false),
       ("남호", 5, false),
       ("홍건희", 5, false),
       ("박치국", 6, false),
       ("남호", 6, false),
       ("홍건희", 6, false);

