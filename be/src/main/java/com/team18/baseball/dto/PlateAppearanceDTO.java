package com.team18.baseball.dto;

public class PlateAppearanceDTO {
    private final Long id;
    private final Long playerId;
    private final int atBat;
    private final int hit;
    private final int out;

    private PlateAppearanceDTO(Long id, Long playerId, int atBat, int hit, int out) {
        this.id = id;
        this.playerId = playerId;
        this.atBat = atBat;
        this.hit = hit;
        this.out = out;
    }

    public static PlateAppearanceDTO from(Long id, Long playerId, int atBat, int hit, int out) {
        return new PlateAppearanceDTO(id, playerId, atBat, hit, out);
    }

    public Long getId() {
        return id;
    }

    public Long getPlayerId() {
        return playerId;
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
