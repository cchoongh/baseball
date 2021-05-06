package com.team18.baseball.domain;

import org.springframework.data.annotation.Id;

public class PlateApperance {
    @Id
    private final Long id;
    private final Long playerId;
    private final int atBat;
    private final int hit;
    private final int out;

    PlateApperance(Long id, Long playerId, int atBat, int hit, int out) {
        this.id = id;
        this.playerId = playerId;
        this.atBat = atBat;
        this.hit = hit;
        this.out = out;
    }

    public static PlateApperance create (Long id,
                                         Long playerId,
                                         int atBat, int hit, int out) {
        return new PlateApperance(id,
                playerId,
                atBat, hit, out);
    }
}
