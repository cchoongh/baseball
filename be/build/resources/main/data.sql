INSERT INTO `user`(github_id)
VALUES ("kkk@honux.com"),
       ("jjj@honux.com"),
       ("qqq@honux.com"),
       ("rrr@honux.com"),
       ("ppp@honux.com"),
       ("aaa@honux.com");

INSERT INTO game(id, home_user_id, away_user_id, `playing_status`)
VALUES (1, null, null, "READY"),
       (2, null, null, "READY"),
       (3, null, null, "READY");

INSERT INTO team (id, `name`)
VALUES (1, "hanwha"),
       (2, "doosan"),
       (3, "kia"),
       (4, "samsung"),
       (5, "lotte"),
       (6, "lg");

INSERT INTO game_has_team(team_id, game, game_key, score)
VALUES (1, 1, "HOME", 0),
       (2, 1, "AWAY", 0),
       (3, 2, "HOME", 0),
       (4, 2, "AWAY", 0),
       (5, 3, "HOME", 0),
       (6, 3, "AWAY", 0);

INSERT INTO player(`name`, team, `uniform_number`, is_pitcher)
VALUES ("김오잉", 1, 1, true),
       ("남치수", 1, 2, false),
       ("임준섭", 1, 3, false),
       ("김종식", 1, 4, true),
       ("남색", 1, 5, false),
       ("임춘", 1, 6, false),
       ("김총", 1, 7, true),
       ("남치", 1, 8, false),
       ("임꺽정", 1, 9, false),
       ("하이", 2, 11, true),
       ("바이", 2, 12, true),
       ("봉쥬르", 2, 13, true),
       ("호랑이", 2,14, false),
       ("추신수", 2, 15, false),
       ("박지성", 2, 16, false),
       ("불낙", 2, 17, false),
       ("이건희", 2, 18, false),
       ("호두마루", 2, 19, false),
       ("윤중현", 3, 20, false),
       ("심동섭", 3, 21, false),
       ("이민우", 3, 22, false),
       ("이준영", 3, 23, true),
       ("임기영", 3, 24, true),
       ("남재현", 3, 25, true),
       ("김윤동", 3, 26, false),
       ("박준표", 3, 27, false),
       ("브룩스", 3, 28, false),
       ("홍정우", 4, 29, false),
       ("양창섭", 4, 30, false),
       ("김대우", 4, 31, false),
       ("심창민", 4, 32, true),
       ("구준범", 4, 33, true),
       ("우규민", 4, 34, true),
       ("이승현", 4, 35, false),
       ("오승환", 4, 36, false),
       ("장필준", 4, 37, false),
       ("토마토", 5, 71, true),
       ("해물", 5, 72, true),
       ("박치기", 5, 73, true),
       ("태평양", 5, 74, false),
       ("알리오", 5, 75, false),
       ("올리오", 5, 76, false),
       ("아숨차", 5, 77, false),
       ("힘들어", 5, 78, false),
       ("힘내려", 5, 79, false),
       ("멸국국수", 6, 61, true),
       ("김칫국", 6, 62, true),
       ("된장", 6, 63, true),
       ("고추장", 6, 64, false),
       ("라면", 6, 65, false),
       ("케찹", 6, 66, false),
       ("콩국수", 6, 67, false),
       ("청국장", 6, 68, false),
       ("미역국", 6, 69, false);

