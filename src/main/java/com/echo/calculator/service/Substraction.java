package com.echo.calculator.service;

import com.echo.calculator.util.ArithmeticUtil;

/**
 * Created by echo on 16-9-21.
 */
public class Substraction implements IOperation {

  @Override
  public String doCalculate(Context context) {
    return ArithmeticUtil.subtract(context.getFst(), context.getSnd()).toString();
  }
}
