package com.team18.baseball.entity;

import org.springframework.data.annotation.Id;

public class Player {
    @Id
    private final Long id;
    private final Long team;
    private final String name;
    private boolean isPitcher;

    Player(Long id, Long team, String name, boolean isPitcher) {
        this.id = id;
        this.team = team;
        this.name = name;
        this.isPitcher = isPitcher;
    }

    public static Player create(Long id, Long team, String name) {
        return new Player(id, team, name, false);
    }
}
