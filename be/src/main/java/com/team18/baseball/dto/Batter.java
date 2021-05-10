package com.team18.baseball.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.team18.baseball.entity.Player;

public class Batter {
    private Long id;
    private String name;
    @JsonProperty("uniform_number")
    private int uniformNumber;
    private boolean out;

    public Batter() {
    }

    public Batter(Long id, String name, int uniformNumber, boolean out) {
        this.id = id;
        this.name = name;
        this.uniformNumber = uniformNumber;
        this.out = out;
    }

    public final Batter create(Long id, String name, int uniformNumber, boolean out) {
        return new Batter(id, name, uniformNumber, out);
    }

    public boolean isOut() {
        return out;
    }
}
