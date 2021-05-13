package com.team18.baseball.exception;

public class UserNotFoundException extends NotFoundException {
    public UserNotFoundException() {
        super("해당하는 유저가 없습니다");
    }
}
