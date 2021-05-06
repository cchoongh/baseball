package com.team18.baseball.domain;

import org.springframework.data.annotation.Id;

import java.util.Map;

public class Match {

    @Id
    private final Long id;
    private final Long homeId;
    private final Long awayId;
    Map<TeamType, TeamParticipateMatch> teams;

    Match(Long id, Long homeId, Long awayId, Map<TeamType,TeamParticipateMatch> teams) {
        this.id = id;
        this.homeId = homeId;
        this.awayId = awayId;
        this.teams = teams;
    }

    public static final Match create(Long id, Long homeId, Long awayId, Map<TeamType, TeamParticipateMatch> teams) {
        return new Match(id, homeId, awayId, teams);
    }
}
