package com.team18.baseball.entity;

import org.springframework.data.annotation.Id;

import java.util.*;
import java.util.stream.Collectors;

public class Game {
    @Id
    private final Long id;
    private List<HalfInning> halfInningList = new ArrayList<>();
    private Map<String, GameHasTeam> teams = new HashMap<>();
    private Long homeUserId;
    private Long awayUserId;


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


    public Long getHomeUserId() {
        return homeUserId;
    }

    public Long getAwayUserId() {
        return awayUserId;
    }

    private GameHasTeam getHomeTeamInfo() {
        return teams.get(TeamType.HOME.toString());
    }

    private GameHasTeam getAwayTeamInfo() {
        return teams.get(TeamType.AWAY.toString());
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
                .collect(Collectors.toMap(Map.Entry::getKey,
                        e -> e.getValue().getTeamId()));
    }

    private Optional<String> checkTeamType(Long teamId) {
        return getTeamIdsInGame().entrySet()
                .stream()
                .filter(e -> Objects.equals(e.getValue(), teamId))
                .map(Map.Entry::getKey)
                .findFirst();
    }

    public boolean teamExists(Long teamId) {
        return getTeamIdsInGame().containsValue(teamId);
    }

    public boolean hasTwoTeams() {
        return homeTeamExist() && awayTeamExist();
    }

    private boolean homeTeamExist() {
        return this.homeUserId != null;
    }

    private boolean awayTeamExist() {
        return this.awayUserId != null;
    }

    public boolean isPlaying() {
        return halfInningList.size() != 0;
    }

//    private void addHomeTeam(Team team) {
//        this.teams.put(TeamType.HOME.toString(), GameHasTeam.create(team.getId()));
//    }
//
//    private void addAwayTeam(Team team) {
//        this.teams.put(TeamType.AWAY.toString(), GameHasTeam.create(team.getId()));
//    }

    @Override
    public String toString() {
        return "Game{" +
                "id=" + id +
                ", halfInningList=" + halfInningList +
                ", teams=" + teams +
                '}';
    }

    public void addUserId(Long teamId, Long userId) {
        String teamType = checkTeamType(teamId).orElseThrow(IllegalStateException::new);
        if(teamType.equals(TeamType.HOME.toString())) {
            this.homeUserId = userId;
        } else {
            this.awayUserId = userId;
        }
    }
}
