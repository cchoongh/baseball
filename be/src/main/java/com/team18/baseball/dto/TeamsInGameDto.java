package com.team18.baseball.dto;

public class TeamsInGameDto {

    private final Long id;
    private final TeamSelectionData home;
    private final TeamSelectionData away;

    private TeamsInGameDto(Long id, TeamSelectionData home, TeamSelectionData away) {
        this.id = id;
        this.home = home;
        this.away = away;
    }

    public static TeamsInGameDto from(Long id, TeamSelectionData home, TeamSelectionData away) {
       return new TeamsInGameDto(id, home, away);
    }

    public TeamSelectionData getHome() {
        return home;
    }

    public TeamSelectionData getAway() {
        return away;
    }
}
