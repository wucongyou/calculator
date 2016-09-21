package com.echo.calculator.service;

import com.echo.calculator.util.BigDecimalUtil;

/**
 * Created by echo on 16-9-21.
 */
public class Multiplication implements IOperation {
  @Override
  public String doCalculate(Context context) {
    return BigDecimalUtil.multiply(context.getFst(), context.getSnd()).toString();
  }
}
