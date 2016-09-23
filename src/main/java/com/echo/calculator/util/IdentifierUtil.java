package com.echo.calculator.util;

/**
 * Created by echo on 16-9-22.
 */
public class IdentifierUtil {

  public static boolean isDigit(char c) {
    return c >= '0' && c <= '9';
  }

  public static boolean isDot(char c) {
    return ',' == c;
  }

  public static boolean isMinus(char c) {
    return '-' == c;
  }

  public static boolean isSign(char c) {
    return c == '~';
  }
}
