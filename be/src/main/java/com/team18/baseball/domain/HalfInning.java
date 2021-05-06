package com.team18.baseball.domain;

import org.springframework.data.annotation.Id;

public class HalfInning {
    @Id
    private final Long id;
    private final Long gameId;
    private final InningType.InningTypeEnum inningType;
    private final boolean firstBase;
    private final boolean secondBase;
    private final boolean thirdBase;
    private final int score;
    private final int strike;
    private final int ball;
    private final int out;
    private final boolean isEnd;
    private final int batterId;

    HalfInning(Long id, Long game_id, InningType.InningTypeEnum inningType,
               boolean firstBase, boolean secondBase, boolean thirdBase,
               int score, int strike, int ball, int out, boolean isEnd, int batterId) {
        this.id = id;
        this.gameId = game_id;
        this.inningType = inningType;
        this.firstBase = firstBase;
        this.secondBase = secondBase;
        this.thirdBase = thirdBase;
        this.score = score;
        this.strike = strike;
        this.ball = ball;
        this.out = out;
        this.isEnd = isEnd;
        this.batterId = batterId;
    }

    public static final HalfInning create(Long id, Long game_id, InningType.InningTypeEnum inningType,
                                          boolean first_base, boolean second_base, boolean third_base,
                                          int score, int strike, int ball, int out, boolean isEnd, int batter_id) {
        return new HalfInning(id, game_id, inningType, first_base, second_base, third_base, score, strike, ball
                , out, isEnd, batter_id);
    }
}
