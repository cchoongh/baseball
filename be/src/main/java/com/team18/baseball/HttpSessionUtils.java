package com.team18.baseball;

import com.team18.baseball.entity.User;

import javax.servlet.http.HttpSession;
import java.util.Optional;

public class HttpSessionUtils {
    public static final String USER_SESSION_KEY = "sessionedUser";

    public static Optional<User> getLoginUser(HttpSession session) {
        return Optional.ofNullable((User) session.getAttribute(USER_SESSION_KEY));
    }
}
