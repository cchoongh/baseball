package com.team18.baseball.entity;

import com.team18.baseball.dto.request.PitchResult;
import org.springframework.data.annotation.Id;

public class HalfInning {
    @Id
    private final Long id;
    private final int inning;
    private final String inningType;
    private int score;
    private boolean isEnd;

    HalfInning(Long id,
               int inning, String inningType,
               int score,
               boolean isEnd) {
        this.id = id;
        this.inning = inning;
        this.inningType = inningType;
        this.score = score;
        this.isEnd = isEnd;
    }

    public static final HalfInning create(int inning, String inningType) {
        return new HalfInning(null,
                inning, inningType,
                0,
                false);
    }

    public static final HalfInning createNext(HalfInning lastHalfInning) {
        if(lastHalfInning.inningType.toString().equals(InningType.TOP.toString())) {
            return new HalfInning(null,
                    lastHalfInning.getInning(), InningType.BOTTOM.toString(),
                    0,
                    false);
        }
        return new HalfInning(null,
                lastHalfInning.getInning(), InningType.TOP.toString(),
                0,
                false);
    }

    public int getInning() {
        return inning;
    }

    public String getInningType() {
        return inningType;
    }

    public boolean isEnd() {
        return isEnd;
    }

    @Override
    public String toString() {
        return "HalfInning{" +
                "id=" + id +
                ", inning=" + inning +
                ", inningType='" + inningType + '\'' +
                ", score=" + score +
                ", isEnd=" + isEnd +
                '}';
    }

    public void update(PitchResult pitchResult) {
        if(isEnd) {
            throw new IllegalStateException();
        }
        score = pitchResult.getScore().getBatting_team();
    }
}
