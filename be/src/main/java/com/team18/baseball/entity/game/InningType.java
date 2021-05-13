package com.team18.baseball.entity.game;

public enum InningType {
  TOP("초"),
  BOTTOM("말");

  private final String korean;

  InningType(String korean) {
            this.korean = korean;
        }
}
