package com.team18.baseball.dto;

import com.team18.baseball.dto.pitchResult.PitchResult;

public class PlayersDTO {
    private final Long playerId;
    private final String playerName;
    private final int atBat;
    private final int hit;
    private final int out;
    private final int average;

    private PlayersDTO(Long playerId, String playerName, int atBat, int hit, int out, int average) {
        this.playerId = playerId;
        this.playerName = playerName;
        this.atBat = atBat;
        this.hit = hit;
        this.out = out;
        this.average = average;
    }

//    public static PlayersDTO makePlayersDTO(PitchResult pitchResult) {
//        Long playerId = pitchResult
//        PlayersDTO playersDTO = new PlayersDTO()
//    }

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
