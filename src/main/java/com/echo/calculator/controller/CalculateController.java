package com.echo.calculator.controller;

import com.echo.calculator.constant.CalcStatus;
import com.echo.calculator.constant.Operator;
import com.echo.calculator.constant.State;
import com.echo.calculator.model.CalcContext;
import com.echo.calculator.util.ArithmeticUtil;
import com.echo.calculator.util.IdentifierUtil;

import lombok.Data;

import java.math.BigDecimal;

/**
 * Created by echo on 16-9-21.
 */
public class CalculateController {

  public static final String DEFAULT_RESULT = "0";

  private CalcContext ctx;

  public CalculateController(CalcContext ctx) {
    this.ctx = ctx;
  }

  // 在屏幕上显示的内容，依据当前的不同状态来定。
  public String getExpression() {
    String leftValue = ctx.getLeftOp().getValue();
    char operatorValue = ctx.getRecentOperator().value;
    String rightValue = ctx.getRightOp().getValue();
    String result = ctx.getResult();
    switch (ctx.getState()) {
      case RESET:
      case LEFT_OPERAND_WAIT:
        return leftValue;
      case OPERATOR_WAIT:
        return leftValue + operatorValue;
      case RIGHT_OPERAND_WAIT:
        return leftValue + operatorValue + rightValue;
      case SHOW:
        return result;
      default:
        return DEFAULT_RESULT;
    }
  }

  /**
   * 重置
   */
  private void reset() {
    ctx.getLeftOp().clear();
    ctx.getRightOp().clear();
    ctx.setResult("0");
    ctx.setRecentOperator(Operator.BLANK);
    ctx.setState(State.RESET);
  }

  private void readLeftOperand(char c) {

    if (IdentifierUtil.isDigit(c) || IdentifierUtil.isDot(c) || IdentifierUtil.isSign(c)) {
      ctx.getLeftOp().readChar(c);
    } else if (c == Operator.EQUAL.value) {
      ctx.setRecentOperator(Operator.EQUAL);
      calcResult();
      ctx.setState(State.SHOW);
    } else if (c == Operator.CLEAR.value) { // 按下C键
      reset();
      ctx.setState(State.LEFT_OPERAND_WAIT);
    } else {
      ctx.setState(State.OPERATOR_WAIT);
      readOperator(c);
    }
  }

  private void readOperator(char key) {
    if (key == '+' || key == '-' || key == '*' || key == '/') {
      ctx.setRecentOperator(Operator.getByValue(key));
      ctx.setState(State.RIGHT_OPERAND_WAIT);
    } else if ((key >= '0' && key <= '9') || key == '.' || key == '~') {
      readOperandRight(key);
      ctx.setState(State.OPERATOR_WAIT);
    }
  }

  private void readOperandRight(char key) {
    if ((key >= '0' && key <= '9') || key == '.' || key == '~') {
      ctx.getRightOp().readChar(key);
    } else if (key == Operator.EQUAL.value) {
      calcResult();
      ctx.setState(State.SHOW);
    } else if (key == Operator.CLEAR.value) { // 按下C键
      reset();
      ctx.setState(State.RESET);
    } else {
      ctx.setState(State.CONTINUE);
      beforeContinue(key);
    }
  }

  void showing(char key) { // 此时，已经显示出了结果
    if ((key >= '0' && key <= '9') || key == '.' || key == '~') {
      reset();
      ctx.getLeftOp().readChar(key);
      ctx.setState(State.LEFT_OPERAND_WAIT);
    } else if (key == '+' || key == '-' || key == '*' || key == '/') {
      ctx.getLeftOp().setValue(ctx.getResult());
      ctx.getRightOp().clear();
      readOperator(key);
      ctx.setState(State.OPERATOR_WAIT);
    }
  }

  void beforeContinue(char key) {
    calcResult();
    ctx.getLeftOp().setValue(ctx.getResult());
    ctx.getRightOp().clear();
    readOperator(key);
    ctx.setState(State.OPERATOR_WAIT);
  }

  private void calcResult() {

    if (CalcStatus.NORMAL != ctx.getStatus()) {
      ctx.setResult(ctx.getResult());
      return;
    }

    String leftValue = ctx.getLeftOp().getValue();
    String rightValue = ctx.getRightOp().getValue();
    String result;

    switch (ctx.getRecentOperator()) {
      case PLUS:
        result = ArithmeticUtil.add(leftValue, rightValue).toString();
        break;
      case MINUS:
        result = ArithmeticUtil.subtract(leftValue, rightValue).toString();
        break;
      case TIMES:
        result = ArithmeticUtil.multiply(leftValue, rightValue).toString();
        break;
      case DIVISION:
        //进行除以0判断
        if (BigDecimal.ZERO.compareTo(new BigDecimal(rightValue)) == 0) {
          if (new BigDecimal(leftValue).compareTo(BigDecimal.ZERO) > 0) {
            result = CalcStatus.POSITIVE_INFINITY.desc;
            ctx.setStatus(CalcStatus.POSITIVE_INFINITY);
          } else {
            ctx.setStatus(CalcStatus.NEGATIVE_INFINITY);
          }
          break;
        }
        result = ArithmeticUtil.divide(leftValue, rightValue).toString();
        break;
      default:
        result = leftValue;
        break;
    }
    ctx.setResult(result);
  }

  // 在此函数中设定状态机模型
  void keyPressed(char key) {
    if (key == Operator.CLEAR.value) {
      ctx.setState(State.RESET);
    }
    switch (ctx.getState()) {
      case RESET:
      case LEFT_OPERAND_WAIT:
        readLeftOperand(key);
        break;
      case OPERATOR_WAIT:
        readOperator(key);
        break;
      case RIGHT_OPERAND_WAIT:
        readOperandRight(key);
        break;
      case SHOW:
        showing(key);
        break;
      case CONTINUE:
        beforeContinue(key);
        break;
      default:
        reset();
        break;
    }
  }

  public CalcContext getCtx() {
    return ctx;
  }

  public void setCtx(CalcContext ctx) {
    this.ctx = ctx;
  }
}
