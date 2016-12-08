package com.echo.calculator.constant;

/**
 * Created by echo on 16-9-21.
 */

/**
 * 定义将会使用到的符号
 */
public enum CalcOperator {

  BLANK(' '),

  PLUS('+'),

  MINUS('-'),

  TIMES('*'),

  DIVISION('/'),

  SINE("sin"),

  SIGN('~'),

  EQUAL('='),

  DOT('.'),

  CLEAR('c'),

  ONE('1'),

  TWO('2'),

  THREE('3'),

  FOUR('4'),

  FIVE('5'),

  SIX('6'),

  SEVEN('7'),

  EIGHT('8'),

  NINE('9'),

  ZERO('0');

  public String value;

  CalcOperator(char value) {
    this.value = value + "";
  }

  CalcOperator(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static CalcOperator getByValue(String value) {
    for (CalcOperator operator : CalcOperator.values()) {
      if (operator.getValue().equals(value)) {
        return operator;
      }
    }
    return null;
  }

}
