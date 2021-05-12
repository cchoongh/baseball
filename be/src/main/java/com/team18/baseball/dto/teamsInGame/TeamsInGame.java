package com.team18.baseball.dto.teamsInGame;

public class TeamsInGame {
    private final Long gameId;
    private final String gameStatus;
    private final TeamInGameData home;
    private final TeamInGameData away;

    private TeamsInGame(Long gameId, String gameStatus, TeamInGameData home, TeamInGameData away) {
        this.gameId = gameId;
        this.gameStatus = gameStatus;
        this.home = home;
        this.away = away;
    }

    public static TeamsInGame from(Long gameId, String gameStatus, TeamInGameData home, TeamInGameData away) {
       return new TeamsInGame(gameId, gameStatus, home, away);
    }

    public Long getGameId() {
        return gameId;
    }

    public TeamInGameData getHome() {
        return home;
    }

    public TeamInGameData getAway() {
        return away;
    }

    public String getGameStatus() {
        return gameStatus;
    }
}
