package org.apache.commons.numbers.gamma

import scala.annotation.static

class GammaException(message: String, formatArguments: Any*) extends IllegalArgumentException(String.format(message, formatArguments: _*))

/**
 * @since 1.1
 */
object GammaException {
  @static private final val serialVersionUID: Long = 20170505L

  final val OutOfRange: String = "Number %s is out of range [%s, %s]"
}
