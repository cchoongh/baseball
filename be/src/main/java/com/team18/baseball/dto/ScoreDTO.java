package com.team18.baseball.dto;

import com.team18.baseball.entity.game.Game;
import com.team18.baseball.entity.game.HalfInning;
import com.team18.baseball.entity.game.InningType;
import com.team18.baseball.entity.game.PlayingStatus;

import java.util.ArrayList;
import java.util.List;

public class ScoreDTO {

    private String homeName;
    private String awayName;
    private int homeScore;
    private int awayScore;
    private List<Integer> homeScoreList;
    private List<Integer> awayScoreList;
    private int homeTotal;
    private int awayTotal;

    public ScoreDTO(String homeName, String awayName) {
        this.homeName = homeName;
        this.awayName = awayName;
    }

//    private ScoreDTO(String homeName, String awayName, List<Integer> homeScoreList,
//                     List<Integer> awayScoreList, int homeTotal, int awayTotal) {
//        this.homeName = homeName;
//        this.awayName = awayName;
//        this.homeScoreList = homeScoreList;
//        this.awayScoreList = awayScoreList;
//        this.homeTotal = homeTotal;
//        this.awayTotal = awayTotal;
//    }

    public static ScoreDTO create(String homeName, String awayName) {
        return new ScoreDTO(homeName, awayName);
    }

//    public static ScoreDTO create(String homeName, String awayName, List<Integer> homeScoreList,
//                                  List<Integer> awayScoreList, int homeTotal, int awayTotal) {
//        return new ScoreDTO(homeName, awayName, homeScoreList, awayScoreList, homeTotal, awayTotal);
//    }

    public List<Integer> makeHomeScore(Game game) {
        List<HalfInning> halfInnings = game.getHalfInnings();
        List<Integer> homeScoreList = new ArrayList<>();
        for (HalfInning halfInning : halfInnings) {
            int score = halfInning.getScore();
            String inningType = halfInning.getInningType();
            String playingStatus = halfInning.getPlayingStatus();
            if (inningType.equals(InningType.BOTTOM.name())) {
                homeScore = score;
                if (playingStatus.equals(PlayingStatus.END.name())) {
                    homeScoreList.add(homeScore);
                }
                if(playingStatus.equals(PlayingStatus.IS_PLAYING.name())) {
                    if(homeScoreList.size() == 0) {
                        homeScoreList.add(0, homeScore);
                    }
                    homeScoreList.set(halfInning.getInning() - 1, homeScore);
                }

            }
        }
        this.homeScoreList = homeScoreList;
        setHomeTotal();
        return homeScoreList;
    }

    public List<Integer> makeAwayScore(Game game) {
        List<HalfInning> halfInnings = game.getHalfInnings();
        List<Integer> awayScoreList = new ArrayList<>();
        for (HalfInning halfInning : halfInnings) {
            int score = halfInning.getScore();
            String inningType = halfInning.getInningType();
            String playingStatus = halfInning.getPlayingStatus();
            if (inningType.equals(InningType.TOP.name())) {
                awayScore = score;
                if (playingStatus.equals(PlayingStatus.END.name())) awayScoreList.add(awayScore);
                if(playingStatus.equals(PlayingStatus.IS_PLAYING.name())) {
                    if(awayScoreList.size() == 0) {
                        awayScoreList.add(0, awayScore);
                    }
                    awayScoreList.set(halfInning.getInning() - 1, awayScore);
                }
            }
        }
        this.awayScoreList = awayScoreList;
        setAwayTotal();
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

    public String getHomeName() {
        return homeName;
    }

    public String getAwayName() {
        return awayName;
    }

    public List<Integer> getHomeScoreList() {
        return homeScoreList;
    }

    public List<Integer> getAwayScoreList() {
        return awayScoreList;
    }

    public int getHomeTotal() {
        return homeTotal;
    }

    public int getAwayTotal() {
        return awayTotal;
    }
}
