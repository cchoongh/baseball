package com.team18.baseball.dto;

public class TeamsInGame {
    private final Long gameId;
    private final String gameStatus;
    private final TeamSelectionData home;
    private final TeamSelectionData away;

    private TeamsInGame(Long gameId, String gameStatus, TeamSelectionData home, TeamSelectionData away) {
        this.gameId = gameId;
        this.gameStatus = gameStatus;
        this.home = home;
        this.away = away;
    }

    public static TeamsInGame from(Long gameId, String gameStatus, TeamSelectionData home, TeamSelectionData away) {
       return new TeamsInGame(gameId, gameStatus, home, away);
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

    public String getGameStatus() {
        return gameStatus;
    }
}
