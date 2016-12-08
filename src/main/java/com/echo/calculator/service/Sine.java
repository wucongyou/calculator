package com.echo.calculator.service;

import com.echo.calculator.util.ArithmeticUtil;

/**
 * Created by echo on 16-9-24.
 */
public class Sine implements IOperation {

  @Override
  public String doCalculate(Context context) {
    return ArithmeticUtil.sine(context.getFst()).toString();
  }
  
}
