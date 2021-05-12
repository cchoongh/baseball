package com.team18.baseball.entity.game;

import com.team18.baseball.dto.pitchResult.PitchResult;
import com.team18.baseball.dto.pitchResult.PitchResultDto;
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

    public void updatePitchResult(PitchResult pitchResult) {
        if(!isPlaying()) {
            throw new IllegalStateException();
        }
        score = pitchResult.getScore().getAwayScore();
    }


    public void end() {
        this.playingStatus = PlayingStatus.END.name();
    }
}
