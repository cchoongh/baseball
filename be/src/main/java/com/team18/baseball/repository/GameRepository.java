package com.team18.baseball.repository;

import com.team18.baseball.entity.Game;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Set;

public interface GameRepository extends CrudRepository<Game, Long> {
    @Query("select g.* from game g join game_has_team ght on g.id = ght.game where ght.team_id = :id")
    Set<Game> findByTeamId(@Param("id") Long id);
}
