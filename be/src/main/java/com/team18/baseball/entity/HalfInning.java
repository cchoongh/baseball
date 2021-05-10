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

    public int getScore() {
        return score;
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

    //home팀이면은 top일 때 pitch가능
    public void update(PitchResult pitchResult, TeamType teamType) {
        if(isEnd) {
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
