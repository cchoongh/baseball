package com.team18.baseball.entity;

import org.springframework.data.annotation.Id;

public class Player {
    @Id
    private final Long id;
    private final Long team;
    private final String name;
    private final int uniformNumber;
    private boolean isPitcher;

    Player(Long id, Long team, String name, int uniformNumber, boolean isPitcher) {
        this.id = id;
        this.team = team;
        this.name = name;
        this.uniformNumber = uniformNumber;
        this.isPitcher = isPitcher;
    }

    public static Player create(Long id, Long team, String name, int uniformNumber) {
        return new Player(id, team, name, uniformNumber, false);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getUniformNumber() {
        return uniformNumber;
    }

    public boolean isPitcher() {
        return isPitcher;
    }
}
