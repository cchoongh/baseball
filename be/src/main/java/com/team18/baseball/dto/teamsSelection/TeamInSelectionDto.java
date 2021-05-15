package com.team18.baseball.dto.teamsSelection;

import com.team18.baseball.dto.TeamDto;
import com.team18.baseball.entity.Team;

public class TeamInSelectionDto extends TeamDto {
    private final Boolean selected;

    private TeamInSelectionDto(Long id, String name, Boolean selected) {
        super(id, name);
        this.selected = selected;
    }

    public static TeamInSelectionDto from(Team team) {
        return new TeamInSelectionDto(team.getId(), team.getName(), team.selected());
    }

    public Boolean getSelected() {
        return selected;
    }
}
