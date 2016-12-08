package com.echo.calculator.constant;

/**
 * Created by echo on 16-9-21.
 */
public enum CalcState {

  RESET(0),

  LEFT_OPERAND_WAIT(1),

  OPERATOR_WAIT(2),

  RIGHT_OPERAND_WAIT(3),

  SHOW(4),

  CONTINUE(5);

  public int code;

  CalcState(int code) {
    this.code = code;
  }
}
