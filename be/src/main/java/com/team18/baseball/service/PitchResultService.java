package com.team18.baseball.service;

import com.team18.baseball.dto.pitchResult.PitchResult;
import com.team18.baseball.dto.pitchResult.PitchResultDto;
import com.team18.baseball.dto.pitchResult.Runner;
import com.team18.baseball.entity.game.HalfInning;
import com.team18.baseball.entity.game.TeamType;
import com.team18.baseball.repository.HalfInningRepository;
import com.team18.baseball.repository.PitchResultRepository;
import org.springframework.stereotype.Service;

@Service
public class PitchResultService {
    private final PitchResultRepository pitchResultRepository;
    private final HalfInningService halfInningService;

    public PitchResultService(PitchResultRepository pitchResultRepository,
                              HalfInningService halfInningService) {
        this.pitchResultRepository = pitchResultRepository;
        this.halfInningService = halfInningService;
    }

    public void pitch(PitchResult pitchResult, Runner[] runners, HalfInning lastHalfInning) {
        pitchResult.addPlayerInfo(runners);
        pitchResultRepository.save(pitchResult);

        halfInningService.update(lastHalfInning, pitchResult);
    }

    public PitchResult getLastPitchResult() {
        return pitchResultRepository.findById(pitchResultRepository.count()).orElseThrow(IllegalStateException::new);
    }
}
