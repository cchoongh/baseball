package com.team18.baseball.entity.battingBoard;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Embedded;

public class BattingRecord {
    @Id
    private Long id;
    private int playerId;
    private String action;
    private int nthBatter;
    private String batterName;
    private int strike;
    private int ball;

    BattingRecord() {
        id = null;
    }

    public int getPlayerId() {
        return playerId;
    }

    public String getAction() {
        return action;
    }

    public int getNth_batter() {
        return nthBatter;
    }

    public String getBatterName() {
        return batterName;
    }

    public int getStrike() {
        return strike;
    }

    public int getBall() {
        return ball;
    }
}
