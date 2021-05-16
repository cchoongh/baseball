package com.team18.baseball.service;

import com.team18.baseball.TeamRoleUtils;
import com.team18.baseball.dto.PlateAppearanceDTO;
import com.team18.baseball.dto.PlateAppearanceInfoDTO;
import com.team18.baseball.dto.PlayersDTO;
import com.team18.baseball.dto.ScoreBoardDTO;
import com.team18.baseball.dto.pitchResult.PitchResult;
import com.team18.baseball.dto.pitchResult.PitchResultDTO;
import com.team18.baseball.dto.startGame.GameDTO;
import com.team18.baseball.dto.startGame.StartGameInfo;
import com.team18.baseball.dto.startGame.TeamForStartDTO;
import com.team18.baseball.dto.teamsSelection.TeamInSelectionDTO;
import com.team18.baseball.dto.teamsSelection.TeamsInSelectionDTO;
import com.team18.baseball.entity.GameHasTeam;
import com.team18.baseball.entity.Player;
import com.team18.baseball.entity.Team;
import com.team18.baseball.entity.User;
import com.team18.baseball.entity.battingBoard.BattingRecord;
import com.team18.baseball.entity.game.*;
import com.team18.baseball.repository.GameRepository;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class GameService {
    private static final Integer MAX_HALF_INNING_INDEX = 18;
    private static final Integer PLAYERS_NUM = 9;

    private final GameRepository gameRepository;

    private final TeamService teamService;
    private final PitchResultService pitchResultService;
    private final HalfInningService halfInningService;


    public GameService(GameRepository gameRepository,
                       TeamService teamService,
                       PitchResultService pitchResultService,
                       HalfInningService halfInningService) {
        this.gameRepository = gameRepository;
        this.teamService = teamService;
        this.pitchResultService = pitchResultService;
        this.halfInningService = halfInningService;
    }

    public List<TeamsInSelectionDTO> getTeamsInGameList() {
        gameRepository.findByPlayingStatus(PlayingStatus.READY.name());
        return Streamable.of(gameRepository.findByPlayingStatus(PlayingStatus.READY.name()))
                .map(game -> TeamsInSelectionDTO.from(game.getId(),
                        game.checkStatus(),
                        TeamInSelectionDTO.from(getTeam(game, TeamType.HOME)),
                        TeamInSelectionDTO.from(getTeam(game, TeamType.AWAY))))
                .toList();
    }

    public boolean selectTeam(User user, Long gameId, Long teamId) {
        if (teamService.containsTeam(user)) {
            throw new IllegalStateException();
        }
        Game game = getGameAndHasStatus(gameId, PlayingStatus.READY);
        if (!hasTeam(game, teamId)) {
            throw new IllegalStateException();
        }

        Long userId = user.getId();
        if (!teamService.selectedBy(teamId, userId)) {
            return false;
        }
        addUserId(game, teamId, userId);
        return true;
    }

    private void addUserId(Game game, Long teamId, Long userId) {
        game.addUserId(checkTeamType(game, teamId).orElseThrow(IllegalStateException::new), userId);
        gameRepository.save(game);
    }

    private Optional<String> checkTeamType(Game game, Long teamId) {
        return getTeamIds(game).entrySet()
                .stream()
                .filter(e -> Objects.equals(e.getValue(), teamId))
                .map(Map.Entry::getKey)
                .findFirst();
    }

    public void unselectTeam(User user, Long gameId, Long teamId) {
        Game game = getGameAndHasStatus(gameId, PlayingStatus.READY);
        Long userId = user.getId();
        game.checkUser(userId).orElseThrow(IllegalStateException::new);
        if (!hasTeam(game, teamId)) {
            throw new IllegalStateException();
        }

        teamService.unselect(teamId, userId);
        game.deleteUser(userId);
        gameRepository.save(game);
    }

    public Optional<StartGameInfo> start(User user, Long gameId) {
        Game game = getGameAndHasNotStatus(gameId, PlayingStatus.END);
        game.checkUser(user.getId()).orElseThrow(IllegalStateException::new);
        if (!game.hasTwoUsers()) {
            return Optional.empty();
        }

        if (game.checkStatus().equals(PlayingStatus.IS_PLAYING.name())) {
            return Optional.of(getStartGameInfo(game));
        }

        game.addFirstHalfInning();
        gameRepository.save(game);
        return Optional.of(getStartGameInfo(game));
    }

    private Game getGameAndHasNotStatus(Long gameId, PlayingStatus notStatus) {
        Game game = gameRepository.findById(gameId).orElseThrow(IllegalStateException::new);
        if (game.checkStatus().equals(notStatus.name())) {
            throw new IllegalStateException();
        }
        return game;
    }

    private StartGameInfo getStartGameInfo(Game game) {
        return StartGameInfo.from(GameDTO.from(game, game.getLastHalfInning()),
                getTeamInfo(game, TeamType.HOME), getTeamInfo(game, TeamType.AWAY));
    }

    private TeamForStartDTO getTeamInfo(Game game, TeamType teamType) {
        List<HalfInning> halfInnings = game.getHalfInnings();
        GameHasTeam gameHasTeam = game.getGameHasTeam(teamType);
        return TeamForStartDTO.from(getTeam(game, teamType),
                TeamRoleUtils.checkTeamRole(teamType, halfInnings.size()), gameHasTeam.getScore());
    }

    public void pitch(User user, Long gameId, PitchResultDTO pitchResultDto) {
        Game game = getGameAndHasStatus(gameId, PlayingStatus.IS_PLAYING);
        TeamType teamType = game.checkUser(user.getId()).orElseThrow(IllegalStateException::new);

        if (TeamRoleUtils.checkTeamRole(teamType, game.getHalfInnings().size()) == TeamRole.BATTING) {
            throw new IllegalStateException();
        }

        halfInningService.pitch(game.getLastHalfInning(), PitchResult.from(pitchResultDto));
        gameRepository.save(game);
    }
    public Optional<PitchResultDTO> getPitchResult(User user, Long gameId) {
        Game game = getGameAndHasStatus(gameId, PlayingStatus.IS_PLAYING);
        game.checkUser(user.getId()).orElseThrow(IllegalStateException::new);

        final Optional<PitchResult> lastPitchResult = halfInningService.getLastPitchResult(game.getLastHalfInning());
        if(lastPitchResult.isPresent()) {
            return Optional.of(PitchResultDTO.from(lastPitchResult.get()));
        };
        return Optional.empty();
    }

    public void recordBatting(User user, Long gameId, List<BattingRecord> battingRecords) {
        Game game = getGameAndHasStatus(gameId, PlayingStatus.IS_PLAYING);
        TeamType teamType = game.checkUser(user.getId()).orElseThrow(IllegalStateException::new);

        if (TeamRoleUtils.checkTeamRole(teamType, game.getHalfInnings().size()) == TeamRole.BATTING) {
            throw new IllegalStateException();
        }

        halfInningService.addBattingRecord(game.getLastHalfInning(), battingRecords);
    }

    public List<BattingRecord> getBattingRecords(User user, Long gameId) {
        Game game = getGameAndHasStatus(gameId, PlayingStatus.IS_PLAYING);
        game.checkUser(user.getId()).orElseThrow(IllegalStateException::new);

        return halfInningService.getBattingRecords(game.getLastHalfInning());
    }

    public ScoreBoardDTO getScore(User user, Long gameId) {
        Game game = getGameAndHasStatus(gameId, PlayingStatus.IS_PLAYING);
        game.checkUser(user.getId()).orElseThrow(IllegalStateException::new);

        GameHasTeam gameHasHomeTeam = game.getGameHasTeam(TeamType.HOME);
        GameHasTeam gameHasAwayTeam = game.getGameHasTeam(TeamType.AWAY);

        Team homeTeam = teamService.findTeam(gameHasHomeTeam.getId());
        Team awayTeam = teamService.findTeam(gameHasAwayTeam.getId());

        String homeName = homeTeam.getName();
        String awayName = awayTeam.getName();
        ScoreBoardDTO scoreBoardDTO = ScoreBoardDTO.create(homeName, awayName);
        scoreBoardDTO.makeHomeScore(game);
        scoreBoardDTO.makeAwayScore(game);
        return scoreBoardDTO;
    }

    public PlateAppearanceDTO getPlateAppearance(User user, Long gameId) {
        Game game = getGameAndHasStatus(gameId, PlayingStatus.IS_PLAYING);
        game.checkUser(user.getId()).orElseThrow(IllegalStateException::new);

        Team homeTeam = getTeam(game, TeamType.HOME);
        Team awayTeam = getTeam(game, TeamType.AWAY);
        String homeTeamName = homeTeam.getName();
        String awayTeamName = awayTeam.getName();
        List<Player> homePlayers = homeTeam.getPlayers();
        List<Player> awayPlayers = awayTeam.getPlayers();
        Optional<PitchResult> lastPitchResult = pitchResultService.getLastPitchResult();
        if (!lastPitchResult.isPresent()) { // pitch 안 한 상태
            List<PlayersDTO> homePlayersDTO = makeNullPlayersDTO(homePlayers);
            List<PlayersDTO> awayPlayersDTO = makeNullPlayersDTO(awayPlayers);
            PlateAppearanceInfoDTO homePAInfos = PlateAppearanceInfoDTO.createNullPAInfo(homeTeamName, homePlayersDTO);
            PlateAppearanceInfoDTO awayPAInfos = PlateAppearanceInfoDTO.createNullPAInfo(awayTeamName, awayPlayersDTO);
            return PlateAppearanceDTO.createNullPA(awayTeamName, homeTeamName, awayPAInfos, homePAInfos);
        }
        List<PlayersDTO> homePlayersDTO = makePlayersDTO(homePlayers, lastPitchResult.get());
        List<PlayersDTO> awayPlayersDTO = makePlayersDTO(awayPlayers, lastPitchResult.get());
        PlateAppearanceInfoDTO homePAInfos = PlateAppearanceInfoDTO.create(homeTeamName, homePlayersDTO);
        PlateAppearanceInfoDTO awayPAInfos = PlateAppearanceInfoDTO.create(awayTeamName, awayPlayersDTO);
        return PlateAppearanceDTO.create(awayPAInfos, homePAInfos);
    }

    private List<PlayersDTO> makePlayersDTO(List<Player> players, PitchResult lastPitchResult) {
        List<PlayersDTO> playersDTOs = new ArrayList<>();
        for (int i = 0; i < PLAYERS_NUM; i++) {
            Player player = players.get(i);
            Long playerId = player.getId();
            String playerName = player.getName();
            int playerAtBat = 0;
            int PlayerHit = 0;
            int PlayerOut = 0;
            if (lastPitchResult.getBatter().getPlayerName().equals(playerName)) {
                playerAtBat++;
            }
            if (lastPitchResult.getBatter().getPlayerName().equals(playerName)
                    && lastPitchResult.getPitchResult().equals(BattingResult.BALL.name())) {
                PlayerHit++;
            }
            if (lastPitchResult.getBatter().isOut()) {
                PlayerOut++;
            }
            int homePlayerAverage = (PlayerHit + playerAtBat) / 2;
            PlayersDTO playersDTO = PlayersDTO.create(playerId, playerName, playerAtBat, PlayerHit, PlayerOut, homePlayerAverage);
            playersDTOs.add(playersDTO);
        }
        return playersDTOs;
    }

    private List<PlayersDTO> makeNullPlayersDTO(List<Player> players) {
        List<PlayersDTO> playersDTOs = new ArrayList<>();
        for (int i = 0; i < PLAYERS_NUM; i++) {
            Player player = players.get(i);
            Long playerId = player.getId();
            String playerName = player.getName();
            int playerAtBat = 0;
            int PlayerHit = 0;
            int PlayerOut = 0;
            int homePlayerAverage = 0;
            PlayersDTO playersDTO = PlayersDTO.create(playerId, playerName, playerAtBat, PlayerHit, PlayerOut, homePlayerAverage);
            playersDTOs.add(playersDTO);
        }
        return playersDTOs;
    }

    public boolean endAndStartNewHalfInning(User user, Long gameId, PitchResultDTO pitchResultDto) {
        Game game = getGameAndHasStatus(gameId, PlayingStatus.IS_PLAYING);
        game.checkUser(user.getId()).orElseThrow(IllegalStateException::new);

        halfInningService.end(game.getLastHalfInning());

        if ( game.getLastHalfInningIndex() == MAX_HALF_INNING_INDEX) {
            return false;
        }

        game.addHalfInning();
        gameRepository.save(game);
        halfInningService.pitch(game.getLastHalfInning(), PitchResult.from(pitchResultDto));

        return true;
    }

    public void endGame(User user, Long gameId) {
        Game game = getGameAndHasStatus(gameId, PlayingStatus.IS_PLAYING);
        game.checkUser(user.getId()).orElseThrow(IllegalStateException::new);

        game.getLastHalfInning().end();
        game.end();
        addTotalScore(game);
        gameRepository.save(game);

        teamService.unselect(getTeamId(game, TeamType.HOME), getTeamId(game, TeamType.AWAY));
    }

    private void addTotalScore(Game game) {
        int homeScore = 0;
        int awayScore = 0;
        for (HalfInning halfInning : game.getHalfInnings()) {
            if (halfInning.getInningType().equals(InningType.TOP.name())) {
                homeScore += halfInning.getScore();
            } else {
                awayScore += halfInning.getScore();
            }
        }

        game.getGameHasTeam(TeamType.HOME).addScore(homeScore);
        game.getGameHasTeam(TeamType.AWAY).addScore(awayScore);
    }

    private Team getTeam(Game game, TeamType teamType) {
        return teamService.findTeam(getTeamId(game, teamType));
    }

    private Long getTeamId(Game game, TeamType teamType) {
        return game.getGameHasTeam(teamType).getTeamId();
    }

    private boolean hasTeam(Game game, Long teamId) {
        return getTeamIds(game).containsValue(teamId);
    }

    private Map<String, Long> getTeamIds(Game game) {
        return game.getTeams().entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey,
                        e -> e.getValue().getTeamId()));
    }

    private Game getGameAndHasStatus(Long gameId, PlayingStatus playingStatus) {
        Game game = gameRepository.findById(gameId).orElseThrow(IllegalStateException::new);
        if (!game.checkStatus().equals(playingStatus.name())) {
            throw new IllegalStateException();
        }
        return game;
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
