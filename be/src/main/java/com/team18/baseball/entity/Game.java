package com.team18.baseball.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.Id;

import java.util.*;
import java.util.stream.Collectors;

public class Game {
    @Id
    private final Long id;
    private List<HalfInning> halfInnings;
    private Map<String, GameHasTeam> teams;
    private Long homeUserId;
    private Long awayUserId;
    private String playingStatus;

    Game(Long id) {
        this.id = id;
        halfInnings = new ArrayList<>();
        teams = new HashMap<>();
        playingStatus = PlayingStatus.READY.name();
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

    public String checkStatus() {
        return playingStatus;
    }

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

    @JsonIgnore
    public HalfInning getLastHalfInning() {
        return halfInnings.get(halfInnings.size()-1);
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

//    private void addHomeTeam(Team team) {
//        this.teams.put(TeamType.HOME.toString(), GameHasTeam.create(team.getId()));
//    }
//
//    private void addAwayTeam(Team team) {
//        this.teams.put(TeamType.AWAY.toString(), GameHasTeam.create(team.getId()));
//    }

    public TeamType checkUser(Long userId) {
        if (homeUserId.equals(userId)) {
            return TeamType.HOME;
        }
        if (awayUserId.equals(userId)) {
            return TeamType.AWAY;
        }
        throw new IllegalStateException();
    }

    public boolean isStarted() {
        return halfInnings.size() != 0;
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
        if((teamType.equals(TeamType.HOME.name())) && (this.homeUserId == null)) {
            this.homeUserId = userId;
        }

        if((teamType.equals(TeamType.AWAY.name())) && (this.awayUserId == null)) {
            this.awayUserId = userId;
        }
    }

    public void deleteUser(Long userId) {
        if((homeUserId != null ) && (homeUserId.equals(userId))) {
            this.homeUserId = null;
        }
        if((awayUserId != null) && (awayUserId.equals(userId))) {
            this.awayUserId = null;
        }
    }

    public HalfInning addHalfInning() {
        if(halfInnings.size() == 0) {
            HalfInning halfInning = HalfInning.create(1,  InningType.TOP.name());
            halfInnings.add(halfInning);
            return halfInning;
        }

        HalfInning lastInning = getLastHalfInning();
        if(lastInning.getInningType().equals(InningType.TOP.name())) {
            HalfInning halfInning = HalfInning.create(lastInning.getInning(), InningType.BOTTOM.name());
            halfInnings.add(halfInning);
            return halfInning;
        }
        HalfInning halfInning = HalfInning.create(lastInning.getInning() + 1, InningType.TOP.name());
        halfInnings.add(halfInning);
        return halfInning;
    }

    public void changeStatus(PlayingStatus isPlaying) {
        this.playingStatus = isPlaying.name();
    }

}
