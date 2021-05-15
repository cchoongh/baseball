package com.team18.baseball.entity.game;

import com.team18.baseball.entity.GameHasTeam;
import org.springframework.data.annotation.Id;

import java.util.*;
import java.util.stream.Collectors;

public class Game {
    @Id
    private final Long id;
    private final List<HalfInning> halfInnings;
    private final Map<String, GameHasTeam> teams;
    private Long homeUserId, awayUserId;
    private String playingStatus;

    Game(Long id, List<HalfInning> halfInnings, Map<String, GameHasTeam> teams, String playingStatus) {
        this.id = id;
        this.halfInnings = halfInnings;
        this.teams = teams;
        this.playingStatus = playingStatus;
    }

    public static Game create() {
        return new Game(null, new ArrayList<>(), new HashMap<>(), PlayingStatus.READY.name());
    }

    public Long getId() {
        return id;
    }

    public List<HalfInning> getHalfInnings() {
        return this.halfInnings;
    }

    public Map<String, GameHasTeam> getTeams() {
        return this.teams;
    }

    public GameHasTeam getGameHasTeam(TeamType teamType) {
        return teams.get(teamType.toString());
    }

    public String checkStatus() {
        return playingStatus;
    }

    public HalfInning getLastHalfInning() {
        if (halfInnings.size() < 1) {
            throw new IllegalStateException();
        }
        return halfInnings.get(halfInnings.size()-1);
    }

    public int getLastHalfInningIndex() {
        return halfInnings.size();
    }

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

    public void addUserId(String teamType, Long userId) {
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

    public void changeStatus(PlayingStatus isPlaying) {
        this.playingStatus = isPlaying.name();
    }

    public void end() {
        playingStatus = PlayingStatus.END.name();
    }
}
