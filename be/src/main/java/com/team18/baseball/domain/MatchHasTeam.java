package com.team18.baseball.domain;

import org.springframework.data.annotation.Id;

public class MatchHasTeam {
    @Id
    private final Long id;
    private final Long teamId;
    private final TeamType teamType;
    private final int score;

    MatchHasTeam(Long id, Long teamId, TeamType teamType, int score) {
        this.id = id;
        this.teamId = teamId;
        this.teamType = teamType;
        this.score = score;
    }

    public static final MatchHasTeam create(Long id, Long teamId, TeamType teamType, int score) {
        return new MatchHasTeam(id, teamId, teamType, score);
    }
}
