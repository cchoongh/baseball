package com.team18.baseball.dto.teamsInGame;

public class TeamsInGameDto {
    private final Long gameId;
    private final String gameStatus;
    private final TeamDto home;
    private final TeamDto away;

    private TeamsInGameDto(Long gameId, String gameStatus, TeamDto home, TeamDto away) {
        this.gameId = gameId;
        this.gameStatus = gameStatus;
        this.home = home;
        this.away = away;
    }

    public static TeamsInGameDto from(Long gameId, String gameStatus, TeamDto home, TeamDto away) {
       return new TeamsInGameDto(gameId, gameStatus, home, away);
    }

    public Long getGameId() {
        return gameId;
    }

    public TeamDto getHome() {
        return home;
    }

    public TeamDto getAway() {
        return away;
    }

    public String getGameStatus() {
        return gameStatus;
    }
}
