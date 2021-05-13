package com.team18.baseball.service;

import com.team18.baseball.dto.*;
import com.team18.baseball.dto.PlateAppearanceInfoDTO;
import com.team18.baseball.entity.*;
import com.team18.baseball.TeamRoleUtils;
import com.team18.baseball.dto.pitchResult.PitchResult;
import com.team18.baseball.dto.pitchResult.PitchResultDto;
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
    private static final Integer MAX_HALF_INNING_INDEX = 18;

    private final GameRepository gameRepository;
    private final TeamRepository teamRepository;
    private final HalfInningRepository halfInningRepository;
    private final PlateAppearanceRepository plateAppearanceRepository;
    private final PitchResultRepository pitchResultRepository;

    private final TeamService teamService;
    private final PitchResultService pitchResultService;
    private final HalfInningService halfInningService;


    public GameService(GameRepository gameRepository, TeamRepository teamRepository,
                       HalfInningRepository halfInningRepository, PlateAppearanceRepository plateAppearanceRepository,
                       PitchResultRepository pitchResultRepository,
                       TeamService teamService, PitchResultService pitchResultService,
                       HalfInningService halfInningService) {
        this.gameRepository = gameRepository;
        this.teamRepository = teamRepository;
        this.plateAppearanceRepository = plateAppearanceRepository;
        this.halfInningRepository = halfInningRepository;
        this.pitchResultRepository = pitchResultRepository;
        this.teamService = teamService;
        this.pitchResultService = pitchResultService;
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
        if (teamService.containsTeam(user)) {
            throw new IllegalStateException();
        }

        Game game = getGameAndHasStatus(gameId, PlayingStatus.READY);
        if (!game.hasTeam(teamId)) {
            throw new IllegalStateException();
        }

        Long userId = user.getId();
        if (!teamService.selectedBy(teamId, userId)) {
            return false;
        }
        addUserId(game, teamId, userId);
        return true;
    }

    private Game getGameAndHasStatus(Long gameId, PlayingStatus status) {
        Game game = gameRepository.findById(gameId).orElseThrow(IllegalStateException::new);
        if (!game.checkStatus().equals(status.name())) {
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
        if (!game.hasTwoUsers()) {
            return Optional.empty();
        }

        //상대팀 유저도 start를 누르면 해당 정보의 초기화 정보를 볼 수 있다.
        if (game.checkStatus().equals(PlayingStatus.IS_PLAYING.name())) {
            return Optional.of(getStartGameInfo(game));
        }

        game.addFirstHalfInning();
        gameRepository.save(game);
        return Optional.of(getStartGameInfo(game));
    }

    private StartGameInfo getStartGameInfo(Game game) {
        TeamInfo homeTeamInfo = getTeamInfo(game, TeamType.HOME);
        TeamInfo awayTeamInfo = getTeamInfo(game, TeamType.AWAY);
        return StartGameInfo.from(GameInfo.from(game, game.getLastHalfInning())
                , homeTeamInfo, awayTeamInfo);
    }

    private TeamInfo getTeamInfo(Game game, TeamType teamType) {
        GameHasTeam gameHasTeam = game.getGameHasTeam(teamType);
        Team team = teamService.findTeam(gameHasTeam.getTeamId());
        return TeamInfo.from(team, TeamRoleUtils.checkTeamRole(teamType, game.getHalfInnings().size()), gameHasTeam.getScore());
    }

    private Game getGameAndHasNotStatus(Long gameId, PlayingStatus notStatus) {
        Game game = gameRepository.findById(gameId).orElseThrow(IllegalStateException::new);
        if (game.checkStatus().equals(notStatus.name())) {
            throw new IllegalStateException();
        }
        return game;
    }

    public void pitch(User user, Long gameId, PitchResultDto pitchResultDto) {
        PitchResult pitchResult = pitchResultService.pitch(PitchResult.from(pitchResultDto), pitchResultDto.getRunners());
        Game game = getGameAndHasStatus(gameId, PlayingStatus.IS_PLAYING);
        TeamType teamType = game.checkUser(user.getId()).orElseThrow(IllegalStateException::new);

        if (TeamRoleUtils.checkTeamRole(teamType, game.getHalfInnings().size()) == TeamRole.BATTING) {
            throw new IllegalStateException();
        }

        halfInningService.update(game.getLastHalfInning(), pitchResult);
    }

    public PitchResultDto getPitchResult(Long gameId) {
        getGameAndHasStatus(gameId, PlayingStatus.IS_PLAYING);
        return PitchResultDto.from(pitchResultService.getLastPitchResult());
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

    public ScoreDTO getScore(User user, Long gameId) {
        Game game = getGameAndHasStatus(gameId, PlayingStatus.IS_PLAYING);
        game.checkUser(user.getId()).orElseThrow(IllegalStateException::new);

        Team homeTeam = teamService.findTeam(game.getHomeTeamId());
        Team awayTeam =teamService.findTeam(game.getAwayTeamId());

        String homeName = homeTeam.getName();
        String awayName = awayTeam.getName();
        ScoreDTO scoreDTO = ScoreDTO.create(homeName, awayName);
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
        PitchResult lastPitchResult = pitchResultService.getLastPitchResult();
        //lastPitchResult.g
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


    public boolean endAndStartHalfInning(User user, Long gameId, PitchResultDto pitchResultDto) {
        Game game = getGameAndHasStatus(gameId, PlayingStatus.IS_PLAYING);
        game.checkUser(user.getId()).orElseThrow(IllegalStateException::new);


        halfInningService.end(game.getLastHalfInning());

        int lastHalfInningIndex = game.getLastHalfInningIndex();
        if ((lastHalfInningIndex == MAX_HALF_INNING_INDEX)) {
            game.end();
            gameRepository.save(game);
            //18개 이닝에 대한 총점을 score 입력 //
            return false;
        }

        game.addHalfInning();
        gameRepository.save(game);

        pitchResultService.pitch(PitchResult.from(pitchResultDto), pitchResultDto.getRunners());
        return true;
    }

    public void endGame(User user, Long gameId) {
        Game game = getGameAndHasStatus(gameId, PlayingStatus.IS_PLAYING);
        game.checkUser(user.getId()).orElseThrow(IllegalStateException::new);
        game.end();
        gameRepository.save(game);
        teamService.unselect(game.getGameHasTeam(TeamType.AWAY).getTeamId(), game.getAwayUserId());
        teamService.unselect(game.getGameHasTeam(TeamType.HOME).getTeamId(), game.getHomeUserId());
    }

//    private void recordHalfInningScore(Game game, int lastHalfInningIndex) {
//        //마지막 마지막 인덱스가 홀수 일 때 home팀 수비 away팀 공격 -> away팀 점수
//        if ((lastHalfInningIndex / 2) == 1) {
//            recordHalfInningScore(game, lastHalfInningIndex, TeamType.HOME);
//        } else {
//            recordHalfInningScore(game, lastHalfInningIndex), TeamType.AWAY);
//        }
//    }
//
//    private void recordHalfInningScore(Game game, int lastHalfInningIndex, TeamType teamType) {
//        GameHasTeam teamInfo = game.getGameHasTeam(TeamType.HOME);
//        int homeScore = game.getHalfInnings().get(lastHalfInningIndex).getScore();
//        teamInfo.recordScore(homeScore);
//    }


}
