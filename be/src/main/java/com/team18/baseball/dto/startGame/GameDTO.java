package com.team18.baseball.dto.startGame;

import com.team18.baseball.entity.game.Game;
import com.team18.baseball.entity.game.HalfInning;

public class GameDTO {
    private final Long id;
    private final int currentInning;
    private final String frame;

    private GameDTO(Long id, int currentInning, String frame) {
        this.id = id;
        this.currentInning = currentInning;
        this.frame = frame;
    }

    public static GameDTO from(Game game, HalfInning halfInning) {
        return new GameDTO(game.getId(), halfInning.getInning(), halfInning.getInningType().toString());
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

