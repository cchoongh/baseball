package com.team18.baseball.dto;

import com.team18.baseball.entity.*;

import java.util.ArrayList;
import java.util.List;

public class ScoreDTO {

    private String homeName;
    private String awayName;
    //    private boolean isUserHomeTeam;
//    // user의 id를 가져와서 teams의 homeUserId와 일치하면 home
//    private String battingTeam;
//    // 홈 팀은 top(초) 에 항상 수비(fielding)다. away 팀은 top에 항상 공격이다.
//    // 초 에 수비인 팀 = home, 초에 공격인 팀 = away
    private int homeScore;
    private int awayScore;
    private List<Integer> homeScoreList;
    private List<Integer> awayScoreList;
    private int homeTotal;
    private int awayTotal;

    private ScoreDTO(String homeName, String awayName, List<Integer> homeScoreList,
                     List<Integer> awayScoreList, int homeTotal, int awayTotal) {
        this.homeName = homeName;
        this.awayName = awayName;
        this.homeScoreList = homeScoreList;
        this.awayScoreList = awayScoreList;
        this.homeTotal = homeTotal;
        this.awayTotal = awayTotal;
    }

    public static ScoreDTO create(String homeName, String awayName, List<Integer> homeScoreList,
                                  List<Integer> awayScoreList, int homeTotal, int awayTotal) {
        return new ScoreDTO(homeName, awayName, homeScoreList, awayScoreList, homeTotal, awayTotal);
    }


    public List<Integer> makeHomeScore(List<HalfInning> halfInnings) {
        // 홈 팀은 top(초) 에 항상 수비(fielding)다. away 팀은 top에- (반대)
        // HalfInning의 score를 가져올 때 inningType이 top(초)이면 그 score는 away팀 스코어이다. (bottom이면 반대)
        List<Integer> homeScoreList = new ArrayList<>();
        for (HalfInning halfInning : halfInnings) {
            int score = halfInning.getScore();
            String inningType = halfInning.getInningType();
            boolean isEnd = halfInning.isEnd();
            if (inningType.equals(InningType.BOTTOM.toString())) {
                homeScore = score;
                if (isEnd) homeScoreList.add(awayScore);
            }
        }
        return homeScoreList;
    }

    public List<Integer> makeAwayScore(List<HalfInning> halfInnings) {
        List<Integer> awayScoreList = new ArrayList<>();
        for (HalfInning halfInning : halfInnings) {
            int score = halfInning.getScore();
            String inningType = halfInning.getInningType();
            boolean isEnd = halfInning.isEnd();
            if (inningType.equals(InningType.TOP.toString())) {
                awayScore = score;
                if (isEnd) awayScoreList.add(homeScore);
            }
        }
        return awayScoreList;
    }

    public int setHomeTotal() {
        for (Integer homeScore : homeScoreList) {
            homeTotal += homeScore;
        }
        return homeTotal;
    }

    public int setAwayTotal() {
        for (Integer awayScore : awayScoreList) {
            awayTotal += awayScore;
        }
        return awayTotal;
    }
}
