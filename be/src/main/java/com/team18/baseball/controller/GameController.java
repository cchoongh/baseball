package com.team18.baseball.controller;

import com.team18.baseball.HttpSessionUtils;
import com.team18.baseball.dto.StartGameInfo;
import com.team18.baseball.dto.TeamsInGame;
import com.team18.baseball.dto.request.PitchResult;
import com.team18.baseball.entity.HalfInning;
import com.team18.baseball.entity.User;
import com.team18.baseball.response.ResponseBody;
import com.team18.baseball.service.GameService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/games")
public class GameController {
    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping()
    public List<TeamsInGame> getGames() {
        return gameService.getTeamsInGameList();
    }

    @PostMapping("/test")
    public PitchResult test(@RequestBody PitchResult pitchResult) {
        return pitchResult;
    }

    @PostMapping("/{gameId}/team/{teamId}")
    public ResponseBody selectTeam(@PathVariable Long gameId, @PathVariable Long teamId, HttpSession session) {
        User user = HttpSessionUtils.getLoginUser(session).orElseThrow(IllegalStateException::new);
        return gameService.selectTeam(user, gameId, teamId) ? ResponseBody.selectOk() : ResponseBody.selectFail();
    }

    @GetMapping("/{gameId}/start")
    public StartGameInfo startGame(@PathVariable Long gameId, HttpSession session) {
        User user = HttpSessionUtils.getLoginUser(session).orElseThrow(IllegalStateException::new);
        return gameService.start(user, gameId);
    }

    @PostMapping("/{gameId}/pitch")
    public HalfInning pitch(@RequestBody PitchResult pitchResult, @PathVariable Long gameId, HttpSession session) {
        User user = HttpSessionUtils.getLoginUser(session).orElseThrow(IllegalStateException::new);
        return gameService.pitch(user, gameId, pitchResult);
    }

    @GetMapping("/{gameId}/pitchResult")
    public PitchResult getPitchResult(@PathVariable Long gameId, HttpSession session) {
        User user = HttpSessionUtils.getLoginUser(session).orElseThrow(IllegalStateException::new);
        return gameService.getPitchResult(user, gameId);
    }
}