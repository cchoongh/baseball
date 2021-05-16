package com.team18.baseball.dto.startGame;

public class StartGameInfo {
    private final GameDTO gameDto;
    private final TeamForStartDTO home;
    private final TeamForStartDTO away;

    private StartGameInfo(GameDTO gameDto, TeamForStartDTO home, TeamForStartDTO away) {
        this.gameDto = gameDto;
        this.home = home;
        this.away = away;
    }

    public static StartGameInfo from(GameDTO gameDto, TeamForStartDTO homeTeam, TeamForStartDTO awayTeam) {
        return new StartGameInfo(gameDto, homeTeam, awayTeam);
    }

    public GameDTO getGameDto() {
        return gameDto;
    }

    public TeamForStartDTO getHome() {
        return home;
    }

    public TeamForStartDTO getAway() {
        return away;
    }
}
