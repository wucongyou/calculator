package com.echo.calculator.service;

import com.echo.calculator.util.ArithmeticUtil;

/**
 * Created by echo on 16-9-21.
 */
public class Multiplication implements IOperation {

  @Override
  public String doCalculate(Context context) {
    return ArithmeticUtil.multiply(context.getFst(), context.getSnd()).toString();
  }
  
}
