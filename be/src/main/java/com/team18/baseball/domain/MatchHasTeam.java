package com.team18.baseball.domain;

public class MatchHasTeam {

    private final Long teamId;
    private final TeamType teamType;
    private final int score;

    MatchHasTeam(Long teamId, TeamType teamType, int score) {
        this.teamId = teamId;
        this.teamType = teamType;
        this.score = score;
    }

    public static final MatchHasTeam create(Long teamId, TeamType teamType, int score) {
        return new MatchHasTeam(teamId, teamType, score);
    }
}
