package com.echo.calculator.constant;

/**
 * Created by echo on 16-9-22.
 */
public enum Digit {
  ONE(1),
  TWO(2),
  THREE(3),
  FOUR(4),
  FIVE(5),
  SIX(6),
  SEVEN(7),
  EIGHT(8),
  NINE(9),
  ZERO(0);

  Digit(int value) {
    this.value = value;
  }

  public int value;
}
