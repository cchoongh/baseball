package com.team18.baseball.entity;

import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Game {
    @Id
    private final Long id;
//    private List<HalfInning> halfInningList = new ArrayList<>();
    private Map<TeamType, GameHasTeam> teams = new HashMap<>();


    Game(Long id) {
        this.id = id;
    }

    public static final Game create(Long id) {
        return new Game(id);
    }

    public Long getId() {
        return id;
    }

//    public List<HalfInning> getHalfInningList() {
//        return halfInningList;
//    }

    public Map<TeamType, GameHasTeam> getTeams() {
        return teams;
    }
//
//    public void addHomeTeam(Team team) {
//        this.teams.put(TeamType.home(), GameHasTeam.create(team.getId()));
//    }
//
//    public void addAwayTeam(Team team) {
//        this.teams.put(TeamType.away(), GameHasTeam.create(team.getId()));
//    }
}
