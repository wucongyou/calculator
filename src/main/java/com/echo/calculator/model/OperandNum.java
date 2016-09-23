package com.echo.calculator.model;

import com.echo.calculator.constant.Identifier;
import com.echo.calculator.util.IdentifierUtil;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

/**
 * Created by echo on 16-9-21.
 */
@AllArgsConstructor
@Getter
@Data
public class OperandNum {
  public static final OperandNum ZERO = new OperandNum("0");
  private String value;

  public void readChar(char c) {
    //当前为0，若输入字符也是0，不处理
    if ("0".equals(value) && '0' == c) {
      return;
    }
    if (IdentifierUtil.isDigit(c)) {
      if ("0".equals(value)) {
        value = "";
      }
      value += c;
    }
    if (IdentifierUtil.isDot(c)) {
      if (!value.contains(".")) {
        value += c;
      }
    }
    if (IdentifierUtil.isSign(c)) {

      if (!value.startsWith("-")) {
        value = "-" + value;
      }
    }
  }

  public void clear() {
    this.value = "0";
  }

  public void setValue(String value) {
    if (value.startsWith("-")) {
      this.value = value.substring(1);
      return;
    }
    this.value = value;
  }
}
