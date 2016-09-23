package com.echo.calculator.constant;

/**
 * Created by echo on 16-9-23.
 */
public enum CalcStatus {

  NORMAL(1),
  POSITIVE_INFINITY(2),
  NEGATIVE_INFINITY(3);
  public int code;

  CalcStatus(int code) {
    this.code = code;
  }
}
