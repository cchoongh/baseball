package com.team18.baseball.dto.teamsSelection;

public class TeamsInSelectionDto {
    private final Long gameId;
    private final String gameStatus;
    private final TeamInSelectionDto home;
    private final TeamInSelectionDto away;

    private TeamsInSelectionDto(Long gameId, String gameStatus, TeamInSelectionDto home, TeamInSelectionDto away) {
        this.gameId = gameId;
        this.gameStatus = gameStatus;
        this.home = home;
        this.away = away;
    }

    public static TeamsInSelectionDto from(Long gameId, String gameStatus, TeamInSelectionDto home, TeamInSelectionDto away) {
       return new TeamsInSelectionDto(gameId, gameStatus, home, away);
    }

    public Long getGameId() {
        return gameId;
    }

    public TeamInSelectionDto getHome() {
        return home;
    }

    public TeamInSelectionDto getAway() {
        return away;
    }

    public String getGameStatus() {
        return gameStatus;
    }
}
