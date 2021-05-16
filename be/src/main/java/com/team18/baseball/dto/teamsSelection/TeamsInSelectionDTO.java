package com.team18.baseball.dto.teamsSelection;

public class TeamsInSelectionDTO {
    private final Long gameId;
    private final String gameStatus;
    private final TeamInSelectionDTO home;
    private final TeamInSelectionDTO away;

    private TeamsInSelectionDTO(Long gameId, String gameStatus, TeamInSelectionDTO home, TeamInSelectionDTO away) {
        this.gameId = gameId;
        this.gameStatus = gameStatus;
        this.home = home;
        this.away = away;
    }

    public static TeamsInSelectionDTO from(Long gameId, String gameStatus, TeamInSelectionDTO home, TeamInSelectionDTO away) {
       return new TeamsInSelectionDTO(gameId, gameStatus, home, away);
    }

    public Long getGameId() {
        return gameId;
    }

    public TeamInSelectionDTO getHome() {
        return home;
    }

    public TeamInSelectionDTO getAway() {
        return away;
    }

    public String getGameStatus() {
        return gameStatus;
    }
}
