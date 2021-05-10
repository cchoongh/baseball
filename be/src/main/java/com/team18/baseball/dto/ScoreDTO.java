package com.team18.baseball.dto;

import java.util.List;

public class ScoreDTO {

    private boolean isUserHomeTeam;
    private boolean isUserBatting;
    //halfinning에 담긴 score를 넣어 보내 준다.
    private List<Integer> fieldingScore;
    private List<Integer> battingScore;
    private int fieldingTotal;
    private int battingTotal;

    private ScoreDTO(boolean isUserHomeTeam, boolean isUserBatting, List<Integer> fieldingScore,
                    List<Integer> battingScore, int fieldingTotal, int battingTotal) {
        this.isUserHomeTeam = isUserHomeTeam;
        this.isUserBatting = isUserBatting;
        this.fieldingScore = fieldingScore;
        this.battingScore = battingScore;
        this.fieldingTotal = fieldingTotal;
        this.battingTotal = battingTotal;
    }

    public static ScoreDTO create(boolean isUserHomeTeam, boolean isUserBatting,  List<Integer> fieldingScore,
                                  List<Integer> battingScore, int fieldingTotal, int battingTotal) {
        return new ScoreDTO(isUserHomeTeam, isUserBatting, fieldingScore, battingScore, fieldingTotal, battingTotal);
    }

}
