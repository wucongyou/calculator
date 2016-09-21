package com.echo.calculator.service;

import com.echo.calculator.util.BigDecimalUtil;

/**
 * Created by echo on 16-9-21.
 */
public class Addition implements IOperation {
  @Override
  public String doCalculate(Context context) {
    return BigDecimalUtil.add(context.getFst(),context.getSnd()).toString();
  }
}
