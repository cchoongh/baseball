package com.team18.baseball.response;

public class ResponseBody<T> {
    private final Status status;
    private final String message;
    private final T body;

    private ResponseBody(Status status, String message, T body) {
        this.status = status;
        this.message = message;
        this.body = body;
    }

    private ResponseBody(Status status, String message) {
        this.status = status;
        this.message = message;
        this.body = null;
    }

    public static <T> ResponseBody<T> selectOk() {
        return new ResponseBody<>(Status.SELECT_OK, Status.SELECT_OK.getMessage());
    }

    public static <T>  ResponseBody<T> selectFail() {
        return new ResponseBody<>(Status.SELECT_FAIL, Status.SELECT_FAIL.getMessage());
    }

    enum Status {
        SELECT_OK("팀이 선택되었습니다."),
        SELECT_FAIL("이미 선택된 팀입니다. 다른 팀을 골라 주세요.");
        START_OK("게임이 시작"

        private final String message;

        Status(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }
    }

    public Status getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
