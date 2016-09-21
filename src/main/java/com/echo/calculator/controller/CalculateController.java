package com.echo.calculator.controller;

import com.echo.calculator.constant.State;
import com.echo.calculator.model.OperandNum;

/**
 * Created by echo on 16-9-21.
 */
public class CalculateController {
  private String result = "0";
  private String lastUsedOperator = "";
  private State state = State.LEFT_OPERAND;
  private OperandNum op1,op2;

}
