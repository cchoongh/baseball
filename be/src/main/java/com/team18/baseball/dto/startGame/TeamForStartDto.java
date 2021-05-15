package com.team18.baseball.dto.startGame;

import com.team18.baseball.dto.TeamDto;
import com.team18.baseball.entity.Player;
import com.team18.baseball.entity.Team;
import com.team18.baseball.entity.game.TeamRole;

import java.util.ArrayList;
import java.util.List;

public class TeamForStartDto extends TeamDto {
    private final String mode;
    private final int score;
    private final PlayerDto pitcher;
    private final List<PlayerDto> batters;

    private TeamForStartDto(Long id, String name,
                            String mode,
                            int score,
                            PlayerDto pitcher, List<PlayerDto> batters) {
        super(id, name);
        this.mode = mode;
        this.score = score;
        this.pitcher = pitcher;
        this.batters = batters;
    }

    public static TeamForStartDto from(Team team, TeamRole teamRole, int score) {
        PlayerDto pitcher = null;
        List<PlayerDto> batters = new ArrayList<>();

        for (Player player : team.getPlayers()) {
            PlayerDto playerDto = PlayerDto.from(player);
            if (player.isPitcher()) {
                pitcher = playerDto;
            }
            batters.add(playerDto);
        }

        return new TeamForStartDto(team.getId(), team.getName(), teamRole.name(), score, pitcher, batters);
    }

    public String getMode() {
        return mode;
    }

    public int getScore() {
        return score;
    }

    public PlayerDto getPitcher() {
        return pitcher;
    }

    public List<PlayerDto> getBatters() {
        return batters;
    }
}
