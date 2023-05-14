package org.apache.commons.numbers.gamma

case class Policy(eps: Double, maxIterations: Int)

/**
 * @since 1.1
 */
object Policy {

  final val Default = Policy(0.00000000000000011102230246251565404236316680908203125, 1000000)

}
