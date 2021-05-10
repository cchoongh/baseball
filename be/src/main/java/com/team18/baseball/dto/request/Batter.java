package com.team18.baseball.dto.request;

public class Batter {
    private Long id;
    private String name;
    private int uniformNumber;
    private String pitchResult;
    private boolean out;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getUniformNumber() {
        return uniformNumber;
    }

    public String getPitchResult() {
        return pitchResult;
    }

    public boolean isOut() {
        return out;
    }
}
