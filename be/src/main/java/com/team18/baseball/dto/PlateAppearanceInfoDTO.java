package com.team18.baseball.dto;


import java.util.List;

public class PlateAppearanceInfoDTO {
    private String teamName;
    private List<PlayersDTO> players;

    private PlateAppearanceInfoDTO(String teamName, List<PlayersDTO> players) {
        this.teamName = teamName;
        this.players = players;
    }

    public PlateAppearanceInfoDTO create(String teamName, List<PlayersDTO> players) {
        return new PlateAppearanceInfoDTO(teamName, players);
    }

    public String getTeamName() {
        return teamName;
    }

    public List<PlayersDTO> getPlayers() {
        return players;
    }
}
