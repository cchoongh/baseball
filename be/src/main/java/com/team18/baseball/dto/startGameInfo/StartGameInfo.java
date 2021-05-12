package com.team18.baseball.dto.startGameInfo;

public class StartGameInfo {
    private final GameInfo gameInfo;
    private final TeamInfo homeTeam;
    private final TeamInfo awayTeam;

    private StartGameInfo(GameInfo gameInfo, TeamInfo homeTeam, TeamInfo batting_team) {
        this.gameInfo = gameInfo;
        this.homeTeam = homeTeam;
        this.awayTeam = batting_team;
    }

    public static StartGameInfo from(GameInfo gameInfo, TeamInfo homeTeam, TeamInfo awayTeam) {
        return new StartGameInfo(gameInfo, homeTeam, awayTeam);
    }

    public GameInfo getGameInfo() {
        return gameInfo;
    }

    public TeamInfo getHomeTeam() {
        return homeTeam;
    }

    public TeamInfo getAwayTeam() {
        return awayTeam;
    }
}
