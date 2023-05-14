package org.apache.commons.numbers.core

class Sum private (initialValue: Double) extends java.util.function.DoubleSupplier, java.util.function.DoubleConsumer {
  private var sum: Double = initialValue
  private var comp: Double = 0.0D

  def add(t: Double): this.type = {
    val newSum = sum + t
    comp += ExtendedPrecision.twoSumLow(sum, t, newSum)
    sum = newSum

    this
  }

  def add(terms: Double*): this.type = {
    terms.foreach(add)
    this
  }

  def addProduct(a: Double, b: Double): this.type = {
    val ab = a * b
    val pLow = ExtendedPrecision.productLow(a, b, ab)

    val newSum = sum + ab
    comp += ExtendedPrecision.twoSumLow(sum, ab, newSum) + pLow
    sum = newSum

    this
  }

  def addProducts(a: Array[Double], b: Array[Double]): this.type = {
    val len = a.length
    if (len != b.length) throw new IllegalArgumentException("Dimension mismatch: " + a.length + " != " + b.length)

    var i: Int = 0
    while(i < len) {
      addProduct(a(i), b(i))
      i += 1
    }

    this
  }

  def add(other: Sum): this.type = {
    val s = other.sum
    val c = other.comp

    this.add(s).add(c)
  }

  override def accept(value: Double): Unit = {
    add(value)
  }

  override def getAsDouble: Double = {
    val hpsum = sum + comp
    if (hpsum.isFinite) hpsum
    else sum
  }

}

/**
 * @since 1.1
 */
object Sum {

  def create(): Sum = new Sum(0.0D)

  def of(a: Double): Sum = new Sum(a)

  def of(values: Double*): Sum = create().add(values: _*)

  def ofProducts(a: Array[Double], b: Array[Double]): Sum = create().addProducts(a, b)

}
