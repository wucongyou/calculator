package com.echo.calculator.service;

/**
 * Created by echo on 16-9-24.
 */
public class Equal implements IOperation {

  @Override
  public String doCalculate(Context context) {
    return context.getFst();
  }

}
