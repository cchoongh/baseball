package com.team18.baseball.service;

import com.team18.baseball.dto.TeamSelectionData;
import com.team18.baseball.dto.TeamsInGameDto;
import com.team18.baseball.entity.*;
import com.team18.baseball.repository.GameRepository;
import com.team18.baseball.repository.TeamRepository;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public boolean selectTeam(User user, Long gameId, Long teamId) {
        //이 팀이 존재하는 팀 아이디인지 확인한다.
        Team team = teamRepository.findById(teamId).orElseThrow(IllegalStateException::new);
        // 이 게임이 존재하는 게임인지 확인한다.
        Game game = gameRepository.findById(gameId).orElseThrow(IllegalStateException::new);
        // 이 게임이 해당 팀을 갖고 있는지 확인한다
        if(! game.teamExists(teamId) ) {
            throw new IllegalStateException();
        };
        //user가 team을 선택한다.
        if(!team.selectTeam(user.getId())) {
            return false;
        }
        teamRepository.save(team);
        // game에 userId를 기록한다.
        game.addUserId(teamId, user.getId());
        gameRepository.save(game);
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
