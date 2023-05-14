package org.apache.commons.numbers.core

/**
 * @since 1.1
 */
object ExtendedPrecision {

  final val Multiplier = 134217729.0D

  final val SafeUpper = 669692879491417075592765655662501131600878007315958504652343992731469406953085076558248986759809911329746670573470716765741965803557696277249036098418660925245910485926514436588817162816398196367372136384565404686473871329212422972447846496629816432160699779855408885478776864478289024177325354254336.0D

  final val DownScale = 0.000000000931322574615478515625D

  final val UpScale = 1073741824D

  final val ExpMask = 0x7ff

  final val CmpUnsigned2046 = Int.MinValue + 2046

  final val CmpUnsignedMinus1 = Int.MinValue - 1

  def productLow(x: Double, y: Double, xy: Double): Double = {
    if(isNotNormal(xy)) {
      xy - xy
    } else {
      val a: Double = Math.abs(x)
      val b: Double = Math.abs(y)
      if(a + b + Math.abs(xy) >= SafeUpper) {
        if(a > b) {
          productLowUnscaled(x * DownScale, y, xy * DownScale) * UpScale
        } else {
          productLowUnscaled(x, y * DownScale, xy * DownScale) * UpScale
        }
      } else {
        productLowUnscaled(x, y, xy)
      }
    }
  }

  def isNotNormal(a: Double): Boolean = {
    val baisedExponent = (java.lang.Double.doubleToRawLongBits(a) >>> 52).toInt & ExpMask
    (baisedExponent + CmpUnsignedMinus1) >= CmpUnsigned2046
  }

  def productLowUnscaled(x: Double, y: Double, xy: Double) = {
    val hx: Double = highPartUnscaled(x)
    val lx: Double = x - hx

    val hy: Double = highPartUnscaled(y)
    val ly: Double = y - hy

    productLow(hx, lx, hy, ly, xy)
  }

  def squareLowUnscaled(x: Double, xx: Double): Double = {
    val hx: Double = highPartUnscaled(x)
    val lx: Double = x - hx

    productLow(hx, lx, hx, lx, xx)
  }

  def productLow(hx: Double, lx: Double, hy: Double, ly: Double, xy: Double) = {
    (lx * ly) - (((xy - hx * hy) - lx * hy) - hx * ly)
  }

  def highPartUnscaled(value: Double): Double = {
    val c = Multiplier * value
    c - (c - value)
  }

  def twoSumLow(a: Double, b: Double, sum: Double): Double = {
    val bVirtual = sum - a
    (a - (sum - bVirtual)) + (b - bVirtual)
  }

}
