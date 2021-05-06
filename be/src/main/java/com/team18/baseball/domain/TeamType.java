package com.team18.baseball.domain;

public class TeamType {

    private final TeamTypeEnum teamTypeEnum;

    TeamType(TeamTypeEnum teamTypeEnum) {
        this.teamTypeEnum = teamTypeEnum;
    }

    public static TeamType away() {
        return new TeamType(TeamTypeEnum.AWAY);
    }

    public static TeamType home() {
        return new TeamType(TeamTypeEnum.HOME);
    }

    enum TeamTypeEnum {
        AWAY("원정팀"),
        HOME("홈팀");

        private final String korean;

        TeamTypeEnum(String korean) {
            this.korean = korean;
        }
    }
}
