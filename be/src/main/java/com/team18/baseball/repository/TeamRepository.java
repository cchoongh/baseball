package com.team18.baseball.repository;

import com.team18.baseball.entity.Team;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface TeamRepository extends CrudRepository<Team, Long> {
    Optional<Team> findByUserId(Long userId) ;
}
