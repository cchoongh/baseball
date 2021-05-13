package com.team18.baseball.service;

import com.team18.baseball.dto.batterBoard.BatterRecordDto;
import com.team18.baseball.entity.BatterRecord;
import com.team18.baseball.repository.BatterRecordRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BatterRecordService {
    private final BatterRecordRepository batterRecordRepository;

    public BatterRecordService(BatterRecordRepository batterRecordRepository) {
        this.batterRecordRepository = batterRecordRepository;
    }

    public void saveBatterRecord(List<BatterRecordDto> batterRecordBoard, Long halfInningId) {
        for ( BatterRecordDto batterRecordDto : batterRecordBoard) {
            BatterRecord batterRecord = BatterRecord.from(batterRecordDto, halfInningId);
            batterRecordRepository.save(batterRecord);
        }
    }
}
