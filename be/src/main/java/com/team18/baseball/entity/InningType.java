package com.team18.baseball.entity;

public enum InningType {
  TOP("초"),
  BOTTOM("말");

  private final String korean;

  InningType(String korean) {
            this.korean = korean;
        }
}
