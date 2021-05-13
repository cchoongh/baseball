package com.team18.baseball.entity.game;

import com.team18.baseball.entity.GameHasTeam;

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

    public String checkStatus() {
        return playingStatus;
    }

    public GameHasTeam getGameHasTeam(TeamType teamType) {
        return teams.get(teamType.toString());
    }

    public List<HalfInning> getHalfInnings() {
        return this.halfInnings;
    }

    public HalfInning getLastHalfInning() {
        if (halfInnings.size() < 1) {
            throw new IllegalStateException();
        }
        return halfInnings.get(halfInnings.size()-1);
    }

    private Map<String, Long> getTeamIds() {
        return teams.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey,
                        e -> e.getValue().getTeamId()));
    }

    public Long getHomeTeamId() {
        return getTeamIds().get(TeamType.HOME.name());
    }

    public Long getAwayTeamId() {
        return getTeamIds().get(TeamType.AWAY.name());
    }

    private Optional<String> checkTeamType(Long teamId) {
        return getTeamIds().entrySet()
                .stream()
                .filter(e -> Objects.equals(e.getValue(), teamId))
                .map(Map.Entry::getKey)
                .findFirst();
    }

    public boolean hasTeam(Long teamId) {
        return getTeamIds().containsValue(teamId);
    }

//    public TeamType getTeamType(Long teamId) {
//        if(!getTeamIds().containsValue(teamId)) {
//            throw new IllegalStateException();
//        }
//        return TeamType.valueOf(checkTeamType(teamId).get());
//    }

    public boolean hasTwoUsers() {
        return homeUserExist() && awayUserExist();
    }

    private boolean homeUserExist() {
        return this.homeUserId != null;
    }

    private boolean awayUserExist() {
        return this.awayUserId != null;
    }


    public Optional<TeamType> checkUser(Long userId) {
        if ((homeUserId != null) && (homeUserId.equals(userId))) {
            return Optional.of(TeamType.HOME);
        }
        if ((awayUserId != null) && (awayUserId.equals(userId))) {
            return Optional.of(TeamType.AWAY);
        }
        return Optional.empty();
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

    public void addFirstHalfInning() {
        if(halfInnings.size() != 0) {
            throw new IllegalStateException();
        }
        HalfInning halfInning = HalfInning.create(1,  InningType.TOP.name());
        halfInnings.add(halfInning);
        changeStatus(PlayingStatus.IS_PLAYING);
    }

    public void changeStatus(PlayingStatus isPlaying) {
        this.playingStatus = isPlaying.name();
    }

    public void end() {
        playingStatus = PlayingStatus.END.name();
    }

    public int getLastHalfInningIndex() {
        return halfInnings.size();
    }
}
