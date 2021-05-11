package com.team18.baseball.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class StartGameInfo {
    private final GameInfo gameInfo;
    private final TeamInfo fieldingTeam;
    private final TeamInfo battingTeam;

    private StartGameInfo(GameInfo gameInfo, TeamInfo fieldingTeam, TeamInfo batting_team) {
        this.gameInfo = gameInfo;
        this.fieldingTeam = fieldingTeam;
        this.battingTeam = batting_team;
    }

    public static StartGameInfo from(GameInfo gameInfo, TeamInfo fieldingTeam, TeamInfo batting_team) {
        return new StartGameInfo(gameInfo, fieldingTeam, batting_team);
    }

    public GameInfo getGameInfo() {
        return gameInfo;
    }

    public TeamInfo getFieldingTeam() {
        return fieldingTeam;
    }

    public TeamInfo getBattingTeam() {
        return battingTeam;
    }
}
