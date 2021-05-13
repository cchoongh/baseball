package com.team18.baseball.entity;

import org.springframework.data.annotation.Id;

public class User {
    @Id
    private final Long id;
    private final String githubId;

    User(Long id, String githubId) {
        this.id = id;
        this.githubId = githubId;
    }

    public static User create(Long id, String githubId) {
        return new User(id, githubId);
    }

    public Long getId() {
        return id;
    }
}
