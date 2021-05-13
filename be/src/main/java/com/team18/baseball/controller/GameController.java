package com.team18.baseball.controller;

import com.team18.baseball.dto.ScoreDTO;
import com.team18.baseball.entity.battingBoard.BattingRecord;
import com.team18.baseball.dto.startGameInfo.StartGameInfo;
import com.team18.baseball.dto.teamsInGame.TeamsInGame;
import com.team18.baseball.dto.pitchResult.PitchResultDto;
import com.team18.baseball.entity.User;
import com.team18.baseball.repository.UserRepository;
import com.team18.baseball.response.ResponseBody;
import com.team18.baseball.service.GameService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/games")
public class GameController {

    private final GameService gameService;
    private final UserRepository userRepository;

    public GameController(GameService gameService, UserRepository userRepository) {
        this.gameService = gameService;
        this.userRepository = userRepository;
    }

    @GetMapping()
    public List<TeamsInGame> getGames() {
        return gameService.getTeamsInGameList();
    }

    @PostMapping("/{gameId}/teams/{teamId}/user/{userId}")
    public ResponseBody<Object> selectTeam(@PathVariable Long gameId, @PathVariable Long teamId, @PathVariable Long userId) {
//        User user = HttpSessionUtils.getLoginUser(session).orElseThrow(IllegalStateException::new);
        User user = userRepository.findById(userId).orElseThrow(IllegalStateException::new);
        return gameService.selectTeam(user, gameId, teamId) ? ResponseBody.selectOk() : ResponseBody.selectFail();
    }

    @PutMapping("/{gameId}/teams/{teamId}/user/{userId}")
    public void unselectTeam(@PathVariable Long gameId, @PathVariable Long teamId, @PathVariable Long userId) {
        User user = userRepository.findById(userId).orElseThrow(IllegalStateException::new);
//        User user = HttpSessionUtils.getLoginUser(session).orElseThrow(IllegalStateException::new);
        gameService.unselectTeam(user, gameId, teamId);
    }

    @GetMapping("/{gameId}/start/user/{userId}")
    public ResponseBody<Object> startGame(@PathVariable Long gameId, @PathVariable Long userId) {
        User user = userRepository.findById(userId).orElseThrow(IllegalStateException::new);
//        User user = HttpSessionUtils.getLoginUser(session).orElseThrow(IllegalStateException::new);
        Optional<StartGameInfo> startGameInfo = gameService.start(user, gameId);
        return startGameInfo.<ResponseBody<Object>>map(ResponseBody::startOk).orElseGet(ResponseBody::startFail);
    }

    @PostMapping("/{gameId}/pitch/user/{userId}")
    public void pitch(@RequestBody PitchResultDto pitchResultDto, @PathVariable Long gameId, @PathVariable Long userId) {
        User user = userRepository.findById(userId).orElseThrow(IllegalStateException::new);
//        User user = HttpSessionUtils.getLoginUser(session).orElseThrow(IllegalStateException::new);
        gameService.pitch(user, gameId, pitchResultDto);
    }

    @GetMapping("/{gameId}/pitchResult/user/{userId}")
    public PitchResultDto getPitchResult(@PathVariable Long gameId, @PathVariable Long userId) {
        User user = userRepository.findById(userId).orElseThrow(IllegalStateException::new);
//        HttpSessionUtils.getLoginUser(session).orElseThrow(IllegalStateException::new);
        return gameService.getPitchResult(gameId);
    }

    @GetMapping("/{gameId}/score/user/{userId}")
    public ScoreDTO getScore(@PathVariable Long gameId, @PathVariable Long userId) {
        User user = userRepository.findById(userId).orElseThrow(IllegalStateException::new);
//        User user = HttpSessionUtils.getLoginUser(session).orElseThrow(IllegalStateException::new);
        return gameService.getScore(user, gameId);
    }

    @GetMapping("/{gameId}/players/user/{userId}")
    public void getPlayers(@PathVariable Long gameId, @PathVariable Long userId) {
        User user = userRepository.findById(userId).orElseThrow(IllegalStateException::new);
//        User user = HttpSessionUtils.getLoginUser(session).orElseThrow(IllegalStateException::new);
        gameService.getPlateAppearance(gameId);
    }

    @PostMapping("/{gameId}/halfInning/user/{userId}")
    public ResponseBody<Object> endHalfInning(@RequestBody PitchResultDto pitchResultDto, @PathVariable Long gameId, @PathVariable Long userId) {
//        User user = HttpSessionUtils.getLoginUser(session).orElseThrow(IllegalStateException::new);
        User user = userRepository.findById(userId).orElseThrow(IllegalStateException::new);
        return gameService.endAndStartHalfInning(user, gameId, pitchResultDto) ? ResponseBody.newHalfInningOk() : ResponseBody.newHalfInningFail();
    }

    @PostMapping("/{gameId}/end/user/{userId}")
    public void endGame(@PathVariable Long gameId, @PathVariable Long userId) {
        User user = userRepository.findById(userId).orElseThrow(IllegalStateException::new);
        gameService.endGame(user, gameId);
    }

    @PostMapping("/{gameId}/batterRecord/user/{userId}")
    public void recordBatting(@RequestBody List<BattingRecord> battingRecordBoard, @PathVariable Long gameId, @PathVariable Long userId) {
        User user = userRepository.findById(userId).orElseThrow(IllegalStateException::new);
        gameService.recordBatting(user, gameId, battingRecordBoard);
    }

    @GetMapping("/{gameId}/batterBoard/user/{userId}")
    public List<BattingRecord> getBatterRecord(@PathVariable Long gameId, @PathVariable Long userId) {
        User user = userRepository.findById(userId).orElseThrow(IllegalStateException::new);
        return gameService.getBattingBoard(user, gameId);
    }
}
