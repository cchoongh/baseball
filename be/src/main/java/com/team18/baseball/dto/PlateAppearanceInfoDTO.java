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

    // pa 에서 paInfo로
    // Long id, String playerName, int atBat, int hit, int out
//    public static PlateAppearanceInfo from(PlateAppearance plateAppearance) {
//        return new PlateAppearanceInfo(plateAppearance.getId(), plateAppearance.getPlayerName(),
//                plateAppearance.getAtBat(), plateAppearance.getHit(), plateAppearance.getOut());
//
//    }
}
