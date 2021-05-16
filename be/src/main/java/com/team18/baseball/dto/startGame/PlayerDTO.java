package com.team18.baseball.dto.startGame;

import com.team18.baseball.entity.Player;

public class PlayerDTO {
    private final Long id;
    private final String name;
    private final int uniformNumber;

    PlayerDTO(Long id, String name, int uniformNumber) {
        this.id = id;
        this.name = name;
        this.uniformNumber = uniformNumber;
    }

    public static PlayerDTO from(Player player) {
        return new PlayerDTO(player.getId(), player.getName(), player.getUniformNumber());
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getUniformNumber() {
        return uniformNumber;
    }
}
