package com.team18.baseball.service;

import com.team18.baseball.dto.pitchResultDto.PitchResult;
import com.team18.baseball.dto.pitchResultDto.Runner;
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
        return pitchResultRepository.findById(pitchResultRepository.count()).orElseThrow(IllegalStateException::new);
    }
}
