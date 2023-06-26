package org.apache.commons.numbers.gamma

/**
 * @since 1.1
 */
object LogBeta {

  final val Ten = 10.0D
  final val Two = 2.0D
  final val Thousand = 1000.0D
  final val HalfLogTwoPi = 0.9189385332046727D

  final val Delta: IArray[Double] = IArray(
    0.833333333333333333333333333333E-01D,
    -0.277777777777777777777777752282E-04D,
    0.793650793650793650791732130419E-07D,
    -0.595238095238095232389839236182E-09D,
    0.841750841750832853294451671990E-11D,
    -0.191752691751854612334149171243E-12D,
    0.641025640510325475730918472625E-14D,
    -0.295506514125338232839867823991E-15D,
    0.179643716359402238723287696452E-16D,
    -0.139228964661627791231203060395E-17D,
    0.133802855014020915603275339093E-18D,
    -0.154246009867966094273710216533E-19D,
    0.197701992980957427278370133333E-20D,
    -0.234065664793997056856992426667E-21D,
    0.171348014966398575409015466667E-22D
  )

  def deltaMinusDeltaSum(a: Double, b: Double): Double = {
    if (a < 0 || a > b) throw new GammaException(GammaException.OutOfRange, a, 0, b)
    if (b < Ten) throw new GammaException(GammaException.OutOfRange, b, Ten, Double.PositiveInfinity)

    val h = a / b
    val p = h / (1 + h)
    val q = 1 / (1 + h)
    val q2 = q * q

    val s = new Array[Double](Delta.length)
    s(0) = 1
    var i = 1
    while (i < s.length) {
      s(i) = 1 + (q + q2 * s(i - 1))

      i = i + 1
    }

    val sqrtT = 10 / b
    val t = sqrtT * sqrtT
    var w = Delta(Delta.length - 1) * s(s.length - 1)
    i = Delta.length - 2
    while (i >= 0) {
      w = t * w + Delta(i) * s(i)

      i = i - 1
    }

    w * p / b
  }

  def sumDeltaMinusDeltaSum(p: Double, q: Double): Double = {
    if (p < Ten) throw new GammaException(GammaException.OutOfRange, p, Ten, Double.PositiveInfinity)
    if (q < Ten) throw new GammaException(GammaException.OutOfRange, q, Ten, Double.PositiveInfinity)

    val a = Math.min(p, q)
    val b = Math.max(p, q)
    val sqrtT = 10 / a
    val t = sqrtT * sqrtT
    var z = Delta(Delta.length - 1)

    var i = Delta.length - 2
    while (i >= 0) {
      z = t * z + Delta(i)

      i = i - 1
    }

    z / a + deltaMinusDeltaSum(a, b)
  }

  def value(p: Double, q: Double): Double = {
    if(p.isNaN || q.isNaN || p <= 0.0 || q <= 0.0) {
      Double.NaN
    } else {
      val a = Math.min(p, q)
      val b = Math.max(p, q)
      if(a >= Ten) {
        val w = sumDeltaMinusDeltaSum(a, b)
        val h = a / b
        val c = h / (1 + h)
        val u = -(a - 0.5) * Math.log(c)
        val v = b * Math.log1p(h)
        if (u <= v) {
          (((-0.5 * Math.log(b) + HalfLogTwoPi) + w) - u) - v
        } else {
          (((-0.5 * Math.log(b) + HalfLogTwoPi) + w) - v) - u
        }
      } else if(a > Two) {
        if(b > Thousand) {
          val n = Math.floor(a - 1).toInt
          var prod: Double = 1.0
          var ared: Double = a
          for (i <- 0 until n) {
            ared -= 1
            prod *= ared / (1 + ared / b)
          }
          (Math.log(prod) - n * Math.log(b)) +
            (LogGamma.value(ared) +
              logGammaMinusLogGammaSum(ared, b))
        } else {
          var prod1: Double = 1.0
          var ared: Double = a
          while (ared > 2) {
            ared -= 1
            val h = ared / b
            prod1 *= h / (1 + h)
          }
          if(b < Ten) {
            var prod2: Double = 1
            var bred: Double = b
            while (bred > 2) {
              bred -= 1
              prod2 *= bred / (ared + bred)
            }
            Math.log(prod1) +
              Math.log(prod2) +
              (LogGamma.value(ared) +
                (LogGamma.value(bred) -
                  LogGammaSum.value(ared, bred)))
          } else {
            Math.log(prod1) +
              LogGamma.value(ared) +
              logGammaMinusLogGammaSum(ared, b)
          }
        }
      } else if(a >= 1.0) {
        if(b > Two) {
          if(b < Ten) {
            var prod: Double = 1.0
            var bred: Double = b
            while (bred > 2) {
              bred -= 1
              prod *= bred / (a + bred)
            }
            Math.log(prod) +
              (LogGamma.value(a) +
                (LogGamma.value(bred) -
                  LogGammaSum.value(a, bred)))
          } else {
            LogGamma.value(a) +
              logGammaMinusLogGammaSum(a, b)
          }
        } else {
          LogGamma.value(a) +
            LogGamma.value(b) -
            LogGammaSum.value(a, b)
        }
      } else {
        if(b >= Ten) {
          LogGamma.value(a) +
            logGammaMinusLogGammaSum(a, b)
        } else {
          val beta: Double = Gamma.value(a) * Gamma.value(b) / Gamma.value(a + b)
          if(beta.isFinite) {
            Math.log(beta)
          } else {
            LogGamma.value(a) + (LogGamma.value(b) - LogGamma.value(a + b))
          }
        }
      }
    }
  }

  def logGammaMinusLogGammaSum(a: Double, b: Double): Double = {
    if (a < 0) throw new GammaException(GammaException.OutOfRange, a, 0, Double.PositiveInfinity)
    if (b < Ten) throw new GammaException(GammaException.OutOfRange, b, Ten, Double.PositiveInfinity)

    var d: Double = 0.0
    var w: Double = 0.0
    if (a <= b) {
      d = b + (a - 0.5)
      w = deltaMinusDeltaSum(a, b)
    } else {
      d = a + (b - 0.5)
      w = deltaMinusDeltaSum(b, a)
    }

    val u = d * Math.log1p(a / b)
    val v = a * (Math.log(b) - 1)

    if (u <= v)
      (w - u) - v
    else
      (w - v) - u
  }

}
