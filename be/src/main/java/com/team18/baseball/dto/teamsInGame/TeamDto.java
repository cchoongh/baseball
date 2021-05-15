package com.team18.baseball.dto.teamsInGame;

import com.team18.baseball.entity.Team;

public class TeamDto {
    private final Long id;
    private final String name;
    private final Boolean selected;

    private TeamDto(Long id, String name, Boolean selected) {
        this.id = id;
        this.name = name;
        this.selected = selected;
    }

    public static TeamDto from(Team team) {
        return new TeamDto(team.getId(), team.getName(), team.selected());
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Boolean getSelected() {
        return selected;
    }
}
