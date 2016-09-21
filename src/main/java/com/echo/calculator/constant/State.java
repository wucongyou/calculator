package com.echo.calculator.constant;

import lombok.Data;

/**
 * Created by echo on 16-9-21.
 */
public enum State {

  RESET(0),
  LEFT_OPERAND(1),
  OPERATOR(2),
  RIGHT_OPERAND(3),
  SHOW(4),
  CONTINUE(5);

  public int code;

  State(int code) {
    this.code = code;
  }
}
