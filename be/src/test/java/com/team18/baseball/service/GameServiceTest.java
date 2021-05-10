package com.team18.baseball.service;

import com.team18.baseball.entity.*;
import com.team18.baseball.repository.GameRepository;
import com.team18.baseball.repository.HalfInningRepository;
import com.team18.baseball.repository.TeamRepository;
import com.team18.baseball.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;

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
    @Autowired
    HalfInningRepository halfInningRepository;


    @Test
    void selectTeam() {
        Long userId = 1L;
        User user = userRepository.findById(userId).orElseThrow(IllegalStateException::new);
        Long teamId = 1L;
        Team team = teamRepository.findById(teamId).orElseThrow(IllegalStateException::new);
        Long gameId = 1L;
        Game game = gameRepository.findById(gameId).orElseThrow(IllegalStateException::new);

        gameService.selectTeam(user, gameId, teamId);
        Game foundGame = gameRepository.findById(gameId).orElseThrow(IllegalStateException::new);
        assertThat(foundGame.getAwayUserId()).isEqualTo(userId);

        //게임에 해당하는 팀이 없었을 때 예외가 발생한다
        Long notExistTeam = 3L;
        assertThatThrownBy(() -> gameService.selectTeam(user, 1L, notExistTeam))
                .isInstanceOf(IllegalStateException.class);

        //다른유저가 선택한 팀을 선택했을 때 예외가 발생한다.
        Long userId2 = 2L;
        User user2 = userRepository.findById(userId2).orElseThrow(IllegalStateException::new);
        assertThatThrownBy(() -> gameService.selectTeam(user2, 1L, notExistTeam))
                .isInstanceOf(IllegalStateException.class);
    }

    @Test
    void startGame() {
        //test1 유저가 각각 팀을 선택했을 때 성공적으로 게임시작
        Long userId = 1L;
        User user = userRepository.findById(userId).orElseThrow(IllegalStateException::new);
        Long teamId = 1L;
        Team team = teamRepository.findById(teamId).orElseThrow(IllegalStateException::new);
        Long gameId = 1L;
        Game game = gameRepository.findById(gameId).orElseThrow(IllegalStateException::new);

        gameService.selectTeam(user, gameId, teamId);

        Long user2Id = 2L;
        User user2 = userRepository.findById(user2Id).orElseThrow(IllegalStateException::new);
        Long team2Id = 2L;
        Team team2 = teamRepository.findById(teamId).orElseThrow(IllegalStateException::new);

        gameService.selectTeam(user2, gameId, team2Id);

        game.teamExists(team2Id);
        gameService.start(user, gameId);

        HalfInning halfInning = halfInningRepository.findById(1L).orElseThrow(IllegalStateException::new);
        System.out.println(halfInning.toString());
        assertThat(halfInning.getInningType()).isEqualTo(InningType.TOP.toString());
    }

    @Test
    void startSoloTeam() {
        Long userId = 1L;
        User user = userRepository.findById(userId).orElseThrow(IllegalStateException::new);
        Long teamId = 1L;
        Team team = teamRepository.findById(teamId).orElseThrow(IllegalStateException::new);
        Long gameId = 1L;
        Game game = gameRepository.findById(gameId).orElseThrow(IllegalStateException::new);

        gameService.selectTeam(user, gameId, teamId);
        assertThatThrownBy(() -> gameService.start(user, gameId))
                .isInstanceOf(IllegalStateException.class);
    }


//    @Test
//    void checkPlayer() {
//        Long userId = 1L;
//        User user = userRepository.findById(userId).orElseThrow(IllegalStateException::new);
//        Long teamId = 1L;
//        Team team = teamRepository.findById(teamId).orElseThrow(IllegalStateException::new);
//        Long gameId = 1L;
//        Game game = gameRepository.findById(gameId).orElseThrow(IllegalStateException::new);
//        assertThat(gameService.selectTeam(user, gameId, team.getId())).isTrue();
//        //teamId로 게임 찾는 거 추가 예정 (지금 데이터에 teamId 1번이 gameId 1번의 away 롤인 상황)
//
//        assertThat(gameService.checkPlayer(user, game)).isTrue();
//
//        Long anotherUserId = 3L;
//        User anotherUser = userRepository.findById(anotherUserId).orElseThrow(IllegalStateException::new);
//        Long anotherTeamId = 3L;
//        Team anotherTeam = teamRepository.findById(anotherTeamId).orElseThrow(IllegalStateException::new);
//        gameService.selectTeam(anotherUser, anotherTeam.getId());
//        assertThat(gameService.checkPlayer(anotherUser, game)).isTrue();
//
//    }


}
