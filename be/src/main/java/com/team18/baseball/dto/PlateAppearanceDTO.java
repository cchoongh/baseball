package com.team18.baseball.dto;

public class PlateAppearanceDTO {
    private final Long id;
    private final String playerName;
    private final int atBat;
    private final int hit;
    private final int out;

    private PlateAppearanceDTO(Long id, String playerName, int atBat, int hit, int out) {
        this.id = id;
        this.playerName = playerName;
        this.atBat = atBat;
        this.hit = hit;
        this.out = out;
    }

    public static PlateAppearanceDTO from(Long id, String playerName, int atBat, int hit, int out) {
        return new PlateAppearanceDTO(id, playerName, atBat, hit, out);
    }

    public Long getId() {
        return id;
    }

    public String getPlayerName() {
        return playerName;
    }

    public int getAtBat() {
        return atBat;
    }

    public int getHit() {
        return hit;
    }

    public int getOut() {
        return out;
    }
}
