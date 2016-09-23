package com.echo.calculator.model;

import com.echo.calculator.constant.CalcStatus;
import com.echo.calculator.constant.Operator;
import com.echo.calculator.constant.State;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by echo on 16-9-23.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CalcContext {

  private Operator recentOperator;
  private OperandNum leftOp;
  private OperandNum rightOp;
  private String result;
  private State state;
  private CalcStatus status;


}
