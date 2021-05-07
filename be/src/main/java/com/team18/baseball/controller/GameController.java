package com.team18.baseball.controller;

import com.team18.baseball.dto.ResponseDto;
import com.team18.baseball.dto.TeamsInGameDto;
import com.team18.baseball.repository.TeamRepository;
import com.team18.baseball.repository.UserRepository;
import com.team18.baseball.service.GameService;
import com.team18.baseball.entity.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@RestController

public class GameController {
    private final GameService gameService;
    private final UserRepository userRepository;
    private final TeamRepository teamRepository;

    public GameController(GameService gameService, UserRepository userRepository, TeamRepository teamRepository) {
        this.gameService = gameService;
        this.userRepository = userRepository;
        this.teamRepository = teamRepository;
    }

    @GetMapping("/games")
    public List<TeamsInGameDto> getGames(HttpSession session) {
//        User user = HttpSessionUtils.getLoginUser(session).orElseThrow(IllegalStateException::new);
        return gameService.getTeamsInGameList();
    }

    @GetMapping("/test")
    public Long test() {
        return teamRepository.findById(2L).orElseThrow(IllegalStateException::new).getUserId();
    }

    @PostMapping ("/games/{gameId}/team/{teamId}")
    public ResponseDto selectTeam(@PathVariable Long gameId, @PathVariable Long teamId, HttpSession session) {
//        User user = HttpSessionUtils.getLoginUser(session).orElseThrow(IllegalStateException::new);
        User user = userRepository.findById(1L).orElseThrow(IllegalStateException::new);
        return gameService.selectTeam(user, gameId, teamId) ? ResponseDto.ok() : ResponseDto.selected();
    }
}
