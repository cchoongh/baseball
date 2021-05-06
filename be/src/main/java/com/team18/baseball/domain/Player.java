package com.team18.baseball.domain;

import org.springframework.data.annotation.Id;

public class Player {
    @Id
    private final Long id;
    private final Long team;
    private final String name;


    Player(Long id, Long team, String name) {
        this.id = id;
        this.team = team;
        this.name = name;
    }

    public static Player create(Long id, Long team, String name) {
        return new Player(id, team, name);
    }
}
