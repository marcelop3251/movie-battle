package com.api.battle.domain.exception;

public class GameStartException extends BaseException {

  public GameStartException(String message, TypeException typeException) {
    super(message, typeException);
  }
}
