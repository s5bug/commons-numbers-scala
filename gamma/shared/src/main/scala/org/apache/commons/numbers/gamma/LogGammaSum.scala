package org.apache.commons.numbers.gamma

/**
 * @since 1.1
 */
object LogGammaSum {

  def value(a: Double, b: Double): Double = {
    if (a < 1 || a > 2) throw new GammaException(GammaException.OutOfRange, a, 1, 2)
    if (b < 1 || b > 2) throw new GammaException(GammaException.OutOfRange, b, 1, 2)

    val x = (a - 1) + (b - 1)
    if (x <= 0.5)
      LogGamma1p.value(1 + x)
    else if (x <= 1.5)
      LogGamma1p.value(x) + Math.log1p(x)
    else
      LogGamma1p.value(x - 1) + Math.log(x * (1 + x))
  }

}
