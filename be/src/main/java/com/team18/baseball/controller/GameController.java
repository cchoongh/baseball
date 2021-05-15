package com.team18.baseball.controller;

import com.team18.baseball.dto.PlateAppearanceDTO;
import com.team18.baseball.dto.ScoreDTO;
import com.team18.baseball.entity.battingBoard.BattingRecord;
import com.team18.baseball.dto.startGame.StartGameInfo;
import com.team18.baseball.dto.teamsSelection.TeamsInSelectionDto;
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
    public List<TeamsInSelectionDto> getGames() {
        return gameService.getTeamsInGameList();
    }

    @PostMapping("/{gameId}/teams/{teamId}/user/{userId}")
    public ResponseBody<Object> selectTeam(@PathVariable Long gameId, @PathVariable Long teamId, @PathVariable Long userId) {
//        User user = HttpSessionUtils.getLoginUser(session).orElseThrow(IllegalStateException::new);
        User user = userRepository.findById(userId).orElseThrow(IllegalStateException::new);
        return gameService.selectTeam(user, gameId, teamId) ? ResponseBody.selectOk() : ResponseBody.selectFail();
    }

    @PutMapping("/{gameId}/teams/{teamId}/user/{userId}")
    public ResponseBody<Object> unselectTeam(@PathVariable Long gameId, @PathVariable Long teamId, @PathVariable Long userId) {
        User user = userRepository.findById(userId).orElseThrow(IllegalStateException::new);
//        User user = HttpSessionUtils.getLoginUser(session).orElseThrow(IllegalStateException::new);
        gameService.unselectTeam(user, gameId, teamId);
        return ResponseBody.unselectOk();
    }

    @GetMapping("/{gameId}/start/user/{userId}")
    public ResponseBody<Object> startGame(@PathVariable Long gameId, @PathVariable Long userId) {
        User user = userRepository.findById(userId).orElseThrow(IllegalStateException::new);
//        User user = HttpSessionUtils.getLoginUser(session).orElseThrow(IllegalStateException::new);
        Optional<StartGameInfo> startGameInfo = gameService.start(user, gameId);
        return startGameInfo.<ResponseBody<Object>>map(ResponseBody::startOk).orElseGet(ResponseBody::startFail);
    }

    @PostMapping("/{gameId}/pitch/user/{userId}")
    public ResponseBody<Object> pitch(@RequestBody PitchResultDto pitchResultDto, @PathVariable Long gameId, @PathVariable Long userId) {
        User user = userRepository.findById(userId).orElseThrow(IllegalStateException::new);
//        User user = HttpSessionUtils.getLoginUser(session).orElseThrow(IllegalStateException::new);
        gameService.pitch(user, gameId, pitchResultDto);
        return ResponseBody.pitchOk();
    }

    @GetMapping("/{gameId}/pitchResult/user/{userId}")
    public PitchResultDto getPitchResult(@PathVariable Long gameId, @PathVariable Long userId) {
        User user = userRepository.findById(userId).orElseThrow(IllegalStateException::new);
//        HttpSessionUtils.getLoginUser(session).orElseThrow(IllegalStateException::new);
        return gameService.getPitchResult(user, gameId);
    }

    @PostMapping("/{gameId}/battingRecords/user/{userId}")
    public void recordBatting(@RequestBody List<BattingRecord> battingRecords, @PathVariable Long gameId, @PathVariable Long userId) {
        User user = userRepository.findById(userId).orElseThrow(IllegalStateException::new);
        gameService.recordBatting(user, gameId, battingRecords);
    }

    @GetMapping("/{gameId}/battingRecords/user/{userId}")
    public List<BattingRecord> getBattingRecords(@PathVariable Long gameId, @PathVariable Long userId) {
        User user = userRepository.findById(userId).orElseThrow(IllegalStateException::new);
        return gameService.getBattingRecords(user, gameId);
    }

    @GetMapping("/{gameId}/score/user/{userId}")
    public ScoreDTO getScore(@PathVariable Long gameId, @PathVariable Long userId) {
        User user = userRepository.findById(userId).orElseThrow(IllegalStateException::new);
//        User user = HttpSessionUtils.getLoginUser(session).orElseThrow(IllegalStateException::new);
        return gameService.getScore(user, gameId);
    }

    @GetMapping("/{gameId}/pa/user/{userId}")
    public PlateAppearanceDTO getPlateAppearance(@PathVariable Long gameId, @PathVariable Long userId) {
        User user = userRepository.findById(userId).orElseThrow(IllegalStateException::new);
//        User user = HttpSessionUtils.getLoginUser(session).orElseThrow(IllegalStateException::new);
        return gameService.getPlateAppearance(user, gameId);
    }

    @PostMapping("/{gameId}/halfInning/user/{userId}")
    public ResponseBody<Object> endHalfInning(@RequestBody PitchResultDto pitchResultDto, @PathVariable Long gameId, @PathVariable Long userId) {
//        User user = HttpSessionUtils.getLoginUser(session).orElseThrow(IllegalStateException::new);
        User user = userRepository.findById(userId).orElseThrow(IllegalStateException::new);
        return gameService.endAndStartNewHalfInning(user, gameId, pitchResultDto) ? ResponseBody.newHalfInningOk() : ResponseBody.newHalfInningFail();
    }

    @PostMapping("/{gameId}/end/user/{userId}")
    public ResponseBody<Object> endGame(@PathVariable Long gameId, @PathVariable Long userId) {
        User user = userRepository.findById(userId).orElseThrow(IllegalStateException::new);
        gameService.endGame(user, gameId);
        return ResponseBody.endOk();
    }
}
