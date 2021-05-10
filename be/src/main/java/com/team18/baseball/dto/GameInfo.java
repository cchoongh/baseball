package com.team18.baseball.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.team18.baseball.entity.Game;
import com.team18.baseball.entity.HalfInning;

public class GameInfo {
    private final Long id;
    private final int currentInning;
    private final String frame;

    private GameInfo(Long id, int currentInning, String frame) {
        this.id = id;
        this.currentInning = currentInning;
        this.frame = frame;
    }

    public static GameInfo from(Game game, HalfInning halfInning) {
        return new GameInfo(game.getId(), halfInning.getInning(), halfInning.getInningType().toString());
    }

    public Long getId() {
        return id;
    }

    @JsonProperty("current_inning")
    public int getCurrentInning() {
        return currentInning;
    }

    public String getFrame() {
        return frame;
    }
}

