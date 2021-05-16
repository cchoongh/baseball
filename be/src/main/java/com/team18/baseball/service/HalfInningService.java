package com.team18.baseball.service;

import com.team18.baseball.dto.pitchResult.PitchResult;
import com.team18.baseball.entity.game.HalfInning;
import com.team18.baseball.entity.battingBoard.BattingRecord;
import com.team18.baseball.entity.game.PlayingStatus;
import com.team18.baseball.repository.HalfInningRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HalfInningService {
    private final HalfInningRepository halfInningRepository;

    public HalfInningService(HalfInningRepository halfInningRepository) {
        this.halfInningRepository = halfInningRepository;
    }

    public void pitch(HalfInning halfInning, PitchResult pitchResult) {
        halfInning.addPitchResult(pitchResult);
    }

    public Optional<PitchResult> getLastPitchResult(HalfInning halfInning) {
        List<PitchResult> pitchesResult = halfInning.getPitchesResult();
        int pitchesResultSize = pitchesResult.size();
        if (pitchesResultSize == 0) {
           return Optional.empty();
        }
        return Optional.of(pitchesResult.get(pitchesResultSize - 1));
    }

    public void addBattingRecord(HalfInning halfInning, List<BattingRecord> battingRecords) {
        for(BattingRecord battingRecord : battingRecords) {
            halfInning.addBattingRecord(battingRecord);
        }
        halfInningRepository.save(halfInning);
    }

    public List<BattingRecord> getBattingRecords(HalfInning halfInning) {
        return halfInning.getBattingRecords();
    }

    public void end(HalfInning lastHalfInning) {
        lastHalfInning.end();
        halfInningRepository.save(lastHalfInning);
    }
}
