package com.team18.baseball.service;

import com.team18.baseball.dto.teamsInGame.TeamInGameData;
import com.team18.baseball.entity.Team;
import com.team18.baseball.entity.User;
import com.team18.baseball.repository.TeamRepository;
import org.springframework.stereotype.Service;

@Service
public class TeamService {

    private final TeamRepository teamRepository;

    public TeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    public TeamInGameData getTeamsInGameData(Long teamId) {
        Team team = findTeam(teamId);
        return TeamInGameData.from(team);
    }

    public boolean selectedBy(Long teamId, Long userId) {
        Team team = findTeam(teamId);
        if (!team.selectedBy(userId)) {
            return false;
        }
        teamRepository.save(team);
        return true;
    }

    public Team findTeam(Long teamId) {
        return teamRepository.findById(teamId).orElseThrow(IllegalStateException::new);
    }

    public void unselect(Long homeTeamId, Long awayTeamId) {
       unselect(homeTeamId);
       unselect(awayTeamId);
    }

    private void unselect(Long teamId) {
        Team team = findTeam(teamId);
        team.unselect();
        teamRepository.save(team);
    }

    public boolean containsTeam(User user) {
        return teamRepository.findByUserId(user.getId()).isPresent();
    }
}
