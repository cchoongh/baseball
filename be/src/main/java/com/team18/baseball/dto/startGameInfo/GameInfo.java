package com.team18.baseball.dto.startGameInfo;

import com.team18.baseball.entity.game.Game;
import com.team18.baseball.entity.game.HalfInning;

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

    public int getCurrentInning() {
        return currentInning;
    }

    public String getFrame() {
        return frame;
    }
}

