package com.team18.baseball.dto;

import com.team18.baseball.entity.PlateAppearance;

public class PlateAppearanceInfo {
    private final Long id;
    private final String playerName;
    private final int atBat;
    private final int hit;
    private final int out;

    private PlateAppearanceInfo(Long id, String playerName, int atBat, int hit, int out) {
        this.id = id;
        this.playerName = playerName;
        this.atBat = atBat;
        this.hit = hit;
        this.out = out;
    }

    public PlateAppearanceInfo create(Long id, String playerName, int atBat, int hit, int out) {
        return new PlateAppearanceInfo(id, playerName, atBat, hit, out);
    }

    // pa 에서 paInfo로
    // Long id, String playerName, int atBat, int hit, int out
    public static PlateAppearanceInfo from(PlateAppearance plateAppearance) {
        return new PlateAppearanceInfo(plateAppearance.getId(), plateAppearance.getPlayerName(),
                plateAppearance.getAtBat(), plateAppearance.getHit(), plateAppearance.getOut());

    }
}
