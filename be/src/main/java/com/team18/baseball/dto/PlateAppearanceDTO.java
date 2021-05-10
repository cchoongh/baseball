package com.team18.baseball.dto;

import java.util.List;

public class PlateAppearanceDTO {

    private final List<PlateAppearanceInfo> fieldingTeam;
    private final List<PlateAppearanceInfo> battingTeam;

    private PlateAppearanceDTO(List<PlateAppearanceInfo> fieldingTeam, List<PlateAppearanceInfo> battingTeam) {
        this.fieldingTeam = fieldingTeam;
        this.battingTeam = battingTeam;
    }

    public static PlateAppearanceDTO from(List<PlateAppearanceInfo> fieldingTeam, List<PlateAppearanceInfo> battingTeam) {
        return new PlateAppearanceDTO(fieldingTeam, battingTeam);
    }

    public List<PlateAppearanceInfo> getFieldingTeam() {
        return fieldingTeam;
    }

    public List<PlateAppearanceInfo> getBattingTeam() {
        return battingTeam;
    }
}
