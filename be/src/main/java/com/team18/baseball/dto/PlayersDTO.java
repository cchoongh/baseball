package com.team18.baseball.dto;

public class PlayersDTO {
    private final Long id;
    private final String playerName;
    private final int atBat;
    private final int hit;
    private final int out;
    private final int average;

    public PlayersDTO(Long id, String playerName, int atBat, int hit, int out, int average) {
        this.id = id;
        this.playerName = playerName;
        this.atBat = atBat;
        this.hit = hit;
        this.out = out;
        this.average = average;
    }
}
