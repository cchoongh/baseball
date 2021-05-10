package com.team18.baseball.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.team18.baseball.entity.Team;

public class TeamSelectionData {
    private final Long id;
    private final String name;
    private final Boolean selected;

    private TeamSelectionData(Long id, String name, Boolean selected) {
        this.id = id;
        this.name = name;
        this.selected = selected;
    }

    public static TeamSelectionData from(Team team) {
        return new TeamSelectionData(team.getId(), team.getName(), false);
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
