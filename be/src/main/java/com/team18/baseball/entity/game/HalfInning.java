package com.team18.baseball.entity.game;

import com.team18.baseball.dto.pitchResult.PitchResult;
import com.team18.baseball.dto.pitchResult.Score;
import com.team18.baseball.entity.battingBoard.BattingRecord;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;

public class HalfInning {
    @Id
    private final Long id;
    private final int inning;
    private final String inningType;
    private final List<PitchResult> pitchesResult;
    private final List<BattingRecord> battingRecords;
    private int score;
    private String playingStatus;

    public HalfInning(Long id,
                      int inning, String inningType,
                      List<PitchResult> pitchesResult, List<BattingRecord> battingRecords,
                      int score,
                      String playingStatus) {
        this.id = id;
        this.inning = inning;
        this.inningType = inningType;
        this.pitchesResult = pitchesResult;
        this.battingRecords = battingRecords;
        this.score = score;
        this.playingStatus = playingStatus;
    }

    public static HalfInning create(int inning, String inningType) {
        return new HalfInning(null,
                inning, inningType,
                new ArrayList<>(), new ArrayList<>(),
                0, PlayingStatus.IS_PLAYING.name());
    }

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

    public List<PitchResult> getPitchesResult() {
        return pitchesResult;
    }

    public List<BattingRecord> getBattingRecords() {
        return battingRecords;
    }

    public int getScore() {
        return score;
    }

    public String checkStatus() {
        return playingStatus;
    }

    public void updateScore(Score score) {
        this.score = score.getAwayScore();
    }

    public void end() {
        this.playingStatus = PlayingStatus.END.name();
    }

    public void addPitchResult(PitchResult pitchResult) {
        pitchesResult.add(pitchResult);
        updateScore(pitchResult.getScore());
    }

    public void addBattingRecord(BattingRecord battingRecord) {
        battingRecords.add(battingRecord);
    }
}
