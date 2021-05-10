package com.team18.baseball.dto;

import com.team18.baseball.entity.Player;

public class Pitcher {
    private final Long id;
    private final String name;
    private final int uniform_number;

    private Pitcher(Long id, String name, int uniform_number) {
        this.id = id;
        this.name = name;
        this.uniform_number = uniform_number;
    }

    public static Pitcher from(Player player) {
        return new Pitcher(player.getId(), player.getName(), player.getUniformNumber());
    }
}
