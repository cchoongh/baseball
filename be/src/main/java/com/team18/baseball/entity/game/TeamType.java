package com.team18.baseball.entity.game;

public enum TeamType {
    AWAY("원정팀"),
    HOME("홈팀");

    private final String korean;

    TeamType(String korean) {
        this.korean = korean;
    }
}
