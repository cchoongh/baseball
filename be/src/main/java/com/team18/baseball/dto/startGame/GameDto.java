package com.team18.baseball.dto.startGame;

import com.team18.baseball.entity.game.Game;
import com.team18.baseball.entity.game.HalfInning;

public class GameDto {
    private final Long id;
    private final int currentInning;
    private final String frame;

    private GameDto(Long id, int currentInning, String frame) {
        this.id = id;
        this.currentInning = currentInning;
        this.frame = frame;
    }

    public static GameDto from(Game game, HalfInning halfInning) {
        return new GameDto(game.getId(), halfInning.getInning(), halfInning.getInningType().toString());
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

