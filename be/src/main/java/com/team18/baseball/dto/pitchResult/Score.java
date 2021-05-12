package com.team18.baseball.dto.pitchResult;

public class Score {
    private int fieldingScore;
    private int battingScore;

    public Score() {
    }

    Score(int fieldingScore, int battingScore) {
        this.fieldingScore = fieldingScore;
        this.battingScore = battingScore;
    }

    public static final Score create(int fieldingScore, int battingScore) {
        return new Score(fieldingScore, battingScore);
    }

    public int getFieldingScore() {
        return fieldingScore;
    }

    public int getBattingScore() {
        return battingScore;
    }
}
