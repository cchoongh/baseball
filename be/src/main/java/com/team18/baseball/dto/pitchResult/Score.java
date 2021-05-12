package com.team18.baseball.dto.pitchResult;

public class Score {
    private int homeScore;
    private int awayScore;

    public Score() {
    }

    Score(int homeScore, int awayScore) {
        this.homeScore = homeScore;
        this.awayScore = awayScore;
    }

    public static final Score create(int fieldingScore, int battingScore) {
        return new Score(fieldingScore, battingScore);
    }

    public int getHomeScore() {
        return homeScore;
    }

    public int getAwayScore() {
        return awayScore;
    }
}
