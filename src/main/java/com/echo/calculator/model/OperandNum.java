package com.echo.calculator.model;

import com.echo.calculator.constant.CalcOperator;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by echo on 16-9-21.
 */
@Data
@AllArgsConstructor
public class OperandNum {

  private String value;

  public void readChar(CalcOperator idf) {
    //当前为0，若输入字符也是0，不处理
    switch (idf) {
      case ZERO:
        if ("0".equals(value)) {
          //do nothing
          break;
        }
      case ONE:
      case TWO:
      case THREE:
      case FOUR:
      case FIVE:
      case SIX:
      case SEVEN:
      case EIGHT:
      case NINE:
        if ("0".equals(value)) {
          value = "";
        } else if ("-0".equals(value)) {
          value = "-";
        }
        value += idf.value;
        break;
      case DOT:
        if (!value.contains(".")) {
          value += idf.value;
        }
        break;
      case SIGN:
        if (!value.startsWith("-")) {
          value = "-" + value;
        }
        break;
    }
  }

  public void clear() {
    this.value = "0";
  }

}
