package com.team18.baseball.entity;

import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Game {
    @Id
    private final Long id;
    private List<HalfInning> halfInningList = new ArrayList<>();
    private Map<String, GameHasTeam> teams = new HashMap<>();

    Game(Long id) {
        this.id = id;
    }

    public static final Game create() {
        return new Game(null);
    }

    public Long getId() {
        return id;
    }

    public List<HalfInning> getHalfInningList() {
        return halfInningList;
    }

    public Map<String, GameHasTeam> getTeams() {
        return teams;
    }

    public void addHomeTeam(Team team) {
        this.teams.put(TeamType.HOME.toString(), GameHasTeam.create(team.getId()));
    }

    public void addAwayTeam(Team team) {
        this.teams.put(TeamType.AWAY.toString(), GameHasTeam.create(team.getId()));
    }


    @Override
    public String toString() {
        return "Game{" +
                "id=" + id +
                ", halfInningList=" + halfInningList +
                ", teams=" + teams +
                '}';
    }
}
