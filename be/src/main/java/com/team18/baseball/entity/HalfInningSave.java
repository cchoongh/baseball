//package com.team18.baseball.entity;
//
//import org.springframework.data.annotation.Id;
//
//public class HalfInningSave {
//    @Id
//    private final Long id;
//    private final Long game;
//    private final int inning;
//    private final InningType inningType;
//    private int score;
//
//    private boolean firstBase;
//    private boolean secondBase;
//    private boolean thirdBase;
//
//    private int strike;
//    private int ball;
//    private int out;
//
//    private boolean isEnd;
//    private int batterId;
//
//    HalfInningSave(Long id, Long game, int inning, InningType inningType,
//                   boolean firstBase, boolean secondBase, boolean thirdBase,
//                   int score, int strike, int ball, int out, boolean isEnd, int batterId) {
//        this.id = id;
//        this.game = game;
//        this.inning = inning;
//        this.inningType = inningType;
//        this.firstBase = firstBase;
//        this.secondBase = secondBase;
//        this.thirdBase = thirdBase;
//        this.score = score;
//        this.strike = strike;
//        this.ball = ball;
//        this.out = out;
//        this.isEnd = isEnd;
//        this.batterId = batterId;
//    }
//
//    public static final HalfInningSave create(Long id, Long game, int inning, InningType inningType,
//                                              boolean first_base, boolean second_base, boolean third_base,
//                                              int score, int strike, int ball, int out, boolean isEnd, int batter_id) {
//        return new HalfInningSave(id, game, inning, inningType, first_base, second_base, third_base, score, strike, ball
//                , out, isEnd, batter_id);
//    }
//}
