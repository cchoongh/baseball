package com.team18.baseball.dto;


import java.util.ArrayList;
import java.util.List;

public class PlateAppearanceInfoDTO {
    private String teamName;
    private List<PlayersDTO> players;

    private PlateAppearanceInfoDTO(List<PlayersDTO> players) {
        this.players = players;
        this.teamName = null;
    }

    private PlateAppearanceInfoDTO(String teamName, List<PlayersDTO> players) {
        this.teamName = teamName;
        this.players = players;
    }

    public static PlateAppearanceInfoDTO create(String teamName, List<PlayersDTO> players) {
        return new PlateAppearanceInfoDTO(teamName, players);
    }

    public static PlateAppearanceInfoDTO createNull() {
        List<PlayersDTO> players = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
             players.add(PlayersDTO.createNull());
        }
        return new PlateAppearanceInfoDTO(players);
    }


    public String getTeamName() {
        return teamName;
    }

    public List<PlayersDTO> getPlayers() {
        return players;
    }
}
