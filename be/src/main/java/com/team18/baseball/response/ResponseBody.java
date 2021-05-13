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

    public static <T>  ResponseBody<T> newHalfInningOk() {
        return new ResponseBody<>(Status.NEW_HALFINNING_OK, Status.NEW_HALFINNING_OK.getMessage());
    }

    public static <T>  ResponseBody<T> newHalfInningFail() {
        return new ResponseBody<>(Status.NEW_HALFINNING_FAIL, Status.NEW_HALFINNING_FAIL.getMessage());
    }

    public static <T> ResponseBody<T> notFound(String message) {
        return new ResponseBody<>(Status.ERROR, message);
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
        SELECT_FAIL("다른 유저가 선택한 팀입니다. 다른 팀을 선택하세요"),
        START_OK("게임이 시작되었습니다"),
        START_FAIL("상대팀 유저를 기다리는 중입니다"),
        NEW_HALFINNING_OK("공격과 수비가 변경됩니다"),
        NEW_HALFINNING_FAIL("경기 종료버튼을 눌러주세요"),
        ERROR("예외발생");

        private final String message;

        Status(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }
    }
}
