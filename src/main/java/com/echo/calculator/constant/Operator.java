package com.echo.calculator.constant;

/**
 * Created by echo on 16-9-21.
 */
public enum Operator {
  PLUS("+"),

  MINUS("-"),

  TIMES("*"),

  DIVISION("/"),

  SIGN("+/-"),

  EQUAL("="),

  DOT(".");

  public String value;

  Operator(String value) {
    this.value = value;
  }

  public static Operator getBy(String value) {
    return Operator.valueOf(value);
  }
}
