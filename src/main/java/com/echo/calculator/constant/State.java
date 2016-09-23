package com.echo.calculator.constant;

import lombok.Data;

/**
 * Created by echo on 16-9-21.
 */
public enum State {

  RESET(0),
  LEFT_OPERAND_WAIT(1),
  OPERATOR_WAIT(2),
  RIGHT_OPERAND_WAIT(3),
  SHOW(4),
  CONTINUE(5);

  public int code;

  State(int code) {
    this.code = code;
  }
}
