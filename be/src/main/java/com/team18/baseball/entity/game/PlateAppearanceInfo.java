package com.team18.baseball.entity.game;

import java.util.List;

public class PlateAppearanceInfo {
    private String teamName;
    private List<Players> players;

    private PlateAppearanceInfo(String teamName, List<Players> players) {
        this.teamName = teamName;
        this.players = players;
    }

    public PlateAppearanceInfo create(String teamName, List<Players> players) {
        return new PlateAppearanceInfo(teamName, players);
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public List<Players> getPlayers() {
        return players;
    }

    public void setPlayers(List<Players> players) {
        this.players = players;
    }
}
