package com.team18.baseball.repository;

import com.team18.baseball.entity.game.HalfInning;
import org.springframework.data.repository.CrudRepository;

public interface HalfInningRepository extends CrudRepository<HalfInning,Long> {
}
