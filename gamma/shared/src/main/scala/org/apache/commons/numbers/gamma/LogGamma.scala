package org.apache.commons.numbers.gamma

import scala.annotation.static

/**
 * @since 1.1
 */
object LogGamma {

  def value(x: Double): Double =
    BoostGamma.lgamma(x)

  def value(x: Double, sign: Array[Int]): Double =
    BoostGamma.lgamma(x, sign)

}
