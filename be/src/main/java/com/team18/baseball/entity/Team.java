package com.team18.baseball.entity;

import org.springframework.data.annotation.Id;

import java.util.List;
import java.util.Objects;

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

    public List<Player> getPlayers() {
        return players;
    }

    public boolean selectTeam(Long userId) {
        if(this.userId != null) {
            return false;
        }
        this.userId = userId;
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Team)) return false;
        Team team = (Team) o;
        return getId().equals(team.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    public void unselect() {
        this.userId = null;
    }
}
