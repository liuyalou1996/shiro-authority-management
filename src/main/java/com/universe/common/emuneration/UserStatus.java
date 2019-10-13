package com.universe.common.emuneration;

public enum UserStatus {
  NORMAL("0"), FORBIDDEN("1"), LOCKED("2");

  private String value;

  private UserStatus(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

}
