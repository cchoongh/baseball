package com.team18.baseball.service;

import com.team18.baseball.dto.pitchResult.PitchResult;
import com.team18.baseball.dto.pitchResult.PitchResultDto;
import com.team18.baseball.entity.game.HalfInning;
import com.team18.baseball.entity.game.TeamType;
import com.team18.baseball.repository.HalfInningRepository;
import org.springframework.stereotype.Service;

@Service
public class HalfInningService {
    private final HalfInningRepository halfInningRepository;

    public HalfInningService(HalfInningRepository halfInningRepository) {
        this.halfInningRepository = halfInningRepository;
    }

    public void update(HalfInning lastHalfInning, PitchResult pitchResult, TeamType teamType) {
        lastHalfInning.updatePitchResult(pitchResult, teamType);
        halfInningRepository.save(lastHalfInning);
    }
}
