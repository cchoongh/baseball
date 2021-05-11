package com.team18.baseball.entity;

import com.team18.baseball.dto.pitcherResult.PitchResult;
import org.springframework.data.annotation.Id;

public class HalfInning {
    @Id
    private final Long id;
    private final int inning;
    private final String inningType;
    private int score;
    private String playingStatus;

    HalfInning(Long id,
               int inning, String inningType) {
        this.id = id;
        this.inning = inning;
        this.inningType = inningType;
        this.score = 0;
        this.playingStatus = PlayingStatus.IS_PLAYING.name();
    }

    public static final HalfInning create(int inning, String inningType) {
        return new HalfInning(null,
                inning, inningType);
    }

    public static final HalfInning createNext(HalfInning lastHalfInning) {
        if(lastHalfInning.inningType.toString().equals(InningType.TOP.toString())) {
            return new HalfInning(null,
                    lastHalfInning.getInning(), InningType.BOTTOM.toString());
        }
        return new HalfInning(null,
                lastHalfInning.getInning(), InningType.TOP.toString());
    }

    public int getInning() {
        return inning;
    }

    public String getInningType() {
        return inningType;
    }

    private boolean isPlaying() {
        return playingStatus.equals(PlayingStatus.IS_PLAYING.name());
    }

    public int getScore() {
        return score;
    }

    //home팀이면은 top일 때 pitch가능
    public void update(PitchResult pitchResult, TeamType teamType) {
        if(isPlaying()) {
            throw new IllegalStateException();
        }
        if((teamType == TeamType.HOME) && (inningType.equals(InningType.BOTTOM.toString()))) {
            throw new IllegalStateException();
        }
        if((teamType == TeamType.AWAY) && (inningType.equals(InningType.TOP.toString()))) {
            throw new IllegalStateException();
        }
        score = pitchResult.getScore().getBattingScore();
    }


}