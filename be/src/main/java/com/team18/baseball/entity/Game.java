package com.team18.baseball.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.Id;

import java.util.*;
import java.util.stream.Collectors;

public class Game {
    @Id
    private final Long id;
    private List<HalfInning> halfInnings = new ArrayList<>();
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

    public List<HalfInning> getHalfInnings() {
        return halfInnings;
    }

//    public Map<String, GameHasTeam> getTeams() {
//        return teams;
//    }

    @JsonIgnore
    public Long getHomeUserId() {
        return homeUserId;
    }

    @JsonIgnore
    public Long getAwayUserId() {
        return awayUserId;
    }

    @JsonIgnore
    public GameHasTeam getHomeTeamInfo() {
        return teams.get(TeamType.HOME.toString());
    }

    @JsonIgnore
    public GameHasTeam getAwayTeamInfo() {
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

    @JsonIgnore
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

    //2명의 유저가 이 게임을 선택했는가
    public boolean hasTwoUsers() {
        return homeUserExist() && awayUserExist();
    }

    private boolean homeUserExist() {
        return this.homeUserId != null;
    }

    private boolean awayUserExist() {
        return this.awayUserId != null;
    }

    public boolean isPlaying() {
        return halfInnings.size() != 0;
    }

//    private void addHomeTeam(Team team) {
//        this.teams.put(TeamType.HOME.toString(), GameHasTeam.create(team.getId()));
//    }
//
//    private void addAwayTeam(Team team) {
//        this.teams.put(TeamType.AWAY.toString(), GameHasTeam.create(team.getId()));
//    }

    public boolean checkUser(Long userId) {
        return (homeUserId.equals(userId)) || ((awayUserId.equals(userId)));
    }

    @Override
    public String toString() {
        return "Game{" +
                "id=" + id +
                ", halfInningList=" + halfInnings +
                ", teams=" + teams +
                '}';
    }

    public void addUserId(Long teamId, Long userId) {
        String teamType = checkTeamType(teamId).orElseThrow(IllegalStateException::new);
        if((teamType.equals(TeamType.HOME.toString())) && (this.homeUserId == null)) {
            this.homeUserId = userId;
        }

        if((teamType.equals(TeamType.AWAY.toString())) && (this.awayUserId == null)) {
            this.awayUserId = userId;
        }
    }

    public HalfInning addInning() {
        if(halfInnings.size() == 0) {
            HalfInning halfInning = HalfInning.create(1,  InningType.TOP.toString());
            halfInnings.add(halfInning);
            return halfInning;
        }

        HalfInning lastInning = halfInnings.get(halfInnings.size()-1);
        if(lastInning.getInningType().equals(InningType.TOP.toString())) {
            HalfInning halfInning = HalfInning.create(lastInning.getInning(), InningType.BOTTOM.toString());
            halfInnings.add(halfInning);
            return halfInning;
        }
        HalfInning halfInning = HalfInning.create(lastInning.getInning() + 1, InningType.TOP.toString());
        halfInnings.add(halfInning);
        return halfInning;
    }
}
