package com.team18.baseball.entity;

public class InningType {
    private final InningTypeEnum inningTypeEnum;

    public InningType(InningTypeEnum inningTypeEnum) {
        this.inningTypeEnum = inningTypeEnum;
    }

    enum InningTypeEnum {
        TOP("초"),
        BOTTOM("말");

        private final String korean;

        InningTypeEnum(String korean) {
            this.korean = korean;
        }
    }
}
