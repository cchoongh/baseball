package com.team18.baseball.dto;

import com.team18.baseball.entity.Player;

public class PlayersDTO {
    private Long playerId;
    private String playerName;
    private int atBat;
    private int hit;
    private int out;
    private int average;

    private PlayersDTO(Player player) {
        this.playerId = player.getId();
        this.playerName = player.getName();
        this.atBat = 0;
        this.hit = 0;
        this.out = 0;
        this.average = 0;
    }

    private PlayersDTO(Long playerId, String playerName, int atBat, int hit, int out, int average) {
        this.playerId = playerId;
        this.playerName = playerName;
        this.atBat = atBat;
        this.hit = hit;
        this.out = out;
        this.average = average;
    }

    public static PlayersDTO create(Long playerId, String playerName, int atBat, int hit, int out, int average) {
        return new PlayersDTO(playerId, playerName, atBat, hit, out, average);
    }

    public Long getPlayerId() {
        return playerId;
    }

    public String getPlayerName() {
        return playerName;
    }

    public int getAtBat() {
        return atBat;
    }

    public int getHit() {
        return hit;
    }

    public int getOut() {
        return out;
    }

    public int getAverage() {
        return average;
    }
}