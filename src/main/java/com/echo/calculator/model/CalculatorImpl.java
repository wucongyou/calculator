package com.echo.calculator.model;

import com.echo.calculator.constant.CalcState;
import com.echo.calculator.constant.CalcStatus;
import com.echo.calculator.constant.CalcOperator;
import com.echo.calculator.service.Addition;
import com.echo.calculator.service.Context;
import com.echo.calculator.service.Division;
import com.echo.calculator.service.Equal;
import com.echo.calculator.service.Multiplication;
import com.echo.calculator.service.Operation;
import com.echo.calculator.service.Sine;
import com.echo.calculator.service.Subtraction;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by echo on 16-9-21.
 */
public class CalculatorImpl implements Calculator {

  public static final String DEFAULT_RESULT = "0";

  private CalcContext ctx;
  private Map<String, Operation> opes;

  public CalculatorImpl() {
    //opes
    Map<String, Operation> opes = new HashMap<>();
    opes.put(CalcOperator.PLUS.value + "", new Addition());
    opes.put(CalcOperator.MINUS.value + "", new Subtraction());
    opes.put(CalcOperator.TIMES.value + "", new Multiplication());
    opes.put(CalcOperator.DIVISION.value + "", new Division());
    opes.put(CalcOperator.SINE.value + "", new Sine());
    opes.put(CalcOperator.EQUAL.value + "", new Equal());
    //ctx
    CalcContext ctx = new CalcContext();
    ctx.setState(CalcState.RESET);
    ctx.setLeftOp(new OperandNum("0"));
    ctx.setRightOp(new OperandNum("0"));
    ctx.setResult("");
    ctx.setStatus(CalcStatus.NORMAL);
    ctx.setRecentOperator(CalcOperator.BLANK);
    this.ctx = ctx;
    this.opes = opes;
  }

  public CalculatorImpl(CalcContext ctx, Map<String, Operation> opes) {
    this.ctx = ctx;
    this.opes = opes;
  }

  /**
   * 计算器的状态机状态跳转函数
   */
  @Override
  public void doReceiveInput(CalcOperator idf) {
    if (CalcOperator.CLEAR == idf) {
      ctx.setState(CalcState.RESET);
    }
    switch (ctx.getState()) {
      case RESET:
      case LEFT_OPERAND_WAIT:
        doReadLeftOperand(idf);
        break;
      case OPERATOR_WAIT:
        readOperator(idf);
        break;
      case RIGHT_OPERAND_WAIT:
        doReadRightOperand(idf);
        break;
      case SHOW:
        doShow(idf);
        break;
      case CONTINUE:
        doContinue(idf);
        break;
      default:
        reset();
        break;
    }
  }

  /**
   * 获取当前已经处理部分的表达式
   */
  @Override
  public String getPrettyOutput() {
    String leftValue = ctx.getLeftOp().getValue();
    String operatorValue = ctx.getRecentOperator().value;
    String rightValue = ctx.getRightOp().getValue();
    String result = ctx.getResult();
    switch (ctx.getState()) {
      case RESET:
      case LEFT_OPERAND_WAIT:
        return leftValue;
      case OPERATOR_WAIT:
        return leftValue + " " + operatorValue;
      case RIGHT_OPERAND_WAIT:
        return leftValue + " " + operatorValue + " " + rightValue;
      case SHOW:
        return result;
      default:
        return DEFAULT_RESULT;
    }
  }

  @Override
  public CalcContext getCalcContext() {
    CalcContext ctxCp = new CalcContext();
    ctxCp.setLeftOp(ctx.getLeftOp());
    ctxCp.setRightOp(ctx.getRightOp());
    ctxCp.setRecentOperator(ctx.getRecentOperator());
    ctxCp.setResult(ctx.getResult());
    return ctxCp;
  }

  private void doCalc() {
    if (CalcStatus.NORMAL != ctx.getStatus()) {
      ctx.setResult(ctx.getResult());
      return;
    }
    String leftValue = ctx.getLeftOp().getValue();
    String rightValue = ctx.getRightOp().getValue();
    Operation ope = lookUpOperation(ctx.getRecentOperator());
    if (null == ope) {
      ctx.setResult(leftValue);
      return;
    }

    ctx.setResult(lookUpOperation(ctx.getRecentOperator()).doCalculate(new Context(leftValue, rightValue)));
    if (CalcStatus.POSITIVE_INFINITY.equals(ctx.getResult())) {
      ctx.setStatus(CalcStatus.POSITIVE_INFINITY);
    } else if (CalcStatus.NEGATIVE_INFINITY.equals(ctx.getResult())) {
      ctx.setStatus(CalcStatus.NEGATIVE_INFINITY);
    }
  }

  private Operation lookUpOperation(CalcOperator operator) {
    return opes.get(operator.value + "");
  }

  /**
   * 重置
   */
  private void reset() {
    ctx.getLeftOp().clear();
    ctx.getRightOp().clear();
    ctx.setResult("0");
    ctx.setRecentOperator(CalcOperator.BLANK);
    ctx.setState(CalcState.RESET);
  }

  private void doReadLeftOperand(CalcOperator idf) {
    switch (idf) {
      case ONE:
      case TWO:
      case THREE:
      case FOUR:
      case FIVE:
      case SIX:
      case SEVEN:
      case EIGHT:
      case NINE:
      case ZERO:
      case DOT:
      case SIGN:
        ctx.getLeftOp().readChar(idf);
        break;
      case EQUAL:
        ctx.setRecentOperator(idf);
        doCalc();
        ctx.setState(CalcState.SHOW);
        break;
      case CLEAR:
        reset();
        ctx.setState(CalcState.LEFT_OPERAND_WAIT);
        break;
      default:
        ctx.setState(CalcState.OPERATOR_WAIT);
        readOperator(idf);
    }
  }

  private void readOperator(CalcOperator idf) {
    switch (idf) {
      case PLUS:
      case MINUS:
      case TIMES:
      case DIVISION:
      case SINE:
        ctx.setRecentOperator(idf);
        ctx.setState(CalcState.OPERATOR_WAIT);
        break;
      case ONE:
      case TWO:
      case THREE:
      case FOUR:
      case FIVE:
      case SIX:
      case SEVEN:
      case EIGHT:
      case NINE:
      case ZERO:
      case DOT:
      case SIGN:
        doReadRightOperand(idf);
        ctx.setState(CalcState.RIGHT_OPERAND_WAIT);
        break;
      case EQUAL:
        doCalc();
        ctx.setState(CalcState.SHOW);
    }

  }

  private void doReadRightOperand(CalcOperator idf) {
    switch (idf) {
      case ONE:
      case TWO:
      case THREE:
      case FOUR:
      case FIVE:
      case SIX:
      case SEVEN:
      case EIGHT:
      case NINE:
      case ZERO:
      case DOT:
      case SIGN:
        ctx.getRightOp().readChar(idf);
        break;
      case EQUAL:
        doCalc();
        ctx.setState(CalcState.SHOW);
        break;
      case CLEAR:
        reset();
        ctx.setState(CalcState.RESET);
        break;
      default:
        ctx.setState(CalcState.CONTINUE);
        doContinue(idf);
    }
  }

  private void doShow(CalcOperator idf) {
    switch (idf) {
      //new
      case ONE:
      case TWO:
      case THREE:
      case FOUR:
      case FIVE:
      case SIX:
      case SEVEN:
      case EIGHT:
      case NINE:
      case ZERO:
      case DOT:
      case SIGN:
        reset();
        break;
      //continue
      case PLUS:
      case MINUS:
      case TIMES:
      case DIVISION:
      case SINE:
        doContinue(idf);
        break;
    }
  }

  private void doContinue(CalcOperator idf) {
    doCalc();
    ctx.getLeftOp().setValue(ctx.getResult());
    ctx.getRightOp().clear();
    readOperator(idf);
    ctx.setState(CalcState.OPERATOR_WAIT);
  }

}
