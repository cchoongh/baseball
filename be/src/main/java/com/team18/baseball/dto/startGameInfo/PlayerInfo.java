package com.team18.baseball.dto.startGameInfo;

import com.team18.baseball.entity.Player;

public class PlayerInfo {
    private final Long id;
    private final String name;
    private final int uniformNumber;

    PlayerInfo(Long id, String name, int uniformNumber) {
        this.id = id;
        this.name = name;
        this.uniformNumber = uniformNumber;
    }

    public static PlayerInfo from(Player player) {
        return new PlayerInfo(player.getId(), player.getName(), player.getUniformNumber());
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
