package org.apache.commons.numbers.gamma

/**
 * @since 1.1
 */
object Gamma {

  def value(x: Double): Double =
    BoostGamma.tgamma(x)

}
