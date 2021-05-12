package com.team18.baseball.dto;

import java.util.List;

public class PlateAppearanceDTO {

    private final List<PlateAppearanceInfo> away;
    private final List<PlateAppearanceInfo> home;

    private PlateAppearanceDTO(List<PlateAppearanceInfo> away, List<PlateAppearanceInfo> home) {
        this.away = away;
        this.home = home;
    }

    public static PlateAppearanceDTO from(List<PlateAppearanceInfo> away, List<PlateAppearanceInfo> home) {
        return new PlateAppearanceDTO(away, home);
    }

    public List<PlateAppearanceInfo> getAway() {
        return away;
    }

    public List<PlateAppearanceInfo> getHome() {
        return home;
    }
}
