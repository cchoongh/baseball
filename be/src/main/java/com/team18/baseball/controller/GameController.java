package com.team18.baseball.controller;

import com.team18.baseball.HttpSessionUtils;
import com.team18.baseball.dto.ScoreDTO;
import com.team18.baseball.dto.pitchResult.PitchResult;
import com.team18.baseball.dto.startGameInfo.StartGameInfo;
import com.team18.baseball.dto.teamsInGame.TeamsInGame;
import com.team18.baseball.dto.pitchResult.PitchResultDto;
import com.team18.baseball.entity.User;
import com.team18.baseball.repository.UserRepository;
import com.team18.baseball.response.ResponseBody;
import com.team18.baseball.service.GameService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
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

    @GetMapping("/{gameId}/score")
    public ScoreDTO getScore(@PathVariable Long gameId, HttpSession session) {
        User user = HttpSessionUtils.getLoginUser(session).orElseThrow(IllegalStateException::new);
        return gameService.getScore(gameId);
    }

    @GetMapping("/{gameId}/players")
    public void getPlayers(@PathVariable Long gameId, HttpSession session) {
        User user = HttpSessionUtils.getLoginUser(session).orElseThrow(IllegalStateException::new);
        gameService.getPlateAppearance(gameId);

    }

    @PostMapping("/{gameId}/halfInning/user/{userId}")
    public ResponseBody<Object> endHalfInning(@RequestBody PitchResultDto pitchResultDto, @PathVariable Long gameId, @PathVariable Long userId) {
//        User user = HttpSessionUtils.getLoginUser(session).orElseThrow(IllegalStateException::new);
        User user = userRepository.findById(userId).orElseThrow(IllegalStateException::new);
        return gameService.endAndStartHalfInning(user, gameId, pitchResultDto) ? ResponseBody.newHalfInningOk() : ResponseBody.newHalfInningFail();
    }
}
