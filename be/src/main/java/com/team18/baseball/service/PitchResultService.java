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

    public PitchResult pitch(PitchResult pitchResult, Runner[] runners) {
        pitchResult.addPlayerInfo(runners);
        return pitchResultRepository.save(pitchResult);
    }

    public PitchResult getLastPitchResult() {
        Long count = pitchResultRepository.count();
        if (count == 0) return new PitchResult();
        PitchResult pitchResult = pitchResultRepository.findById(pitchResultRepository.count()).orElseThrow(IllegalStateException::new);
        return pitchResult;
    }
}
