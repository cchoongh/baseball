package com.team18.baseball.dto.batterBoard;

import org.springframework.data.relational.core.mapping.Embedded;

public class BatterRecordDto {
    private int playerId;
    private String action;
    @Embedded.Nullable
    private BallCount ballCount;

    public int getPlayerId() {
        return playerId;
    }

    public String getAction() {
        return action;
    }

    public BallCount getBallCount() {
        return ballCount;
    }
}
