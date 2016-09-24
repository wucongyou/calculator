package com.echo.calculator.controller;

import com.echo.calculator.view.CalculatorWindow;

/**
 * Created by echo on 16-9-24.
 */
class CalculatorApp {
  public static void main(String[] args) {
    CalculatorWindow mainWindow = new CalculatorWindow();
    CalculatorController control = new CalculatorController();
    mainWindow.setController(control);
    mainWindow.setVisible(true);
  }
}
