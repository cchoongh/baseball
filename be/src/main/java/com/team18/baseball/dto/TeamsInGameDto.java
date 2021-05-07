package com.team18.baseball.dto;

public class TeamsInGameDto {
    private final Long gameId;
    private final TeamSelectionData home;
    private final TeamSelectionData away;

    private TeamsInGameDto(Long gameId, TeamSelectionData home, TeamSelectionData away) {
        this.gameId = gameId;
        this.home = home;
        this.away = away;
    }

    public static TeamsInGameDto from(Long gameId, TeamSelectionData home, TeamSelectionData away) {
       return new TeamsInGameDto(gameId, home, away);
    }

    public Long getGameId() {
        return gameId;
    }

    public TeamSelectionData getHome() {
        return home;
    }

    public TeamSelectionData getAway() {
        return away;
    }
}
