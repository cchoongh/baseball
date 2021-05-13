package com.team18.baseball.repository;

import com.team18.baseball.entity.game.Game;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;
import java.util.List;

public interface GameRepository extends CrudRepository<Game, Long> {
    List<Game> findByPlayingStatus(String playingStatus);
}
