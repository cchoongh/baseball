package com.team18.baseball.dto;

public class TeamDto {
    private final Long id;
    private final String name;

    protected TeamDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
