package com.echo.calculator.util;

import java.math.BigDecimal;

/**
 * Created by echo on 16-9-21.
 */
public class ArithmeticUtil {

  public static BigDecimal add(String fst, String snd) {
    return new BigDecimal(fst).add(new BigDecimal(snd));
  }

  public static BigDecimal subtract(String fst, String snd) {
    return new BigDecimal(fst).subtract(new BigDecimal(snd));
  }

  public static BigDecimal multiply(String fst, String snd) {
    return new BigDecimal(fst).multiply(new BigDecimal(snd));
  }

  public static BigDecimal divide(String fst, String snd) {
    return new BigDecimal(fst).divide(new BigDecimal(snd));
  }
}
