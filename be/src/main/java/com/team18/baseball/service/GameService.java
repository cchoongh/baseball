package com.team18.baseball.service;

import com.team18.baseball.dto.*;
import com.team18.baseball.dto.request.PitchResult;
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
       if(!game.isReadyToStart()) {
           throw new IllegalStateException();
       }
        //그 중 한 명이 해당 user 인가
        if (!game.checkUser(user.getId())) {
            throw new IllegalStateException();
        }

        //이닝을 생성하고 game 정보를 로드한다.
        HalfInning halfInning = game.addHalfInning();
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

    public void pitch(User user, Long gameId, PitchResult pitchResult) {
        //위의 메소드랑 중복
        Game game = gameRepository.findById(gameId).orElseThrow(IllegalStateException::new);
        if (!game.checkUser(user.getId())) {
            throw new IllegalStateException();
        }

        if(!game.isPlaying()) {
            throw new IllegalStateException();
        }

        //pitch 결과를 halfInning에 반영한다. (이닝 추가할 때, 9회말 9회 끝이면 추가 예정.. 검증 로직.. 검증 많아..)
        List<HalfInning> halfInnings = game.getHalfInnings();
        HalfInning lastHalfInning = halfInnings.get(halfInnings.size() - 1);
        lastHalfInning.update(pitchResult);
    }

    //이닝이 끝났다고 post 할 때
    //결과를 game score에 반영, inning end 반영

    // 게임 end라고 post 해주세용

    //is Playing 부분 다 삭제할 듯 ..
}
