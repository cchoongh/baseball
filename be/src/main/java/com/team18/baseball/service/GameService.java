package com.team18.baseball.service;

import com.team18.baseball.dto.TeamSelectionData;
import com.team18.baseball.dto.TeamsInGameDto;
import com.team18.baseball.entity.Game;
import com.team18.baseball.entity.GameHasTeam;
import com.team18.baseball.entity.Team;
import com.team18.baseball.entity.TeamType;
import com.team18.baseball.repository.GameRepository;
import com.team18.baseball.repository.PlayerRepository;
import com.team18.baseball.repository.TeamRepository;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class GameService {
    private final GameRepository gameRepository;
    private final TeamRepository teamRepository;
    private final PlayerRepository playerRepository;

    public GameService(GameRepository gameRepository, TeamRepository teamRepository, PlayerRepository playerRepository) {
        this.gameRepository = gameRepository;
        this.teamRepository = teamRepository;
        this.playerRepository = playerRepository;
    }

    public List<TeamsInGameDto> getTeamsInGameList() {
        return Streamable.of(gameRepository.findAll()).map((game)->getTeamsInGame(game)).toList();
    }

    private TeamsInGameDto getTeamsInGame(Game game) {
        Map<String, GameHasTeam> teams = game.getTeams();
        TeamSelectionData homeData = getTeamSelectionData(teams, TeamType.HOME);
        TeamSelectionData awayData = getTeamSelectionData(teams, TeamType.AWAY);
        return TeamsInGameDto.from(game.getId(), homeData, awayData);
    }

    private TeamSelectionData getTeamSelectionData(Map<String, GameHasTeam> teams, TeamType teamType) {
        Long teamId = teams.get(teamType.toString()).getTeamId();
        Team team = teamRepository.findById(teamId).orElseThrow(IllegalStateException::new);
        return TeamSelectionData.from(team);
    }


}
