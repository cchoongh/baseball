package com.team18.baseball.domain;

public class User {
   private final String githubId;

    User(String githubId) {
        this.githubId = githubId;
    }

    public static User create(String githubId) {
        return new User(githubId);
    }
}
