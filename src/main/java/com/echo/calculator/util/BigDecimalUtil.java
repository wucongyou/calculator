package com.echo.calculator.util;

import java.math.BigDecimal;

/**
 * Created by echo on 16-9-21.
 */
public class BigDecimalUtil {

  public static BigDecimal add(String fst, String snd) {
    return new BigDecimal(fst).add(new BigDecimal(snd));
  }

  public static BigDecimal substract(String fst, String snd) {
    return new BigDecimal(fst).subtract(snd);
  }

  public static BigDecimal multiply(String fst, String snd) {
    return new BigDecimal(fst).multiply(snd);
  }

  public static BigDecimal divide(String fst, String snd) {
    return new BigDecimal(fst).divide(new BigDecimal(snd));
  }
}
