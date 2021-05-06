package com.team18.baseball.entity;

public class BattingResult {
    private final BattingResultType battingResultType;

    public BattingResult(BattingResultType battingResultType) {
        this.battingResultType = battingResultType;
    }

    enum BattingResultType {
        STRIKE("스트라이크"),
        BALL("볼"),
        HIT("안타");

        private final String korean;

        BattingResultType(String korean) {
            this.korean = korean;
        }
    }
}
