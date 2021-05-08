package com.team18.baseball.repository;

import com.team18.baseball.entity.Team;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@Transactional
class GameRepositoryTest {
    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private TeamRepository teamRepository;

    @Test
    void getGameByTeamID() {
        //데이터상 teamId 1L이 d가 1L인 game에 배치된 상황
        Long gameId = 1L;
        Long teamId = 1L;
        Team team = teamRepository.findById(1L).orElseThrow(RuntimeException::new);
        System.out.println(gameRepository.findByTeamId(team.getId()).toString());
    }
}
