package org.apache.commons.numbers.gamma

/**
 * @since 1.1
 */
object LogGamma1p {

  def value(x: Double): Double = {
    if (x < -0.5 || x > 1.5) throw new GammaException(GammaException.OutOfRange, x, -0.5, 1.5)

    -Math.log1p(InvGamma1pm1.value(x))
  }

}
