package com.team18.baseball.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Embedded;
import org.springframework.data.relational.core.mapping.MappedCollection;

import java.util.List;
import java.util.Map;

public class Game {
    @Id
    private final Long id;
    private final List<HalfInning> halfInningList;
    private Map<TeamType, MatchHasTeam> teams;


    Game(Long id, List<HalfInning> halfInningList) {
        this.id = id;
        this.halfInningList = halfInningList;
    }

    public static final Game create(Long id, Match match, List<HalfInning> halfInningList) {
        return new Game(id, halfInningList);
    }
}
