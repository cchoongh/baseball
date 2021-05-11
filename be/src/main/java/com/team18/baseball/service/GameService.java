package com.team18.baseball.service;

import com.team18.baseball.dto.*;
import com.team18.baseball.dto.pitcherResult.PitchResult;
import com.team18.baseball.entity.*;
import com.team18.baseball.repository.GameRepository;
import com.team18.baseball.repository.HalfInningRepository;
import com.team18.baseball.repository.PitchResultRepository;
import com.team18.baseball.repository.TeamRepository;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GameService {
    private final GameRepository gameRepository;
    private final TeamRepository teamRepository;
    private final HalfInningRepository halfInningRepository;
    private final PitchResultRepository pitchResultRepository;

    public GameService(GameRepository gameRepository, TeamRepository teamRepository, HalfInningRepository halfInningRepository, PitchResultRepository pitchResultRepository) {
        this.gameRepository = gameRepository;
        this.teamRepository = teamRepository;
        this.halfInningRepository= halfInningRepository;
        this.pitchResultRepository = pitchResultRepository;
    }

    public List<TeamsInGame> getTeamsInGameList() {
        return Streamable.of(gameRepository.findAll()).map((game) -> getTeamsInGame(game)).toList();
    }

    private TeamsInGame getTeamsInGame(Game game) {
        TeamSelectionData homeData = getTeamSelectionData(game, TeamType.HOME);
        TeamSelectionData awayData = getTeamSelectionData(game, TeamType.AWAY);
        return TeamsInGame.from(game.getId(), game.checkStatus(), homeData, awayData);
    }

    private TeamSelectionData getTeamSelectionData(Game game, TeamType teamType) {
        Long teamId = game.getTeamIdsInGame().get(teamType.name());
        Team team = teamRepository.findById(teamId).orElseThrow(IllegalStateException::new);
        return TeamSelectionData.from(team);
    }

    public boolean selectTeam(User user, Long gameId, Long teamId) {
//        user가 이미 팀을 선택했는지 확인한다
        if(teamRepository.findByUserId(user.getId()).isPresent()) {
            System.out.println(user.getId());
            System.out.println(teamRepository.findByUserId(user.getId()).get().getUserId());
            throw new IllegalStateException();
        }

        // 이 게임이 존재하는 게임인지 확인한다.
        Game game = gameRepository.findById(gameId).orElseThrow(IllegalStateException::new);
        // 이 게임이 해당 팀을 갖고 있는지 확인한다
        if (!game.teamExists(teamId)) {
            throw new IllegalStateException();
        }
        // game의 상태를 확인한다
        if (!game.checkStatus().equals(PlayingStatus.READY.name())) {
            throw new IllegalStateException();
        }

        //user가 team을 선택한다.
        Team team = teamRepository.findById(teamId).orElseThrow(IllegalStateException::new);
        if (!team.selectTeam(user.getId())) {
            return false;
        }
        teamRepository.save(team);
        // game에 userId를 기록한다. // 이것을 game에 userId기록을 start 이후로 넘긴다
        game.addUserId(team.getId(), user.getId());
        gameRepository.save(game);

        return true;
    }

    public Optional<StartGameInfo> start(User user, Long gameId) {
        Game game = gameRepository.findById(gameId).orElseThrow(IllegalStateException::new);

        //메소드 묶어줘야겠다.
        //2명의 유저가 이 게임을 선택했는가.
        //
        if(game.checkStatus().equals(PlayingStatus.END.name())){
            throw new IllegalStateException();
        }

        if(game.checkStatus().equals(PlayingStatus.IS_PLAYING.name())){
            GameInfo gameInfo = GameInfo.from(game, game.getLastHalfInning());
            //home팀은 항상 bottom에서 공격
            //away팀일 항상 top에서 공격
            GameHasTeam homeTeamInfo = game.getHomeTeamInfo();
            GameHasTeam awayTeamInfo = game.getAwayTeamInfo();
            Team homeTeam = teamRepository.findById(homeTeamInfo.getId()).orElseThrow(IllegalStateException::new);
            Team awayTeam = teamRepository.findById(awayTeamInfo.getId()).orElseThrow(IllegalStateException::new);
            TeamInfo fieldingInfo = TeamInfo.from(homeTeam, TeamType.HOME, homeTeamInfo.getScore());
            TeamInfo battingInfo = TeamInfo.from(awayTeam, TeamType.AWAY, homeTeamInfo.getScore());

            return Optional.of(StartGameInfo.from(gameInfo, fieldingInfo, battingInfo));
        }


       if(!game.hasTwoUsers()) {
           return Optional.empty();
       }
        //그 중 한 명이 해당 user 인가
        TeamType teamType = game.checkUser(user.getId());
       // --------- ,여기 위까지가 검증로기 ----- 아래가 START 되는 것

        //이닝을 생성하고 game 정보를 로드한다.
        HalfInning halfInning = game.addHalfInning();
         //game 상태 변화
        game.changeStatus(PlayingStatus.IS_PLAYING);
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

        return Optional.of(StartGameInfo.from(gameInfo, fieldingInfo, battingInfo));
    }

    public void pitch(User user, Long gameId, PitchResult pitchResult) {
        //위의 메소드랑 중복
        Game game = gameRepository.findById(gameId).orElseThrow(IllegalStateException::new);
        if(!game.checkStatus().equals(PlayingStatus.IS_PLAYING.name())) {
            throw new IllegalStateException();
        }

        //user가 pitch turn인지 체크한다.  (home이면 top일 때 수비)
        TeamType teamType = game.checkUser(user.getId());

        //pitch 결과를 halfInning에 반영한다. (이닝 추가할 때, 9회말 9회 끝이면 추가 예정.. 검증 로직.. 검증 많아..)
        List<HalfInning> halfInnings = game.getHalfInnings();
        HalfInning lastHalfInning = halfInnings.get(halfInnings.size() - 1);
        lastHalfInning.update(pitchResult, teamType);
        halfInningRepository.save(lastHalfInning);

        pitchResultRepository.save(pitchResult);
    }

    public PitchResult getPitchResult(User user, Long gameId) {
        //위의 메소드랑 중복
        Game game = gameRepository.findById(gameId).orElseThrow(IllegalStateException::new);
        if(!game.checkStatus().equals(PlayingStatus.IS_PLAYING.name())) {
            throw new IllegalStateException();
        }
        return pitchResultRepository.findById(pitchResultRepository.count()).orElseThrow(IllegalStateException::new);
    }

    public void unselectTeam(User user, Long gameId, Long teamId) {
        // 중복
        // 이 게임이 존재하는 게임인지 확인한다.
        Game game = gameRepository.findById(gameId).orElseThrow(IllegalStateException::new);
        // 이 게임이 해당 팀을 갖고 있는지 확인한다
        if (!game.teamExists(teamId)) {
            throw new IllegalStateException();
        }
        // game의 상태를 확인한다
        if (!game.checkStatus().equals(PlayingStatus.READY.name())) {
            throw new IllegalStateException();
        }
        //user가 teamId를 갖고 있는지 확인한다.
        Team team = teamRepository.findByUserId(user.getId()).orElseThrow(IllegalStateException::new);
        team.unselect();
        game.deleteUser(user.getId());
        teamRepository.save(team);
        gameRepository.save(game);
    }

    //이닝이 끝났다고 post 할 때
    //결과를 game score에 반영, inning end 반영

    // 게임 end라고 post 해주세용

    //게임 종료 추가
}
