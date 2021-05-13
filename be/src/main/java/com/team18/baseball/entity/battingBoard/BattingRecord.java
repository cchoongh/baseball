package com.team18.baseball.entity.battingBoard;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Embedded;

public class BattingRecord {
    @Id
    private Long id;

    private int playerId;

    private String action;
    @Embedded.Nullable
    private BallCount ballCount;

    public BattingRecord() {
        id = null;
    }

    BattingRecord(int playerId,
                  String action, BallCount ballCount) {
        this.id = null;
        this.playerId = playerId;
        this.action = action;
        this.ballCount = ballCount;
    }

    public static BattingRecord create(int playerId,
                                       String action, BallCount ballCount) {
        return new BattingRecord(playerId, action, ballCount);
    }

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
