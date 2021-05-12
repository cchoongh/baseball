package com.team18.baseball.entity.game;

import org.springframework.data.annotation.Id;

public class PlateAppearance {
    @Id
    private final Long id;
    private final Long playerId;
    private final int atBat;
    private final int hit;
    private final int out;

    PlateAppearance(Long id, Long playerId, int atBat, int hit, int out) {
        this.id = id;
        this.playerId = playerId;
        this.atBat = atBat;
        this.hit = hit;
        this.out = out;
    }

    public static PlateAppearance create (Long id,
                                          Long playerId,
                                          int atBat, int hit, int out) {
        return new PlateAppearance(id,
                playerId,
                atBat, hit, out);
    }
}
