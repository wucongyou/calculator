package com.echo.calculator.service;

import com.echo.calculator.util.ArithmeticUtil;

import java.math.BigDecimal;

/**
 * Created by echo on 16-9-24.
 */
public class Sine implements Operation {
  @Override
  public String doCalculate(Context context) {
    return ArithmeticUtil.sine(context.getFst()).toString();
  }
}
