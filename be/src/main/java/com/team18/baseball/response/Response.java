package com.team18.baseball.response;

public class Response {
    private Long gameId;
    private Status status;
    private String message;

    private Response(Status status) {
        this.status = status;
        this.message = status.getMessage();
    }

    private Response(Long gameId, Status status) {

    }

    public static Response ok() {
        return new Response(Status.OK);
    }

    public static Response selected() {
        return new Response(Status.SELECTED);
    }

    public enum Status {
        OK("팀이 선택되었습니다."),
        SELECTED("이미 선택된 팀입니다. 다른 팀을 골라 주세요.");

        private String message;

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
