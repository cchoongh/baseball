package com.team18.baseball.repository;

import com.team18.baseball.entity.Team;
import org.springframework.data.repository.CrudRepository;

public interface TeamRepository extends CrudRepository<Team, Long> {
}
