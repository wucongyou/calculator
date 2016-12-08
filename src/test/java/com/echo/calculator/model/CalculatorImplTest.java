package com.echo.calculator.model;

import com.echo.calculator.constant.CalcStatus;
import com.echo.calculator.constant.CalcOperator;
import com.echo.calculator.constant.CalcState;
import com.echo.calculator.service.Addition;
import com.echo.calculator.service.Division;
import com.echo.calculator.service.Multiplication;
import com.echo.calculator.service.IOperation;
import com.echo.calculator.service.Subtraction;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by echo on 16-9-23.
 */
public class CalculatorImplTest {

  @Test
  public void testCalc_normal() {
    baseTest("~1.2.3.4.5+2=", "-1.2345", CalcOperator.PLUS, "2");
    baseTest("~1.2*~2=", "-1.2", CalcOperator.TIMES, "-2");
    baseTest("9.9-~1=", "9.9", CalcOperator.MINUS, "-1");
    baseTest("1.4/7=", "1.4", CalcOperator.DIVISION, "7");
  }


  @Test
  public void testCalc_infinity() {
    CalcContext ctx = baseTest("1.4/0=", "1.4", CalcOperator.DIVISION, "0");
    assertThat(ctx.getResult(), is(CalcStatus.POSITIVE_INFINITY.desc));
    ctx = baseTest("~1.4/0=", "-1.4", CalcOperator.DIVISION, "0");
    assertThat(ctx.getResult(), is(CalcStatus.NEGATIVE_INFINITY.desc));
  }

  @Test
  public void testCalc_specialSeq() {
    baseTest("123+4.1c", "0", CalcOperator.BLANK, "0");
    baseTest("8+-*9=", "8", CalcOperator.TIMES, "9");
    baseTest(".3=", "0.3", CalcOperator.EQUAL, "0");
  }

  @Test
  public void testCalc_continue() {
    baseTest("1+~3*5=", "-2", CalcOperator.TIMES, "5");
  }

  private CalcContext baseTest(String exp, String leftValue, CalcOperator operator, String rightVale) {
    CalcContext ctx = iniTestEnv(exp);
    assertContext(ctx, leftValue, operator, rightVale);
    return ctx;
  }

  private void assertContext(CalcContext ctx, String leftValue, CalcOperator operator, String rightValue) {
    assertThat(ctx.getLeftOp().getValue(), is(leftValue));
    assertThat(ctx.getRecentOperator(), is(operator));
    assertThat(ctx.getRightOp().getValue(), is(rightValue));
  }

  private CalcContext iniTestEnv(String exp) {
    //opes
    Map<String, IOperation> opes = new HashMap<>();
    opes.put(CalcOperator.PLUS.value + "", new Addition());
    opes.put(CalcOperator.MINUS.value + "", new Subtraction());
    opes.put(CalcOperator.TIMES.value + "", new Multiplication());
    opes.put(CalcOperator.DIVISION.value + "", new Division());
    //ctx
    CalcContext ctx = new CalcContext();
    ctx.setState(CalcState.RESET);
    ctx.setLeftOp(new OperandNum("0"));
    ctx.setRightOp(new OperandNum("0"));
    ctx.setResult("");
    ctx.setStatus(CalcStatus.NORMAL);
    ctx.setRecentOperator(CalcOperator.BLANK);
    CalculatorImpl controller = new CalculatorImpl(ctx, opes);

    char[] keySeqChars = exp.toCharArray();
    for (char keySeqChar : keySeqChars) {
      controller.doReceiveInput(CalcOperator.getByValue(keySeqChar + ""));
    }
    return ctx;
  }

}
