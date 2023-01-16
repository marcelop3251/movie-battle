package com.api.battle.domain.exception;

public class BaseException extends RuntimeException {

  private static boolean ENABLE_SUPRESSION = true;
  private static boolean WRITE_STACK_TRACE = false;
  private TypeException typeException;

  public BaseException(String message, TypeException type) {
    super(message, null, ENABLE_SUPRESSION, WRITE_STACK_TRACE);
    this.typeException = type;
  }

  public TypeException getTypeException() {
    return this.typeException;
  }
}
