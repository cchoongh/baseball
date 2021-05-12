package com.team18.baseball.dto.pitchResult;

import com.team18.baseball.entity.Player;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Embedded;

import java.util.List;

public class PitchResult {
    @Id
    private Long id;
    private Long homeId;
    private Long awayId;
    private Long battingTeamId;
    private String pitchResult;
    private List<Player> runners;
    @Embedded.Nullable
    private Batter batter;
    @Embedded.Nullable
    private BallCount ballCount;
    @Embedded.Nullable
    private Base base;
    @Embedded.Nullable
    private Score score;

    PitchResult() {
        this.id = null;
    }

    public static final PitchResult create() {
        return new PitchResult();
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
