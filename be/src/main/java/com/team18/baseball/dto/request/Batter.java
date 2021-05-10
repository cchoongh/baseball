package com.team18.baseball.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Batter {
    private Long batterId;
    private String batterName;
    private int batterUniformNumber;
    private String pitchResult;
    private boolean isOut;

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
