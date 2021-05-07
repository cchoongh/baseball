package com.team18.baseball.entity;

import org.springframework.data.annotation.Id;

import java.util.List;

public class Team {
    @Id
    private Long id;
    private final String name;
    private List<Player> players;

    Team(Long id, String name, List<Player> players) {
        this.id = id;
        this.name = name;
        this.players = players;
    }

    public static Team create(Long id, String name, List<Player> players) {
        return new Team(id, name, players);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}