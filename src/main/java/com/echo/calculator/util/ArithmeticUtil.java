package com.echo.calculator.util;

import java.math.BigDecimal;

/**
 * Created by echo on 16-9-21.
 */
public class ArithmeticUtil {

  private static final int DEFAULT_SCALE = 20;

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
    return new BigDecimal(fst).divide(new BigDecimal(snd), DEFAULT_SCALE, BigDecimal.ROUND_HALF_UP);
  }

  public static BigDecimal sine(String num) {
    return new BigDecimal(Math.sin(new BigDecimal(num).doubleValue()))
        .divide(new BigDecimal(1), DEFAULT_SCALE, BigDecimal.ROUND_HALF_UP);
  }
  
}
