package com.echo.calculator.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created by echo on 16-9-21.
 */
@AllArgsConstructor
@Getter
public class OperandNum {
  private String value = "0";
  private boolean sign = true;

  public void readChar(char c) {
    //当前为0，若输入字符也是0，不处理
    if ("0".equals(value) && '0' == c) {
      return;
    }
    if (isDigit(c)) {
      value += c;
    }
    if (isDot(c)) {
      if (!value.contains(".")) {
        value += c;
      }
    }
    if (isMinus(c)) {
      sign = !sign;
    }
  }

  public static boolean isDigit(char c) {
    return c >= '0' && c <= '9';
  }

  public static boolean isDot(char c) {
    return ',' == c;
  }

  public static boolean isMinus(char c) {
    return '-' == c;
  }

}
