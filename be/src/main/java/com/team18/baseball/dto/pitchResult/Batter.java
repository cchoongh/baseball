package com.team18.baseball.dto.pitchResult;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.team18.baseball.entity.Player;

public class Batter {
    private Long batterId;
    private String batterName;
    private int batterUniformNumber;
    private String pitchResult;
    private boolean isOut;

    Batter() {
    }

    Batter(Long batterId, String batterName, int batterUniformNumber) {
        this.batterId = batterId;
        this.batterName = batterName;
        this.batterUniformNumber = batterUniformNumber;
        this.pitchResult = null;
        this.isOut = false;
    }

    public static final Batter create(Player player) {
        return new Batter(player.getId(), player.getName(), player.getUniformNumber());
    }

    public Long getBatterId() {
        return batterId;
    }

    public String getBatterName() {
        return batterName;
    }

    public int getBatterUniformNumber() {
        return batterUniformNumber;
    }

    public String getPitchResult() {
        return pitchResult;
    }

    @JsonProperty("is_out")
    public boolean isOut() {
        return isOut;
    }
}
