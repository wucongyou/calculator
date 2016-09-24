package com.echo.calculator.constant;

/**
 * Created by echo on 16-9-23.
 */
public enum CalcStatus {

  NORMAL(1, "NORMAL"),
  POSITIVE_INFINITY(2, "POSITIVE_INFINITY"),
  NEGATIVE_INFINITY(3, "NEGATIVE_INFINITY");

  public int code;
  public String desc;

  CalcStatus(int code, String desc) {
    this.code = code;
    this.desc = desc;
  }
}
