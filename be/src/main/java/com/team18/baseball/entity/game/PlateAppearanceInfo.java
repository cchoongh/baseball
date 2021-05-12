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
}
