package com.team18.baseball.repository;

import com.team18.baseball.entity.battingBoard.BattingRecord;
import org.springframework.data.repository.CrudRepository;

public interface BattingRecordRepository extends CrudRepository<BattingRecord, Long> {
}
