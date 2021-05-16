package com.team18.baseball.dto.pitchResult;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.team18.baseball.entity.Player;

public class Batter {
    private Long playerId;
    private String playerName;
    private int playerUniformNumber;
    private boolean isOut;

    private Batter() {
    }

    Batter(Long playerId, String playerName, int playerUniformNumber) {
        this.playerId = playerId;
        this.playerName = playerName;
        this.playerUniformNumber = playerUniformNumber;
        this.isOut = false;
    }

    public static Batter create(Player player) {
        return new Batter(player.getId(), player.getName(), player.getUniformNumber());
    }

    public Long getPlayerId() {
        return playerId;
    }

    public String getPlayerName() {
        return playerName;
    }

    public int getPlayerUniformNumber() {
        return playerUniformNumber;
    }

    @JsonProperty("is_out")
    public boolean isOut() {
        return isOut;
    }
}
