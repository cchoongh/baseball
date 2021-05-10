package com.team18.baseball.dto;

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
}

