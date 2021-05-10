package com.team18.baseball.entity;

import org.springframework.data.annotation.Id;

public class PlateAppearance {
    @Id
    private final Long id;
    private final Long playerId;
    private final String playerName;
    private final int atBat;
    private final int hit;
    private final int out;

    PlateAppearance(Long id, Long playerId, String playerName, int atBat, int hit, int out) {
        this.id = id;
        this.playerId = playerId;
        this.playerName = playerName;
        this.atBat = atBat;
        this.hit = hit;
        this.out = out;
    }

    public static PlateAppearance create(Long id,
                                         Long playerId, String playerName,
                                         int atBat, int hit, int out) {
        return new PlateAppearance(id,
                playerId, playerName,
                atBat, hit, out);
    }

    public Long getId() {
        return id;
    }

    public Long getPlayerId() {
        return playerId;
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
