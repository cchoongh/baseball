package com.team18.baseball.dto;

public class ResponseDto {
    private Status status;
    private String message;

    private ResponseDto(Status status) {
        this.status = status;
        this.message = status.getMessage();
    }

    public static ResponseDto ok() {
        return new ResponseDto(Status.OK);
    }

    public static ResponseDto selected() {
        return new ResponseDto(Status.SELECTED);
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
