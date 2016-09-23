package com.echo.calculator.constant;

/**
 * Created by echo on 16-9-21.
 */

/**
 * 定义将会使用到的符号
 */
public enum Operator {

  BLANK(' '),

  PLUS('+'),

  MINUS('-'),

  TIMES('*'),

  DIVISION('/'),

  SIGN('~'),

  EQUAL('='),

  DOT('.'),

  CLEAR('c');

  public char value;

  Operator(char value) {
    this.value = value;
  }

  public char getValue() {
    return value;
  }

  public static Operator getByValue(char value) {
    for (Operator operator : Operator.values()) {
      if (operator.getValue() == value) {
        return operator;
      }
    }
    return null;
  }

}
