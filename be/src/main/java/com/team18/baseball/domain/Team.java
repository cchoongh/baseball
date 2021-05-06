package com.team18.baseball.domain;

import org.springframework.data.annotation.Id;

import java.util.List;
import java.util.Set;

public class Team {
    @Id
    private final Long id;
    private final String name;
    private final List<Player> players;

    Team(Long id, String name, List<Player> players) {
        this.id = id;
        this.name = name;
        this.players = players;
    }

    public static Team create(Long id, String name, List<Player> players) {
        return new Team(id, name, players);
    }
}
