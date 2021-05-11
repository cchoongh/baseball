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

    public static <T>  ResponseBody<T> startFail() {
        return new ResponseBody<>(Status.START_FAIL, Status.START_FAIL.getMessage());
    }

    public static <T>  ResponseBody<T> startOk(T body) {
        return new ResponseBody<>(Status.START_OK, Status.START_OK.getMessage(), body);
    }

    public Status getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public T getBody() {
        return body;
    }

    enum Status {
        SELECT_OK("팀이 선택되었습니다."),
        SELECT_FAIL(""),
        START_OK("게임이 시작됩니다"),
        START_FAIL("상대팀 유저를 기다리는 중입니다");


        private final String message;

        Status(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }
    }
}
