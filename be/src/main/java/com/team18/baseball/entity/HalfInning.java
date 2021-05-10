package com.team18.baseball.entity;

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

    public int getInning() {
        return inning;
    }

    public String getInningType() {
        return inningType;
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
}
