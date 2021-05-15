package com.team18.baseball.dto.startGame;

public class StartGameInfo {
    private final GameDto gameDto;
    private final TeamForStartDto home;
    private final TeamForStartDto away;

    private StartGameInfo(GameDto gameDto, TeamForStartDto home, TeamForStartDto away) {
        this.gameDto = gameDto;
        this.home = home;
        this.away = away;
    }

    public static StartGameInfo from(GameDto gameDto, TeamForStartDto homeTeam, TeamForStartDto awayTeam) {
        return new StartGameInfo(gameDto, homeTeam, awayTeam);
    }

    public GameDto getGameDto() {
        return gameDto;
    }

    public TeamForStartDto getHome() {
        return home;
    }

    public TeamForStartDto getAway() {
        return away;
    }
}
