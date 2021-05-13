package com.team18.baseball.dto.pitchResultDto;

public class Runner {
    private Long playerId;
    private String mode;

    Runner() {
    }

    Runner(Long playerId, String mode) {
        this.playerId = playerId;
        this.mode = mode;
    }

    public static final Runner create(Long playerId, String mode) {
        return new Runner(playerId, mode);
    }

    public Long getPlayerId() {
        return playerId;
    }

    public String getMode() {
        return mode;
    }
}
