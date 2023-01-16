package com.api.battle.domain.entity;

public record ControlQuiz(
  Long id, Long userId, Status status
) {

  public boolean isStarted() {
    return status.equals(Status.STARTED);
  }

  public boolean isDisqualified() {
    return status.equals(Status.DISQUALIFIED);
  }

}
