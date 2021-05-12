package com.team18.baseball.entity.game;

import java.util.List;

public class PlateAppearance {

    private final List<PlateAppearanceInfo> away;
    private final List<PlateAppearanceInfo> home;

    private PlateAppearance(List<PlateAppearanceInfo> away, List<PlateAppearanceInfo> home) {
        this.away = away;
        this.home = home;
    }

    public static PlateAppearance create(List<PlateAppearanceInfo> away, List<PlateAppearanceInfo> home) {
        return new PlateAppearance(away, home);
    }
}
