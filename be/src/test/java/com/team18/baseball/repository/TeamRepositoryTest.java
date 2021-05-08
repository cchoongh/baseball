package com.team18.baseball.repository;

import com.team18.baseball.entity.Team;
import com.team18.baseball.entity.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class TeamRepositoryTest {

    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private UserRepository userRepository;

    @DisplayName("유저 아이디로 팀을 찾아온다")
    @Test
    void findTeamByUser() {
        Long userId = 1L;
        Long teamId = 1L;
        selectTeam(userId, teamId);
        Team team = teamRepository.findByUserId(userId).orElseThrow(RuntimeException::new);
        assertThat(team.getUserId()).isEqualTo(userId);
    }

    private void selectTeam(Long userId, Long teamId) {
        User user = userRepository.findById(userId).orElseThrow(RuntimeException::new);
        Team team = teamRepository.findById(teamId).orElseThrow(RuntimeException::new);
        team.selectTeam(userId);
        teamRepository.save(team);
        Team foundTeam = teamRepository.findById(teamId).orElseThrow(RuntimeException::new);
        assertThat(foundTeam.getUserId()).isEqualTo(userId);
    }

}
