package com.team18.baseball.entity;

import org.springframework.data.annotation.Id;

import java.util.*;
import java.util.stream.Collectors;

public class Game {
    @Id
    private final Long id;
    private List<HalfInning> halfInningList = new ArrayList<>();
    private Map<String, GameHasTeam> teams = new HashMap<>();
//    private Long homeTeamUserID;
//    private Long awayTeamUserID;


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

//    public Map<String, GameHasTeam> getTeams() {
//        return teams;
//    }

    private GameHasTeam getHomeTeamInfo() {
        return teams.get(TeamType.HOME.toString());
    }

    private GameHasTeam getAwayTeamInfo() {
        return teams.get(TeamType.AWAY.toString());
    }

    private void addHomeTeam(Team team) {
        this.teams.put(TeamType.HOME.toString(), GameHasTeam.create(team.getId()));
    }

    private void addAwayTeam(Team team) {
        this.teams.put(TeamType.AWAY.toString(), GameHasTeam.create(team.getId()));
    }

//    public GameHasTeam getTeamInGameInfo(TeamType teamType) {
//        GameHasTeam gameHasTeam;
//        if (teamType == TeamType.HOME) {
//            gameHasTeam = getHomeTeamInfo();
//        } else {
//            gameHasTeam = getAwayTeamInfo();
//        }
//        return gameHasTeam;
//    }

    public Map<String, Long> getTeamIdsInGame() {
        return teams.entrySet().stream()
                .collect(Collectors.toMap(e -> e.getKey(),
                        e -> e.getValue().getTeamId()));
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
