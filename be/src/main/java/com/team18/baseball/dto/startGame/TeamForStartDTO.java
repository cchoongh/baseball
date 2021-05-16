package com.team18.baseball.dto.startGame;

import com.team18.baseball.dto.TeamDto;
import com.team18.baseball.entity.Player;
import com.team18.baseball.entity.Team;
import com.team18.baseball.entity.game.TeamRole;

import java.util.ArrayList;
import java.util.List;

public class TeamForStartDTO extends TeamDto {
    private final String mode;
    private final int score;
    private final PlayerDTO pitcher;
    private final List<PlayerDTO> batters;

    private TeamForStartDTO(Long id, String name,
                            String mode,
                            int score,
                            PlayerDTO pitcher, List<PlayerDTO> batters) {
        super(id, name);
        this.mode = mode;
        this.score = score;
        this.pitcher = pitcher;
        this.batters = batters;
    }

    public static TeamForStartDTO from(Team team, TeamRole teamRole, int score) {
        PlayerDTO pitcher = null;
        List<PlayerDTO> batters = new ArrayList<>();

        for (Player player : team.getPlayers()) {
            PlayerDTO playerDto = PlayerDTO.from(player);
            if (player.isPitcher()) {
                pitcher = playerDto;
            }
            batters.add(playerDto);
        }

        return new TeamForStartDTO(team.getId(), team.getName(), teamRole.name(), score, pitcher, batters);
    }

    public String getMode() {
        return mode;
    }

    public int getScore() {
        return score;
    }

    public PlayerDTO getPitcher() {
        return pitcher;
    }

    public List<PlayerDTO> getBatters() {
        return batters;
    }
}
