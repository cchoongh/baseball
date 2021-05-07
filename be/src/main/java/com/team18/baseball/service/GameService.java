package com.team18.baseball.service;

import com.team18.baseball.dto.TeamSelectionData;
import com.team18.baseball.dto.TeamsInGameDto;
import com.team18.baseball.entity.Game;
import com.team18.baseball.entity.GameHasTeam;
import com.team18.baseball.entity.Team;
import com.team18.baseball.entity.TeamType;
import com.team18.baseball.repository.GameRepository;
import com.team18.baseball.repository.TeamRepository;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class GameService {
    private final GameRepository gameRepository;
    private final TeamRepository teamRepository;

    public GameService(GameRepository gameRepository, TeamRepository teamRepository) {
        this.gameRepository = gameRepository;
        this.teamRepository = teamRepository;
    }

//    public List<Team> getTeams() {
//         return Streamable.of(teamRepository.findAll()).toList();
//    }

    public List<TeamsInGameDto> getTeamsInGameList() {
        return Streamable.of(gameRepository.findAll()).map((game)->getTeamsInGame(game)).toList();
    }

    public TeamsInGameDto getTeamsInGame(Game game) {
        Map<TeamType, GameHasTeam> teams = game.getTeams();
        Long homeTeamId = teams.get(TeamType.home()).getId();
        Long awayTeamId = teams.get(TeamType.away()).getId();
        Team home = teamRepository.findById(homeTeamId).orElseThrow(IllegalStateException::new);
        Team away = teamRepository.findById(awayTeamId).orElseThrow(IllegalStateException::new);
        TeamSelectionData homeData = TeamSelectionData.from(home);
        TeamSelectionData awayData = TeamSelectionData.from(away);
        return TeamsInGameDto.from(game.getId(), homeData, awayData);
    }
}
