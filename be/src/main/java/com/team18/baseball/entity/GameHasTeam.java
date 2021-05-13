package com.team18.baseball.entity;

import org.springframework.data.annotation.Id;

public class GameHasTeam {
    @Id
    private final Long id;
    private final Long teamId;
    private int score;

    GameHasTeam(Long id, Long teamId, int score) {
        this.id = id;
        this.teamId = teamId;
        this.score = score;
    }

    public static final GameHasTeam create(Long teamId) {
        return new GameHasTeam(null, teamId, 0);
    }

    public Long getId() {
        return id;
    }

    public Long getTeamId() {
        return teamId;
    }

    public int getScore() {
        return score;
    }

    public void recordScore(int score) {
        this.score = score;
    }

    public void addScore(int score) {
        this.score += score;
    }
}
