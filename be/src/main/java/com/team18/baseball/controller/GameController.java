package com.team18.baseball.controller;

import com.team18.baseball.HttpSessionUtils;
import com.team18.baseball.dto.startGameInfo.StartGameInfo;
import com.team18.baseball.dto.teamsInGame.TeamsInGame;
import com.team18.baseball.dto.pitchResult.PitchResult;
import com.team18.baseball.entity.User;
import com.team18.baseball.response.ResponseBody;
import com.team18.baseball.service.GameService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

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

    @PostMapping("/{gameId}/team/{teamId}")
    public ResponseBody<Object> selectTeam(@PathVariable Long gameId, @PathVariable Long teamId, HttpSession session) {
        User user = HttpSessionUtils.getLoginUser(session).orElseThrow(IllegalStateException::new);
        return gameService.selectTeam(user, gameId, teamId) ? ResponseBody.selectOk() : ResponseBody.selectFail();
    }

    @PutMapping("/{gameId}/team/{teamId}")
    public void unselectTeam(@PathVariable Long gameId, @PathVariable Long teamId, HttpSession session) {
        User user = HttpSessionUtils.getLoginUser(session).orElseThrow(IllegalStateException::new);
        gameService.unselectTeam(user, gameId, teamId);
    }

    @GetMapping("/{gameId}/start")
    public ResponseBody<Object> startGame(@PathVariable Long gameId, HttpSession session) {
        User user = HttpSessionUtils.getLoginUser(session).orElseThrow(IllegalStateException::new);
        Optional<StartGameInfo> startGameInfo = gameService.start(user, gameId);
        return startGameInfo.<ResponseBody<Object>>map(ResponseBody::startOk).orElseGet(ResponseBody::startFail);
    }

    @PostMapping("/{gameId}/pitch")
    public void pitch(@RequestBody PitchResult pitchResult, @PathVariable Long gameId, HttpSession session) {
        User user = HttpSessionUtils.getLoginUser(session).orElseThrow(IllegalStateException::new);
        gameService.pitch(user, gameId, pitchResult);
    }

    @GetMapping("/{gameId}/pitchResult")
    public PitchResult getPitchResult(@PathVariable Long gameId, HttpSession session) {
        HttpSessionUtils.getLoginUser(session).orElseThrow(IllegalStateException::new);
        return gameService.getPitchResult(gameId);
    }

    @GetMapping("/{gameId}/score")
    public void getScore(@PathVariable Long gameId, HttpSession session) {
        User user = HttpSessionUtils.getLoginUser(session).orElseThrow(IllegalStateException::new);
        // gameService.get
    }

    @GetMapping("/{gameId}/players")
    public void getPlayers(@PathVariable Long gameId, HttpSession session) {
        User user = HttpSessionUtils.getLoginUser(session).orElseThrow(IllegalStateException::new);
        gameService.getPlayersPlateAppearance(gameId);

    }



//    @PostMapping("/{gameId}/halfInning")
//    public void completeHalfInning(@PathVariable Long gameId, HttpSession session) {
//        User user = HttpSessionUtils.getLoginUser(session).orElseThrow(IllegalStateException::new);
//        gameService.completeHalfInning(gameId, user);
//    }


}
