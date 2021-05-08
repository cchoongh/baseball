package com.team18.baseball.controller;

import com.team18.baseball.HttpSessionUtils;
import com.team18.baseball.dto.ResponseDto;
import com.team18.baseball.dto.TeamsInGameDto;
import com.team18.baseball.entity.User;
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
    public List<TeamsInGameDto> getGames() {
        return gameService.getTeamsInGameList();
    }

//    @GetMapping("/test")
//    public Long test() {
//        return teamRepository.findById(2L).orElseThrow(IllegalStateException::new).getUserId();
//    }

    @PostMapping("/team/{teamId}")
    public ResponseDto selectTeam(@PathVariable Long teamId, HttpSession session) {
        User user = HttpSessionUtils.getLoginUser(session).orElseThrow(IllegalStateException::new);
        return gameService.selectTeam(user, teamId) ? ResponseDto.ok() : ResponseDto.selected();
    }

    //이닝을 만들고
    @GetMapping("/{gameId}/start")
    public void startGame(@PathVariable Long gameId, HttpSession session) {
        User user = HttpSessionUtils.getLoginUser(session).orElseThrow(IllegalStateException::new);
        gameService.start(user, gameId);

    }

}
