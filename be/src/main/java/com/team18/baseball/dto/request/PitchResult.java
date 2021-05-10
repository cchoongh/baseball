package com.team18.baseball.dto.request;

public class PitchResult {
    private Batter batter;
    private BallCount ballCount;
    private Base base;
    private Score score;

    public Batter getBatter() {
        return batter;
    }

    public BallCount getBallCount() {
        return ballCount;
    }

    public Base getBase() {
        return base;
    }

    public Score getScore() {
        return score;
    }
}
