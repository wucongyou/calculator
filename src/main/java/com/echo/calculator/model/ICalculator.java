package com.echo.calculator.model;

import com.echo.calculator.constant.CalcOperator;

/**
 * Created by echo on 16-9-24.
 */
public interface ICalculator {

  void doReceiveInput(CalcOperator idf);

  String getPrettyOutput();

  CalcContext getCalcContext();

}
