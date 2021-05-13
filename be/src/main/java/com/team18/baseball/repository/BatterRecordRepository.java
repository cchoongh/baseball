package com.team18.baseball.repository;

import com.team18.baseball.entity.BatterRecord;
import org.springframework.data.repository.CrudRepository;

public interface BatterRecordRepository extends CrudRepository<BatterRecord, Long> {
}
