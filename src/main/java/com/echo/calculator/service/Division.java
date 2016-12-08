package com.echo.calculator.service;

import com.echo.calculator.constant.CalcStatus;
import com.echo.calculator.util.ArithmeticUtil;

import java.math.BigDecimal;

/**
 * Created by echo on 16-9-21.
 */
public class Division implements IOperation {

  @Override
  public String doCalculate(Context context) {

    String result;
    //进行除以0判断
    if (BigDecimal.ZERO.compareTo(new BigDecimal(context.getSnd())) == 0) {
      if (new BigDecimal(context.getFst()).compareTo(BigDecimal.ZERO) > 0) {
        result = CalcStatus.POSITIVE_INFINITY.desc;
      } else {
        result = CalcStatus.NEGATIVE_INFINITY.desc;
      }
      return result;
    }
    result = ArithmeticUtil.divide(context.getFst(), context.getSnd()).toString();
    return result;
  }

}
