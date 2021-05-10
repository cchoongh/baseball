package com.team18.baseball.service;

import com.team18.baseball.dto.*;
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

    public List<TeamsInGame> getTeamsInGameList() {
        return Streamable.of(gameRepository.findAll()).map((game) -> getTeamsInGame(game)).toList();
    }

    private TeamsInGame getTeamsInGame(Game game) {
        TeamSelectionData homeData = getTeamSelectionData(game, TeamType.HOME);
        TeamSelectionData awayData = getTeamSelectionData(game, TeamType.AWAY);
        return TeamsInGame.from(game.getId(), homeData, awayData);
    }

    private TeamSelectionData getTeamSelectionData(Game game, TeamType teamType) {
        Long teamId = game.getTeamIdsInGame().get(teamType.toString());
        Team team = teamRepository.findById(teamId).orElseThrow(IllegalStateException::new);
        return TeamSelectionData.from(team);
    }

    public boolean selectTeam(User user, Long gameId, Long teamId) {
        // 이 게임이 존재하는 게임인지 확인한다.
        Game game = gameRepository.findById(gameId).orElseThrow(IllegalStateException::new);
        // 이 게임이 해당 팀을 갖고 있는지 확인한다
        if (!game.teamExists(teamId)) {
            throw new IllegalStateException();
        }

        //user가 team을 선택한다.
        Team team = teamRepository.findById(teamId).orElseThrow(IllegalStateException::new);
        if (!team.selectTeam(user.getId())) {
            return false;
        }
        teamRepository.save(team);

        // game에 userId를 기록한다.
        game.addUserId(teamId, user.getId());
        gameRepository.save(game);
        return true;
    }

    public StartGameInfo start(User user, Long gameId) {
        Game game = gameRepository.findById(gameId).orElseThrow(IllegalStateException::new);

        //메소드 묶어줘야겠다.
        //2명의 유저가 이 게임을 선택했는가.
        if (!game.hasTwoUsers()) {
            throw new IllegalStateException();
        }
        //그 중 한 명이 해당 user 인가
        if (!game.checkUser(user.getId())) {
            throw new IllegalStateException();
        }

        //game이 이미 진행 중일 때 예외발생
        if ( game.isPlaying()) {
            throw new IllegalStateException();
        }

        //이닝을 생성하고 game 정보를 로드한다.
        HalfInning halfInning = game.addInning();
        gameRepository.save(game);

        //응답객체를 만든다.
        GameInfo gameInfo = GameInfo.from(game, halfInning);
        //home팀은 항상 bottom에서 공격
        //away팀일 항상 top에서 공격
        GameHasTeam homeTeamInfo = game.getHomeTeamInfo();
        GameHasTeam awayTeamInfo = game.getAwayTeamInfo();
        Team homeTeam = teamRepository.findById(homeTeamInfo.getId()).orElseThrow(IllegalStateException::new);
        Team awayTeam = teamRepository.findById(awayTeamInfo.getId()).orElseThrow(IllegalStateException::new);
        TeamInfo fieldingInfo = TeamInfo.from(homeTeam, TeamType.HOME, homeTeamInfo.getScore());
        TeamInfo battingInfo = TeamInfo.from(awayTeam, TeamType.AWAY, homeTeamInfo.getScore());

        return StartGameInfo.from(gameInfo, fieldingInfo, battingInfo);
    }
}
