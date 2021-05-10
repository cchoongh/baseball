package com.team18.baseball.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.team18.baseball.entity.Player;
import com.team18.baseball.entity.Team;
import com.team18.baseball.entity.TeamType;

import java.util.ArrayList;
import java.util.List;

public class TeamInfo {
    private final Long id;
    private final String name;
    private final String teamType;

    private final int score;

    private PlayerInfo pitcher;
    private List<PlayerInfo> batters;

    private TeamInfo(Long id, String name, String teameType,
                     int score,
                     PlayerInfo pitcher, List<PlayerInfo> batters) {
        this.id = id;
        this.name = name;
        this.teamType = teameType;
        this.score = score;
        this.pitcher = pitcher;
        this.batters = batters;
    }

    public static final TeamInfo from(Team team, TeamType teamType, int score) {
        PlayerInfo pitcher = null;
        List<PlayerInfo> batters = new ArrayList<>();

        for (Player player : team.getPlayers()) {
            PlayerInfo playerInfo = PlayerInfo.from(player);
            if (player.isPitcher()) {
                pitcher = playerInfo;
            }
            batters.add(playerInfo);
        }
        return new TeamInfo(team.getId(), team.getName(), teamType.toString(), score, pitcher, batters);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @JsonProperty("team_type")
    public String getTeamType() {
        return teamType;
    }

    public int getScore() {
        return score;
    }

    public PlayerInfo getPitcher() {
        return pitcher;
    }

    public List<PlayerInfo> getBatters() {
        return batters;
    }
}
