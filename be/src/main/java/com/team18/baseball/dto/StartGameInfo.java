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

    @JsonProperty("game_info")
    public GameInfo getGameInfo() {
        return gameInfo;
    }

    @JsonProperty("fielding_team")
    public TeamInfo getFieldingTeam() {
        return fieldingTeam;
    }

    @JsonProperty("batting_team")
    public TeamInfo getBattingTeam() {
        return battingTeam;
    }
}
