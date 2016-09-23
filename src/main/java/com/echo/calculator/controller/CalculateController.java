package com.echo.calculator.controller;

import com.echo.calculator.constant.Identifier;
import com.echo.calculator.constant.Operator;
import com.echo.calculator.constant.State;
import com.echo.calculator.model.OperandNum;
import com.echo.calculator.util.IdentifierUtil;

/**
 * Created by echo on 16-9-21.
 */
public class CalculateController {
  private String result = "0";
  private char symbol_use = ' ';    // 最近一次运算被选中的符号，默认为空
  private State c_state = State.st1;   // 初始状态直接从 st1 开始好了

  private OperandNum op_left = new OperandNum(),  // 定义左、右操作数
      op_right = new OperandNum();

  // 在屏幕上显示的内容，依据当前的不同状态来定。
  public String getExpression() {
    switch (c_state) {
      case RESET:
      case LEFT_OPERAND:
        return op_left.getValue();
      case OPERATOR:
        return op_left.getValue() + symbol_use;
      case RIGHT_OPERAND:
        return op_left.getValue() + symbol_use + op_right.getValue();
      case SHOW:
        return Operator.EQUAL.value + result;
      default:
        return "0";
    }
  }

  /**
   * 重置
   */
  private void reset() {
    op_left.clear();
    op_right.clear();
    result = "0";
    c_state = State.LEFT_OPERAND;
  }

  private void fun_st1_basic(char c) {
    if (IdentifierUtil.isDigit(c) || IdentifierUtil.isDot(c) || IdentifierUtil.isSign(c)) {
      op_left.readChar(c);
      c_state = State.LEFT_OPERAND;
    } else if (c == operator) {
      // result =
      symbol_use = '=';
      fun_result();
      c_state = STATE.st4;
    } else if (c == 'c') { // 按下C键
      reset();
      c_state = STATE.st0;
    } else { // 输入运算符号
      fun_st2(c);
      c_state = STATE.st2;
    }
  }

  private void fun_st2(char key) {
    if (key == '+' || key == '-' || key == '*' || key == '/') {
      symbol_use = key;
      c_state = STATE.st2;
    } else if ((key >= '0' && key <= '9') || key == '.' || key == '~') {
      fun_st3(key);
      c_state = STATE.st3;
    }
  }

  private void fun_st3(char key) {
    if ((key >= '0' && key <= '9') || key == '.' || key == '~') {
      op_right.pushDigital(key);
      c_state = STATE.st3;
    } else if (key == '=') {
      // result =
      fun_result();
      c_state = STATE.st4;
    } else if (key == 'c') { // 按下C键
      reset();
      c_state = STATE.st0;
    } else {
      c_state = STATE.st5; // 按下运算符号
      fun_st5(key);
    }
  }

  void fun_st4(char key) { // 此时，已经显示出了结果
    if ((key >= '0' && key <= '9') || key == '.' || key == '~') {
      reset();
      op_left.pushDigital(key);
      c_state = STATE.st1;
    } else if (key == '+' || key == '-' || key == '*' || key == '/') { // 输入运算符号
      op_left.setValue(result);
      op_right.setClear();
      fun_st2(key);
      c_state = STATE.st2;
    }
  }

  void fun_st5(char key) {
    fun_result();
    op_left.setValue(result);
    op_right.setClear();
    fun_st2(key);
    c_state = STATE.st2;
  }

  private void fun_result() {
    switch (symbol_use) {
      case '+':
        result = op_left.value() + op_right.value();
        break;
      case '-':
        result = op_left.value() - op_right.value();
        break;
      case '*':
        result = op_left.value() * op_right.value();
        break;
      case '/':
        if (op_right.value() == 0.0)
          result = Double.POSITIVE_INFINITY;
        else
          result = op_left.value() / op_right.value();
        break;
      default: // 等号，或来自 st1 的直接按下等号
        result = op_left.value();
        break;
    }
  }

  // 在此函数中设定状态机模型
  void keyPressed(char key) {
    //expression += key;
    if (key == 'c')
      c_state = STATE.st0; // 复位一下

    switch (c_state) {
      case st0:
        reset(); // 初始状态，并且等待输入
      case st1:
        fun_st1_basic(key);
        break; // 输入左操作数
      // ===============
      case st2:
        fun_st2(key);
        break;
      case st3:
        fun_st3(key);
        break;
      case st4:
        fun_st4(key);
        break;
      case st5:
        fun_st5(key);
        break;
      default:
        reset();
        c_state = STATE.st0;
        break;
    }
  }

  // TODO: you can modify this method to print any debug
  //  information (It will be called by CalculatorCmd)
  void debugPrintStatus() {
    // System.out.println("Expression = " + expression);
    System.out.println("c_state" + c_state);
  }

}
