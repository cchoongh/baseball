package com.team18.baseball.dto;

import java.util.List;

public class PlateAppearanceDTO {

    private final List<PlateAppearanceInfoDTO> away;
    private final List<PlateAppearanceInfoDTO> home;

    private PlateAppearanceDTO(List<PlateAppearanceInfoDTO> away, List<PlateAppearanceInfoDTO> home) {
        this.away = away;
        this.home = home;
    }

    public static PlateAppearanceDTO from(List<PlateAppearanceInfoDTO> away, List<PlateAppearanceInfoDTO> home) {
        return new PlateAppearanceDTO(away, home);
    }

    public List<PlateAppearanceInfoDTO> getAway() {
        return away;
    }

    public List<PlateAppearanceInfoDTO> getHome() {
        return home;
    }
}
