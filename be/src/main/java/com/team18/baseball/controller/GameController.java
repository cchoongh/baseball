package com.team18.baseball.controller;

import com.team18.baseball.dto.TeamsInGameDto;
import com.team18.baseball.service.GameService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
public class GameController {
    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping("/games")
    public List<TeamsInGameDto> getGames(HttpSession session) {
//        User user = HttpSessionUtils.getLoginUser(session).orElseThrow(IllegalStateException::new);
        return gameService.getTeamsInGameList();
    }
}
