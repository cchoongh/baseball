package com.team18.baseball.service;

import com.team18.baseball.dto.*;
import com.team18.baseball.dto.PlateAppearanceInfoDTO;
import com.team18.baseball.entity.*;
import com.team18.baseball.dto.pitchResult.PitchResult;
import com.team18.baseball.dto.startGameInfo.GameInfo;
import com.team18.baseball.dto.startGameInfo.StartGameInfo;
import com.team18.baseball.dto.startGameInfo.TeamInfo;
import com.team18.baseball.dto.teamsInGame.TeamInGameData;
import com.team18.baseball.dto.teamsInGame.TeamsInGame;
import com.team18.baseball.entity.GameHasTeam;
import com.team18.baseball.entity.Team;
import com.team18.baseball.entity.User;
import com.team18.baseball.entity.game.*;
import com.team18.baseball.repository.*;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GameService {

    private final GameRepository gameRepository;
    private final TeamRepository teamRepository;
    private final HalfInningRepository halfInningRepository;
    private final PlateAppearanceRepository plateAppearanceRepository;
    private final PitchResultRepository pitchResultRepository;

    private final TeamService teamService;
    private final HalfInningService halfInningService;

    public GameService(GameRepository gameRepository, TeamRepository teamRepository,
                       HalfInningRepository halfInningRepository, PlateAppearanceRepository plateAppearanceRepository,
                       PitchResultRepository pitchResultRepository, TeamService teamService, HalfInningService halfInningService) {
        this.gameRepository = gameRepository;
        this.teamRepository = teamRepository;
        this.plateAppearanceRepository = plateAppearanceRepository;
        this.halfInningRepository = halfInningRepository;
        this.pitchResultRepository = pitchResultRepository;
        this.teamService = teamService;
        this.halfInningService = halfInningService;
    }

    public List<TeamsInGame> getTeamsInGameList() {
        return Streamable.of(gameRepository.findByPlayingStatus(PlayingStatus.READY.name()))
                .map(this::getTeamsInGame).toList();
    }

    private TeamsInGame getTeamsInGame(Game game) {
        TeamInGameData homeData = teamService.getTeamsInGameData(game.getHomeTeamId());
        TeamInGameData awayData = teamService.getTeamsInGameData(game.getAwayTeamId());
        return TeamsInGame.from(game.getId(), game.checkStatus(), homeData, awayData);
    }

    public boolean selectTeam(User user, Long gameId, Long teamId) {
        if(teamService.containsTeam(user)) {
            throw new IllegalStateException();
        }

        Game game = getGameAndHasStatus(gameId, PlayingStatus.READY);
        if(!game.hasTeam(teamId)) {
            throw new IllegalStateException();
        };

        Long userId = user.getId();
        if(!teamService.selectedBy(teamId, userId)) {
            return false;
        };
        addUserId(game, teamId, userId);
        return true;
    }

    private Game getGameAndHasStatus(Long gameId, PlayingStatus status) {
        Game game = gameRepository.findById(gameId).orElseThrow(IllegalStateException::new);
        if(!game.checkStatus().equals(status.name())){
            throw new IllegalStateException();
        }
        return game;
    }

    private void addUserId(Game game, Long teamId, Long userId) {
        game.addUserId(teamId, userId);
        gameRepository.save(game);
    }

    public Optional<StartGameInfo> start(User user, Long gameId) {
        Game game = getGameAndHasNotStatus(gameId, PlayingStatus.END);
        game.checkUser(user.getId()).orElseThrow(IllegalStateException::new);
        if(!game.hasTwoUsers()) {
            return Optional.empty();
        }

        //상대팀 유저도 start를 누르면 해당 정보의 초기화 정보를 볼 수 있다.
        if(game.checkStatus().equals(PlayingStatus.IS_PLAYING.name())){
            return Optional.of(getStartGameInfo(game));
        }

        game.addFirstHalfInning();
        gameRepository.save(game);
        return Optional.of(getStartGameInfo(game));
    }

    private StartGameInfo getStartGameInfo(Game game) {
        TeamInfo fieldingInfo = getTeamInfo(game, TeamType.HOME);
        TeamInfo battingInfo = getTeamInfo(game, TeamType.AWAY);
        return StartGameInfo.from( GameInfo.from(game, game.getLastHalfInning())
                , fieldingInfo, battingInfo);
    }

    private TeamInfo getTeamInfo(Game game, TeamType teamType) {
        GameHasTeam gameHasTeam = game.getGameHasTeam(teamType);
        Team team = teamService.findTeam(gameHasTeam.getTeamId());
        return TeamInfo.from(team, TeamType.HOME, gameHasTeam.getScore());
    }



    private Game getGameAndHasNotStatus(Long gameId, PlayingStatus notStatus) {
        Game game = gameRepository.findById(gameId).orElseThrow(IllegalStateException::new);
        if(game.checkStatus().equals(notStatus.name())){
            throw new IllegalStateException();
        }
        return game;
    }

    public void pitch(User user, Long gameId, PitchResult pitchResult) {
        Game game = getGameAndHasStatus(gameId, PlayingStatus.IS_PLAYING);
        TeamType teamType = game.checkUser(user.getId()).orElseThrow(IllegalStateException::new);

        halfInningService.update(game.getLastHalfInning(), pitchResult, teamType);
        pitchResultRepository.save(pitchResult);
    }

    public PitchResult getPitchResult(Long gameId) {
        Game game = getGameAndHasStatus(gameId, PlayingStatus.IS_PLAYING);
        return pitchResultRepository.findById(pitchResultRepository.count()).orElseThrow(IllegalStateException::new);
    }

    public void unselectTeam(User user, Long gameId, Long teamId) {
        Game game = getGameAndHasStatus(gameId, PlayingStatus.READY);
        if (!game.hasTeam(teamId)) {
            throw new IllegalStateException();
        }

        Long userId = user.getId();
        teamService.unselect(teamId, userId);
        game.deleteUser(userId);
        gameRepository.save(game);
    }

    public ScoreDTO getScore(Long gameId) {
        Game game = gameRepository.findById(gameId).orElseThrow(IllegalStateException::new);
        Long homeTeamId = game.getHomeTeamId();
        Long awayTeamId = game.getAwayTeamId();
        Team homeTeam = teamRepository.findById(homeTeamId).orElseThrow(IllegalStateException::new);
        Team awayTeam = teamRepository.findById(awayTeamId).orElseThrow(IllegalStateException::new);
        String homeName = homeTeam.getName();
        String awayName = awayTeam.getName();
        ScoreDTO scoreDTO = new ScoreDTO(homeName, awayName);
        scoreDTO.makeHomeScore(game);
        scoreDTO.makeAwayScore(game);
        return scoreDTO;
    }

    public PlateAppearanceDTO getPlateAppearance(Long gameId) {
        Game game = gameRepository.findById(gameId).orElseThrow(IllegalStateException::new);
        GameHasTeam gameHasHomeTeam = game.getGameHasTeam(TeamType.HOME);
        GameHasTeam gameHasAwayTeam = game.getGameHasTeam(TeamType.AWAY);
        Long homeTeamId = gameHasHomeTeam.getTeamId();
        Long awayTeamId = gameHasAwayTeam.getTeamId();
        Team homeTeam = teamRepository.findById(homeTeamId).orElseThrow(IllegalStateException::new);
        Team awayTeam = teamRepository.findById(awayTeamId).orElseThrow(IllegalStateException::new);
        String homeTeamName = homeTeam.getName();
        String awayTeamName = awayTeam.getName();
        List<Player> homePlayers = homeTeam.getPlayers();
        List<Player> awayPlayers = awayTeam.getPlayers();


        List<PlateAppearanceInfoDTO> homePAInfos = new ArrayList<>();
        List<PlateAppearanceInfoDTO> awayPAInfos = new ArrayList<>();
//        for(Player player : homePlayers) {
//            PlateAppearance homePA = plateAppearanceRepository.findByPlayerId(player.getId());
//            PlateAppearanceInfo homePAInfo = PlateAppearanceInfo.from(homePA);
//            homePAInfos.add(homePAInfo);
//        }
//        for(Player player : awayPlayers) {
//            PlateAppearance awayPA = plateAppearanceRepository.findByPlayerId(player.getId());
//            PlateAppearanceInfo awayPAInfo = PlateAppearanceInfo.from(awayPA);
//            awayPAInfos.add(awayPAInfo);
//        }
        //PlateAppearance result = PlateAppearance.create();
        return PlateAppearanceDTO.from(homePAInfos, awayPAInfos);
    }

//
//    public Optional<PitchResult> completeHalfInning(Long gameId, User user) {
//        Game game = gameRepository.findById(gameId).orElseThrow(IllegalStateException::new);
//        //user가 게임에 속하는지 확인한다.
//        TeamType teamType = game.checkUser(user.getId()).orElseThrow(IllegalStateException::new);
//        //검증로직
//        // 이닝을 추가할 수 없는 경우 : 이게임이 9회 말일 때 -> game을 종료 상태로 바꾼다
//        HalfInning lastInning = game.getLastHalfInning();
//        if((game.getHalfInnings().indexOf(lastInning) == 18)) {
//            lastInning.end();
//            game.end();
//            return
//        }
//        //gamestatus에 새 판으로 깔아줘야 하고, 기존 halfInnig 상태 바꿔줘야 하고 games에 새로운 hallfInning 넣어야 한다.
//        game.getLastHalfInning()
//
//    }

    //이닝이 끝났다고 post 할 때
    //결과를 game score에 반영, inning end 반영

    // 게임 end라고 post 해주세용

    //게임 종료 추가
}
