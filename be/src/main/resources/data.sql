INSERT INTO `user`(email)
VALUES ("kkk@honux.com"),
       ("jjj@honux.com"),
       ("qqq@honux.com");

INSERT INTO `match`(id, `name`)
VALUES (1, "match1"),
       (2, "match2"),
       (3, "match3");

INSERT INTO team (id, `name`)
VALUES (1, "hanwha"),
       (2, "doosan"),
       (3, "kia"),
       (4, "samsung"),
       (5, "lotte"),
       (6, "lg");

INSERT INTO match_has_team(team_id, `match`, match_key)
VALUES (1, 1, "away"),
       (2, 1, "home"),
       (3, 2, "away"),
       (4, 2, "home"),
       (5, 3, "away"),
       (6, 3, "home");

-- INSERT INTO game ();

INSERT INTO player(`name`, team_id, is_pitcher)
VALUES ("김종수", 1, false),
       ("남지민", 1, false),
       ("임준섭", 1, false),
       ("박치국", 2, false),
       ("남호", 2, false),
       ("홍건희", 2, false);
