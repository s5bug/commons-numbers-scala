package org.apache.commons.numbers.gamma

/**
 * @since 1.1
 */
object BoostTools {

  final val Epsilon: Double = 0.0000000000000002220446049250313080847263336181640625D

  final val KahanEpsilon: Double = 0.00000000000000000021684043449710088680149056017398834228515625D

  final val MsgFailedToConverge: String = "Failed to converge within %d iterations"

  def sumSeries(func: java.util.function.DoubleSupplier, epsilon: Double, maxTerms: Int): Double =
    sumSeries(func, epsilon, maxTerms, 0.0D)

  def sumSeries(func: java.util.function.DoubleSupplier, epsilon: Double, maxTerms: Int, initValue: Double): Double = {
    val eps = getEpsilon(epsilon, Epsilon)

    var result: Double = initValue
    var nextTerm: Double = func.getAsDouble
    result += nextTerm

    var counter: Int = maxTerms - 1

    while (Math.abs(eps * result) < Math.abs(nextTerm) && counter > 0) {
      nextTerm = func.getAsDouble
      result += nextTerm
      counter -= 1
    }

    if (counter <= 0) throw new ArithmeticException(String.format(MsgFailedToConverge, maxTerms))

    result
  }

  def kahanSumSeries(func: java.util.function.DoubleSupplier, epsilon: Double, maxTerms: Int): Double =
    kahanSumSeries(func, epsilon, maxTerms, 0.0D)

  def kahanSumSeries(func: java.util.function.DoubleSupplier, epsilon: Double, maxTerms: Int, initValue: Double): Double = {
    val eps = getEpsilon(epsilon, Epsilon)

    var result: Double = initValue
    var carry: Double = 0.0D
    var nextTerm: Double = func.getAsDouble
    val y: Double = nextTerm - carry
    val t: Double = result + y
    carry = t - result
    carry -= y
    result = t

    var counter: Int = maxTerms - 1

    while (Math.abs(eps * result) < Math.abs(nextTerm) && counter > 0) {
      nextTerm = func.getAsDouble
      val y: Double = nextTerm - carry
      val t: Double = result + y
      carry = t - result
      carry -= y
      result = t
      counter -= 1
    }

    if (counter <= 0) throw new ArithmeticException(String.format(MsgFailedToConverge, maxTerms))

    result
  }

  def getEpsilon(epsilon: Double, minEpsilon: Double) = {
    if (epsilon > minEpsilon) epsilon
    else minEpsilon
  }

  def evaluatePolynomial(c: Array[Double], x: Double): Double = {
    val count: Int = c.length
    var sum: Double = c(count - 1)

    var i: Int = count - 2
    while(i >= 0) {
      sum *= x
      sum += c(i)

      i -= 1
    }

    sum
  }

}
