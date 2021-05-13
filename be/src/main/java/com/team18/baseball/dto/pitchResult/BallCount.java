package com.team18.baseball.dto.pitchResult;

public class BallCount {
    private int strike;
    private int ball;
    private int out;

    BallCount() {
        this.strike = 0;
        this.ball = 0;
        this.out = 0;
    }

    public static final BallCount create() {
        return new BallCount();
    }

    public int getStrike() {
        return strike;
    }

    public int getBall() {
        return ball;
    }

    public int getOut() {
        return out;
    }
}
