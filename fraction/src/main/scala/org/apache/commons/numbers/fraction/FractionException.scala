package org.apache.commons.numbers.fraction

import scala.annotation.static

class FractionException(message: String) extends ArithmeticException(message) {

  def this(message: String, formatArguments: Any*) =
    this(String.format(message, formatArguments: _*))

}

/**
 * @since 1.1
 */
object FractionException {
  @static private final val serialVersionUID: Long = 201701191744L

  final val ErrorConversionOverflow = "Overflow trying to convert %s to fraction (%d/%d)"
  final val ErrorConversion = "Unable to convert %s to fraction after %d iterations"
  final val ErrorZeroDenominator = "Denominator must be different from 0"
  final val ErrorDivideByZero = "The value to divide by must not be zero"
}
