package org.apache.commons.numbers.gamma

/**
 * @since 1.1
 */
object BoostMath {

  def powm1(x: Double, y: Double): Double = {
    if(x > 0) {
      if(Math.abs(y * (x - 1)) < 0.5 || Math.abs(y) < 0.2) {
        val l = y * Math.log(x)
        if(l < 0.5D) {
          Math.expm1(l)
        } else {
          Math.pow(x, y) - 1.0D
        }
      } else {
        Math.pow(x, y) - 1.0D
      }
    } else if(x < 0 && Math.rint(y * 0.5) == (y * 0.5)) {
      powm1(-x, y)
    } else {
      Math.pow(x, y) - 1.0D
    }
  }

}
