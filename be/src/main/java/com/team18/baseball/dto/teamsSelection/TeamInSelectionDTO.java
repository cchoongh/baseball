package com.team18.baseball.dto.teamsSelection;

import com.team18.baseball.dto.TeamDto;
import com.team18.baseball.entity.Team;

public class TeamInSelectionDTO extends TeamDto {
    private final Boolean selected;

    private TeamInSelectionDTO(Long id, String name, Boolean selected) {
        super(id, name);
        this.selected = selected;
    }

    public static TeamInSelectionDTO from(Team team) {
        return new TeamInSelectionDTO(team.getId(), team.getName(), team.selected());
    }

    public Boolean getSelected() {
        return selected;
    }
}
