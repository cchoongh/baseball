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
        TeamSelectionData homeData = getTeamSelectionData(game, TeamType.HOME);
        TeamSelectionData awayData = getTeamSelectionData(game, TeamType.AWAY);
        return TeamsInGameDto.from(game.getId(), homeData, awayData);
    }

    public TeamSelectionData getTeamSelectionData(Game game, TeamType teamType) {
        Long teamId = game.getTeamIdsInGame().get(teamType.toString());
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
        Game game = gameRepository.findById(gameId).orElseThrow(IllegalStateException::new);
        if(!checkPlayer(user, game)) {
            throw new IllegalStateException();
        };
        if(!game.isReady()) {
            throw new IllegalStateException();
        }
        //이닝을 생성하고 game 정보를 로드한다.
    }

    public boolean checkPlayer(User user, Game game) {
        //팀을 선택 안한 유저라면 예외가 발생한다.
        Team team = teamRepository.findByUserId(user.getId()).orElseThrow(IllegalStateException::new);
        if(!game.teamExists(team.getId())) {
            return false;
        }
        return true;
    }
}
