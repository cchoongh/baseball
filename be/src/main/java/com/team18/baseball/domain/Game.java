package com.team18.baseball.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Embedded;

import java.util.List;

public class Game {
    @Id
    private final Long id;
    @Embedded.Empty
    private final Match match;
    private final List<HalfInning> halfInningList;

    Game(Long id, Match match, List<HalfInning> halfInningList) {
        this.id = id;
        this.match = match;
        this.halfInningList = halfInningList;
    }

    public static final Game create(Long id, Match match, List<HalfInning> halfInningList) {
        return new Game(id, match, halfInningList);
    }
}
