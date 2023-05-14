package org.apache.commons.numbers.fraction

/**
 * @since 1.1
 */
object GeneralizedContinuedFraction {

  final val Small = 1.0e-50D

  final val DefaultIterations = Int.MaxValue

  final val MinEpsilon = 0.00000000000000011102230246251565404236316680908203125D

  final val MaxEpsilon = 0.5D

  final val DefaultLow = 1 - MinEpsilon

  final val DefaultEps = 0.0000000000000002220446049250313080847263336181640625D

  final case class Coefficient(a: Double, b: Double)

  def value(gen: java.util.function.Supplier[Coefficient]): Double =
    value(gen, MinEpsilon, DefaultIterations)

  def value(gen: java.util.function.Supplier[Coefficient], epsilon: Double): Double =
    value(gen, epsilon, DefaultIterations)

  def value(gen: java.util.function.Supplier[Coefficient], epsilon: Double, maxIterations: Int): Double = {
    val c: Coefficient = gen.get()
    evaluate(c.b, gen, epsilon, maxIterations)
  }

  def value(b0: Double, gen: java.util.function.Supplier[Coefficient]): Double =
    value(b0, gen, MinEpsilon, DefaultIterations)

  def value(b0: Double, gen: java.util.function.Supplier[Coefficient], epsilon: Double): Double =
    value(b0, gen, epsilon, DefaultIterations)

  def value(b0: Double, gen: java.util.function.Supplier[Coefficient], epsilon: Double, maxIterations: Int): Double = {
    val c = gen.get
    b0 + c.a / evaluate(c.b, gen, epsilon, maxIterations)
  }

  def evaluate(b0: Double, gen: java.util.function.Supplier[Coefficient], epsilon: Double, maxIterations: Int): Double = {
    var low: Double = 0.0D
    var eps: Double = 0.0D

    if (epsilon > MinEpsilon && epsilon <= MaxEpsilon) {
      low = 1 - epsilon
      eps = 1 / low - 1
    } else {
      low = DefaultLow
      eps = DefaultEps
    }

    var hPrev: Double = updateIfCloseToZero(b0)

    var dPrev: Double = 0.0
    var cPrev: Double = hPrev

    var n: Int = maxIterations
    while (n > 0) {
      val c = gen.get()
      val a = c.a
      val b = c.b

      var dN: Double = updateIfCloseToZero(b + a * dPrev)
      val cN: Double = updateIfCloseToZero(b + a / cPrev)

      dN = 1 / dN
      val deltaN = cN * dN
      val hN = hPrev * deltaN

      if (!hN.isFinite) throw new FractionException("Continued fraction diverged to " + hN)

      if (deltaN == 0) throw new FractionException("Ratio of successive convergents is zero")

      if (Math.abs(deltaN - 1) <= eps && deltaN >= low) {
        return hN
      }

      dPrev = dN
      cPrev = cN
      hPrev = hN

      n -= 1
    }

    throw new FractionException("Maximum iterations (%d) exceeded", maxIterations)
  }

  def updateIfCloseToZero(value: Double): Double = {
    if (Math.abs(value) < Small) Math.copySign(Small, value)
    else value
  }

}
