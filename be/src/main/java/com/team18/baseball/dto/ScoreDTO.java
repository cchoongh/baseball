package com.team18.baseball.dto;

import com.team18.baseball.entity.HalfInning;
import com.team18.baseball.entity.InningType;

import java.util.List;

public class ScoreDTO {

    private boolean isUserHomeTeam;
    private boolean isUserBatting;
    //halfinning에 담긴 score를 넣어 보내 준다.
    private int homeScore;
    private int awayScore;
    private List<Integer> homeScoreList;
    private List<Integer> awayScoreList;
    private int homeTotal;
    private int awayTotal;

    private ScoreDTO(boolean isUserHomeTeam, boolean isUserBatting, List<Integer> homeScoreList,
                     List<Integer> awayScoreList, int homeTotal, int awayTotal) {
        this.isUserHomeTeam = isUserHomeTeam;
        this.isUserBatting = isUserBatting;
        this.homeScoreList = homeScoreList;
        this.awayScoreList = awayScoreList;
        this.homeTotal = homeTotal;
        this.awayTotal = awayTotal;
    }

    public static ScoreDTO create(boolean isUserHomeTeam, boolean isUserBatting, List<Integer> homeScoreList,
                                  List<Integer> awayScoreList, int homeTotal, int awayTotal) {
        return new ScoreDTO(isUserHomeTeam, isUserBatting, homeScoreList, awayScoreList, homeTotal, awayTotal);
    }

    public void setScore(HalfInning halfInning) {
        // 홈 팀은 top(초) 에 항상 수비(fielding)다. away 팀은 top에- (반대)
        // HalfInning의 score를 가져올 때 inningType이 top(초)이면 그 score는 away팀 스코어이다. (bottom이면 반대)
        String inningType = halfInning.getInningType();
        int score = halfInning.getScore();
        boolean isEnd = halfInning.isEnd();
        if (inningType.equals(InningType.TOP.toString())) {
            awayScore = score;
            if (isEnd) awayScoreList.add(awayScore);
        }
        if (inningType.equals(InningType.BOTTOM.toString())) {
            homeScore = score;
            if (isEnd) homeScoreList.add(homeScore);
        }
    }

    public int getHomeTotal() {
        for (Integer homeScore : homeScoreList) {
            homeTotal += homeScore;
        }
        return homeTotal;
    }

    public int getAwayTotal() {
        for (Integer awayScore : awayScoreList) {
            awayTotal += awayScore;
        }
        return awayTotal;
    }
}
