package com.team18.baseball.dto.pitcherResult;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Embedded;

public class PitchResult {
    @Id
    private Long id;
    @Embedded.Nullable
    private Batter batter;
    @Embedded.Nullable
    private BallCount ballCount;
    @Embedded.Nullable
    private Base base;
    @Embedded.Nullable
    private Score score;

    public PitchResult() {
        this.id = null;
    }

    public Batter getBatter() {
        return batter;
    }

    public BallCount getBallCount() {
        return ballCount;
    }

    public Base getBase() {
        return base;
    }

    public Score getScore() {
        return score;
    }
}
