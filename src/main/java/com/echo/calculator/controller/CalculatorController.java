package com.echo.calculator.controller;

/**
 * Created by echo on 16-9-24.
 */

// The controller of the calculator application

import com.echo.calculator.constant.CalcOperator;
import com.echo.calculator.model.CalcContext;
import com.echo.calculator.model.ICalculator;
import com.echo.calculator.model.CalculatorImpl;

import java.awt.event.*;

import javax.swing.*;

public class CalculatorController implements ActionListener {

  private ICalculator calc = new CalculatorImpl();

  private JTextField textField;

  private JTextArea textArea;

  public void actionPerformed(ActionEvent e) {
    CalcOperator key = CalcOperator.getByValue(e.getActionCommand());
    calc.doReceiveInput(key);
    if (null != textField) {
      textField.setText(calc.getPrettyOutput());
    }
    if (null != textArea && CalcOperator.EQUAL == key) {
      CalcContext ctx = calc.getCalcContext();

      switch (ctx.getRecentOperator()) {
        case EQUAL:
          textArea.append(ctx.getLeftOp().getValue()
              + " = "
              + ctx.getResult()
              + "\r\n");
          break;
        case SINE:
          textArea.append(
              "Sin("
                  + ctx.getLeftOp().getValue()
                  + ")"
                  + " = "
                  + ctx.getResult()
                  + "\r\n");
          break;
        default:
          textArea.append(ctx.getLeftOp().getValue()
              + " "
              + ctx.getRecentOperator().value
              + " "
              + ctx.getRightOp().getValue()
              + " = "
              + ctx.getResult()
              + "\r\n");
      }
    }
  }

  public void setTextField(JTextField textField) {
    this.textField = textField;
  }

  public void setTextArea(JTextArea textArea) {
    this.textArea = textArea;
  }
  
}
