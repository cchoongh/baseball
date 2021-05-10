package com.team18.baseball.service;

import com.team18.baseball.dto.*;
import com.team18.baseball.entity.*;
import com.team18.baseball.repository.GameRepository;
import com.team18.baseball.repository.HalfInningRepository;
import com.team18.baseball.repository.PlateAppearanceRepository;
import com.team18.baseball.repository.TeamRepository;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GameService {
    private final GameRepository gameRepository;
    private final TeamRepository teamRepository;
    private final HalfInningRepository halfInningRepository;
    private final PlateAppearanceRepository plateAppearanceRepository;

    public GameService(GameRepository gameRepository, TeamRepository teamRepository,
                       HalfInningRepository halfInningRepository, PlateAppearanceRepository plateAppearanceRepository) {
        this.gameRepository = gameRepository;
        this.teamRepository = teamRepository;
        this.halfInningRepository = halfInningRepository;
        this.plateAppearanceRepository = plateAppearanceRepository;
    }

    public List<TeamsInGame> getTeamsInGameList() {
        return Streamable.of(gameRepository.findAll()).map((game) -> getTeamsInGame(game)).toList();
    }

    private TeamsInGame getTeamsInGame(Game game) {
        TeamSelectionData homeData = getTeamSelectionData(game, TeamType.HOME);
        TeamSelectionData awayData = getTeamSelectionData(game, TeamType.AWAY);
        return TeamsInGame.from(game.getId(), homeData, awayData);
    }

    public TeamSelectionData getTeamSelectionData(Game game, TeamType teamType) {
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

    public void start(User user, Long gameId) {
        Game game = gameRepository.findById(gameId).orElseThrow(IllegalStateException::new);
        if (!checkPlayer(user, game)) {
            throw new IllegalStateException();
        }
        if (!game.hasTwoTeams()) {
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

    }

    public boolean checkPlayer(User user, Game game) {
        //유저가 선택한 팀이 없다면 예외가 발생한다.
        Team team = teamRepository.findByUserId(user.getId()).orElseThrow(IllegalStateException::new);
        if (!game.teamExists(team.getId())) {
            return false;
        }
        return true;
    }

//    public ScoreDTO getScore() {
//        // HalfInning.getScore() -> ScoreDTO
//    }

    //player별 PlateAppearance를 가져옴
    public List<PlateAppearanceDTO> getPlayersPlateAppearance(User user, Long gameId) {
        Game game = gameRepository.findById(gameId).orElseThrow(IllegalStateException::new);
        GameHasTeam GameHasHomeTeam = game.getHomeTeamInfo();
        Long homeTeamId = GameHasHomeTeam.getTeamId();
        Team homeTeam = teamRepository.findById(homeTeamId).orElseThrow(IllegalStateException::new);
        List<Player> players = homeTeam.getPlayers();
        List<PlateAppearanceDTO> result = new ArrayList<>();
        for(Player player : players) {
            PlateAppearance playerPAs = plateAppearanceRepository.findByPlayerId(player.getId());
            PlateAppearanceDTO playerPAsDTO = PlateAppearanceDTO.from(playerPAs.getId(), playerPAs.getPlayerName(),
                    playerPAs.getAtBat(), playerPAs.getHit(), playerPAs.getOut());
            result.add(playerPAsDTO);
        }
        return result;
    }



}
