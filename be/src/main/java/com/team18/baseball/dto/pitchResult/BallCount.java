package com.team18.baseball.dto.pitchResult;

public class BallCount {
    private int strike;
    private int ball;
    private int out;

    private BallCount() {
    }

    BallCount(int strike, int ball, int out) {
        this.strike = strike;
        this.ball = ball;
        this.out = out;
    }

    public static BallCount create() {
        return new BallCount(0, 0, 0);
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
