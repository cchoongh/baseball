package com.team18.baseball.entity;

import org.springframework.data.annotation.Id;

import java.util.List;

public class Team {
    @Id
    private final Long id;
    private final String name;
    private List<Player> players;
    private Long userId;

    Team(Long id, String name, List<Player> players, Long userId) {
        this.id = id;
        this.name = name;
        this.players = players;
        this.userId = userId;

    }
    public static Team create(Long id, String name, List<Player> players) {
        return new Team(id, name, players, null);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Long getUserId() {
        return userId;
    }

    public boolean selectTeam(Long userId) {
        if(this.userId != null) {
            return false;
        }
        this.userId = userId;
        return true;
    }
}
