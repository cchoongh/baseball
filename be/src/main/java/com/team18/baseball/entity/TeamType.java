package com.team18.baseball.entity;

public enum TeamType {
    AWAY("원정팀"),
    HOME("홈팀");

    private final String korean;

    TeamType(String korean) {
        this.korean = korean;
    }
}

//    private final TeamTypeEnum teamTypeEnum;
//
//    TeamType(TeamTypeEnum teamTypeEnum) {
//        this.teamTypeEnum = teamTypeEnum;
//    }
//
//    public static TeamType away() {
//        return new TeamType(TeamTypeEnum.AWAY);
//    }
//
//    public static TeamType home() {
//        return new TeamType(TeamTypeEnum.HOME);
//    }
//
//    public String getName() {
//        return this.teamTypeEnum.korean;
//    }
//
//    enum TeamTypeEnum {
//        AWAY("원정팀"),
//        HOME("홈팀");
//
//        private final String korean;
//
//        TeamTypeEnum(String korean) {
//            this.korean = korean;
//        }
//    }

