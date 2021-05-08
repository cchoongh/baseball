package com.team18.baseball.service;

import com.team18.baseball.entity.Game;
import com.team18.baseball.entity.Team;
import com.team18.baseball.entity.User;
import com.team18.baseball.repository.GameRepository;
import com.team18.baseball.repository.TeamRepository;
import com.team18.baseball.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class GameServiceTest {
    @Autowired
    GameService gameService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    GameRepository gameRepository;
    @Autowired
    TeamRepository teamRepository;

    @Test
    void checkPlayer() {
        Long userId = 1L;
        User user = userRepository.findById(userId).orElseThrow(IllegalStateException::new);
        Long teamId = 1L;
        Team team = teamRepository.findById(teamId).orElseThrow(IllegalStateException::new);
        assertThat(gameService.selectTeam(user, team.getId())).isTrue();
        //teamId로 게임 찾는 거 추가 예정 (지금 데이터에 teamId 1번이 gameId 1번의 away 롤인 상황)
        Long gameId = 1L;
        final Game game = gameRepository.findById(gameId).orElseThrow(IllegalStateException::new);
        assertThat(gameService.checkPlayer(user, game)).isTrue();

//        Long anotherUserId = 3L;
//        User anotherUser = userRepository.findById(anotherUserId).orElseThrow(IllegalStateException::new);
//        Long anotherTeamId = 3L;
//        Team anotherTeam = teamRepository.findById(anotherTeamId).orElseThrow(IllegalStateException::new);
//        gameService.selectTeam(anotherUser, anotherTeam.getId());
//        assertThat(gameService.checkPlayer(anotherUser, game)).isTrue();

    }
}
