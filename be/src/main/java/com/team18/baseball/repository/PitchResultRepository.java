package com.team18.baseball.repository;

import com.team18.baseball.dto.pitchResult.PitchResult;
import org.springframework.data.repository.CrudRepository;

public interface PitchResultRepository extends CrudRepository<PitchResult, Long> {
}
