package com.team18.baseball.dto.startGameInfo;

public class StartGameInfo {
    private final GameInfo gameInfo;
    private final TeamInfo home;
    private final TeamInfo away;

    private StartGameInfo(GameInfo gameInfo, TeamInfo home, TeamInfo away) {
        this.gameInfo = gameInfo;
        this.home = home;
        this.away = away;
    }

    public static StartGameInfo from(GameInfo gameInfo, TeamInfo homeTeam, TeamInfo awayTeam) {
        return new StartGameInfo(gameInfo, homeTeam, awayTeam);
    }

    public GameInfo getGameInfo() {
        return gameInfo;
    }

    public TeamInfo getHome() {
        return home;
    }

    public TeamInfo getAway() {
        return away;
    }
}
