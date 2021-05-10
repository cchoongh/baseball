package com.team18.baseball.dto;

public class TeamsInGame {
    private final Long gameId;
    private final TeamSelectionData home;
    private final TeamSelectionData away;

    private TeamsInGame(Long gameId, TeamSelectionData home, TeamSelectionData away) {
        this.gameId = gameId;
        this.home = home;
        this.away = away;
    }

    public static TeamsInGame from(Long gameId, TeamSelectionData home, TeamSelectionData away) {
       return new TeamsInGame(gameId, home, away);
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
