package com.team18.baseball.repository;

import com.team18.baseball.entity.Game;
import org.springframework.data.repository.CrudRepository;

public interface GameRepository extends CrudRepository<Game, Long> {
}
