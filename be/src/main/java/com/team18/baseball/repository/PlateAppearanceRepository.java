package com.team18.baseball.repository;

import com.team18.baseball.entity.PlateAppearance;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PlateAppearanceRepository extends CrudRepository<PlateAppearance, Long> {
    @Query("select * from plate_appearance where player_id = : id")
    PlateAppearance findByPlayerId(@Param("id") Long id);
}
