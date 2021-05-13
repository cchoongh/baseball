package com.team18.baseball.entity.game;

import java.util.List;

public class PlateAppearance {

    private final List<PlateAppearanceInfo> home;
    private final List<PlateAppearanceInfo> away;

    private PlateAppearance(List<PlateAppearanceInfo> home, List<PlateAppearanceInfo> away) {
        this.home = home;
        this.away = away;
    }

    public static PlateAppearance create(List<PlateAppearanceInfo> home, List<PlateAppearanceInfo> away) {
        return new PlateAppearance(home, away);
    }
}
