package org.apache.commons.numbers.gamma

object BoostErf {

  final val Multiplier = 134217729D

  final val OneOverRootPi = 0.5641895835477562869480794515607725858D

  final val ErfcxApprox = 6.71e7D

  final val ComputeErf = 0.5D

  final val ErfcxNegXMax = Math.sqrt(Math.log(Double.MaxValue / 2.0D))

  final val ExpXx1 = 0.0000000097788870334625244140625D

  def erfc(x: Double): Double =
    erfImp(x, true, false)

  def erf(x: Double): Double =
    erfImp(x, false, false)

  def erfImp(z: Double, invert: Boolean, scaled: Boolean): Double = {
    ???
  }

  def erfcx(x: Double): Double = {
    ???
  }

  def erfcInv(z: Double): Double = {
    ???
  }

  def erfInv(z: Double): Double = {
    ???
  }

  def erfInvImp(p: Double, q: Double): Double = {
    ???
  }

  def expxx(x: Double): Double = {
    val a: Double = x * x
    val b: Double = squareLowUnscaled(x, a)
    expxx(a, b)
  }

  def expmxx(x: Double): Double = {
    val a = x * x
    val b = squareLowUnscaled(x, a)
    expxx(-a, -b)
  }

  def expxx(a: Double, b: Double): Double = {
    val ea = Math.exp(a)
    ea * b + ea
  }

  def squareLowUnscaled(x: Double, xx: Double): Double = {
    val hx = highPartUnscaled(x)
    val lx = x - hx
    squareLow(hx, lx, xx)
  }

  def highPartUnscaled(value: Double): Double = {
    val c = Multiplier * value
    c - (c - value)
  }

  def squareLow(hx: Double, lx: Double, xx: Double): Double = {
    lx * lx - ((xx - hx * hx) - 2 * lx * hx)
  }

}
