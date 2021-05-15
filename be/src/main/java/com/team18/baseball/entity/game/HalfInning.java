package com.team18.baseball.entity.game;

import com.team18.baseball.dto.pitchResult.PitchResult;
import com.team18.baseball.entity.battingBoard.BattingRecord;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;

public class HalfInning {
    @Id
    private final Long id;
    private final int inning;
    private final String inningType;
    private int score;
    private String playingStatus;
    private List<BattingRecord> battingRecords;

    HalfInning(Long id,
               int inning, String inningType) {
        this.id = id;
        this.inning = inning;
        this.inningType = inningType;
        this.score = 0;
        this.playingStatus = PlayingStatus.IS_PLAYING.name();
        this.battingRecords = new ArrayList<>();
    }

    public static final HalfInning create(int inning, String inningType) {
        return new HalfInning(null,
                inning, inningType);
    }

//    public static final HalfInning createNext(HalfInning lastHalfInning) {
//        if(lastHalfInning.inningType.toString().equals(InningType.TOP.toString())) {
//            return new HalfInning(null,
//                    lastHalfInning.getInning(), InningType.BOTTOM.toString());
//        }
//        return new HalfInning(null,
//                lastHalfInning.getInning(), InningType.TOP.toString());
//    }

    public Long getId() {
        return id;
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

    public List<BattingRecord> getBattingRecords() {
        return battingRecords;
    }

    public void updatePitchResult(PitchResult pitchResult) {
        if(!isPlaying()) {
            throw new IllegalStateException();
        }
        score = pitchResult.getScore().getAwayScore();
    }

    public String getPlayingStatus() {
        return playingStatus;
    }

    public void end() {
        this.playingStatus = PlayingStatus.END.name();
    }

    public void addBattingRecord(BattingRecord battingRecord) {
        battingRecords.add(battingRecord);
    }
}
