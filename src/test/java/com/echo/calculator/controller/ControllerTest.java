package com.echo.calculator.controller;

import com.echo.calculator.constant.CalcStatus;
import com.echo.calculator.constant.Operator;
import com.echo.calculator.constant.State;
import com.echo.calculator.model.CalcContext;
import com.echo.calculator.model.OperandNum;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by echo on 16-9-23.
 */
public class ControllerTest {

  @Test
  public void testCalc_two_integer() {
    String keySeq = "1+2=";
    CalcContext ctx = parseExpression(keySeq);
    System.out.println(ctx);
    assertContext(ctx, "1", Operator.PLUS, "2", "3");
  }

  @Test
  public void testCalc_decimal() {
    String keySeq = "1.1+2.2=";
    CalcContext ctx = parseExpression(keySeq);
    System.out.println(ctx);
    assertContext(ctx, "1.1", Operator.PLUS, "2.2", "3.3");
  }

  @Test
  public void testCalc_multiDot_decimal() {
    String keySeq = "1.1.1.1+1=";
    CalcContext ctx = parseExpression(keySeq);
    System.out.println(ctx);
    assertContext(ctx, "1.111", Operator.PLUS, "1", "2.111");
  }

  @Test
  public void testCalc_clear() {
    String keySeq = "123+4.1c";
    CalcContext ctx = parseExpression(keySeq);
    assertContext(ctx, "0", Operator.BLANK, "0", "0");
  }

  @Test
  public void testCalc_divide_by_zero() {
    String keySeq = "1/0=";
    CalcContext ctx = parseExpression(keySeq);
    System.out.println(ctx);
    assertContext(ctx, "1", Operator.DIVISION, "0", CalcStatus.POSITIVE_INFINITY.desc);
  }

  private void assertContext(CalcContext ctx, String leftValue, Operator operator, String rightValue, String result) {
    assertThat(ctx.getLeftOp().getValue(), is(leftValue));
    assertThat(ctx.getRecentOperator(), is(operator));
    assertThat(ctx.getRightOp().getValue(), is(rightValue));
    assertThat(ctx.getResult(), is(result));
  }

  private CalcContext parseExpression(String exp) {
    CalcContext ctx = new CalcContext();
    ctx.setState(State.RESET);
    ctx.setLeftOp(new OperandNum("0"));
    ctx.setRightOp(new OperandNum("0"));
    ctx.setResult("");
    ctx.setStatus(CalcStatus.NORMAL);
    ctx.setRecentOperator(Operator.BLANK);
    CalculateController controller = new CalculateController(ctx);

    char[] keySeqChars = exp.toCharArray();
    for (char keySeqChar : keySeqChars) {
      //System.out.println("leftOpOld:" + controller.getCtx().getLeftOp().getValue() + ",rightOp:" + controller.getCtx().getRightOp().getValue());
      //State oldState = controller.getCtx().getState();
      controller.keyPressed(keySeqChar);
      //  System.out.println("keyPressed:" + keySeqChar);
      // System.out.println(oldState + "-->" + controller.getCtx().getState());
      //  System.out.println("ResultExp:" + controller.getExpression());
    }
    return ctx;
  }

}
