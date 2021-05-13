package com.team18.baseball.service;

import com.team18.baseball.dto.pitchResult.PitchResult;
import com.team18.baseball.entity.game.HalfInning;
import com.team18.baseball.entity.battingBoard.BattingRecord;
import com.team18.baseball.repository.HalfInningRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HalfInningService {
    private final HalfInningRepository halfInningRepository;

    public HalfInningService(HalfInningRepository halfInningRepository) {
        this.halfInningRepository = halfInningRepository;
    }

    public void update(HalfInning lastHalfInning, PitchResult pitchResult) {
        lastHalfInning.updatePitchResult(pitchResult);
        halfInningRepository.save(lastHalfInning);
    }

    public void end(HalfInning lastHalfInning) {
        lastHalfInning.end();
        halfInningRepository.save(lastHalfInning);
    }

    public List<BattingRecord> getBattingBoard(Long id) {
        HalfInning halfInning = halfInningRepository.findById(id).orElseThrow(IllegalStateException::new);
        return halfInning.getBattingRecordBoard();
    }

    public void addBattingRecord(List<BattingRecord> battingRecords, Long halfInningId) {
        HalfInning halfInning = halfInningRepository.findById(halfInningId).orElseThrow(IllegalStateException::new);
        for(BattingRecord battingRecord : battingRecords) {
            halfInning.addBattingRecord(battingRecord);
        }
        halfInningRepository.save(halfInning);
    }
}
