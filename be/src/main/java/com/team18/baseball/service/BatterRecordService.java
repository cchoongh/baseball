package com.team18.baseball.service;

import com.team18.baseball.repository.BattingRecordRepository;
import com.team18.baseball.repository.HalfInningRepository;
import org.springframework.stereotype.Service;

@Service
public class BatterRecordService {
    private final BattingRecordRepository battingRecordRepository;

    public BatterRecordService(BattingRecordRepository battingRecordRepository, HalfInningRepository halfInningRepository) {
        this.battingRecordRepository = battingRecordRepository;
    }
}
