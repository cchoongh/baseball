package com.team18.baseball.service;

import com.team18.baseball.dto.TeamSelectionData;
import com.team18.baseball.dto.TeamsInGameDto;
import com.team18.baseball.entity.*;
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

    public boolean selectTeam(User user, Long teamId) {
        Team team = teamRepository.findById(teamId).orElseThrow(IllegalStateException::new);
        if(!team.selectTeam(user.getId())) {
            return false;
        }
        teamRepository.save(team);
        return true;
    }

    public void start(User user, Long gameId) {
        //user가 속한 게임이 맞는지 확인한다
//        TeamRepository.
        //이닝을 생성하고 game 정보를 로드한다.
    }
}
