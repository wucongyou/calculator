package com.echo.calculator.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created by echo on 16-9-21.
 */
@AllArgsConstructor
@Getter
public class OperandNum {
  public static final String DEFAULT_ZERO_VALUE = "0";
  private String value = DEFAULT_ZERO_VALUE;
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

  public void clear() {
    this.value = DEFAULT_ZERO_VALUE;
    this.sign = true;
  }


}
