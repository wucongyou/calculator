package com.echo.calculator.constant;

/**
 * Created by echo on 16-9-21.
 */
public enum Identifier {

  OPERATOR("operator"),
  OPERAND("operand");

  public String desc;

  Identifier(String desc) {
    this.desc = desc;
  }
}
