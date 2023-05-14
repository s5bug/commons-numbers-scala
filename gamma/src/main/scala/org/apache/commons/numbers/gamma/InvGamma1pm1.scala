package org.apache.commons.numbers.gamma

/**
 * @since 1.1
 */
object InvGamma1pm1 {

  final val InvGamma1pM1A0 = 0.611609510448141581788E-08D
  final val InvGamma1pM1A1 = 0.624730830116465516210E-08D
  final val InvGamma1pM1B1 = 0.203610414066806987300E+00D
  final val InvGamma1pM1B2 = 0.266205348428949217746E-01D
  final val InvGamma1pM1B3 = 0.493944979382446875238E-03D
  final val InvGamma1pM1B4 = -0.851419432440314906588E-05D
  final val InvGamma1pM1B5 = -0.643045481779353022248E-05D
  final val InvGamma1pM1B6 = 0.992641840672773722196E-06D
  final val InvGamma1pM1B7 = -0.607761895722825260739E-07D
  final val InvGamma1pM1B8 = 0.195755836614639731882E-09D
  final val InvGamma1pM1P0 = 0.6116095104481415817861E-08D
  final val InvGamma1pM1P1 = 0.6871674113067198736152E-08D
  final val InvGamma1pM1P2 = 0.6820161668496170657918E-09D
  final val InvGamma1pM1P3 = 0.4686843322948848031080E-10D
  final val InvGamma1pM1P4 = 0.1572833027710446286995E-11D
  final val InvGamma1pM1P5 = -0.1249441572276366213222E-12D
  final val InvGamma1pM1P6 = 0.4343529937408594255178E-14D
  final val InvGamma1pM1Q1 = 0.3056961078365221025009E+00D
  final val InvGamma1pM1Q2 = 0.5464213086042296536016E-01D
  final val InvGamma1pM1Q3 = 0.4956830093825887312020E-02D
  final val InvGamma1pM1Q4 = 0.2692369466186361192876E-03D
  final val InvGamma1pM1C = -0.422784335098467139393487909917598E+00D
  final val InvGamma1pM1C0 = 0.577215664901532860606512090082402E+00D
  final val InvGamma1pM1C1 = -0.655878071520253881077019515145390E+00D
  final val InvGamma1pM1C2 = -0.420026350340952355290039348754298E-01D
  final val InvGamma1pM1C3 = 0.166538611382291489501700795102105E+00D
  final val InvGamma1pM1C4 = -0.421977345555443367482083012891874E-01D
  final val InvGamma1pM1C5 = -0.962197152787697356211492167234820E-02D
  final val InvGamma1pM1C6 = 0.721894324666309954239501034044657E-02D
  final val InvGamma1pM1C7 = -0.116516759185906511211397108401839E-02D
  final val InvGamma1pM1C8 = -0.215241674114950972815729963053648E-03D
  final val InvGamma1pM1C9 = 0.128050282388116186153198626328164E-03D
  final val InvGamma1pM1C10 = -0.201348547807882386556893914210218E-04D
  final val InvGamma1pM1C11 = -0.125049348214267065734535947383309E-05D
  final val InvGamma1pM1C12 = 0.113302723198169588237412962033074E-05D
  final val InvGamma1pM1C13 = -0.205633841697760710345015413002057E-06D

  def value(x: Double): Double = {
    if(x < -0.5 || x > 0.5) {
      throw new GammaException(GammaException.OutOfRange, x, -0.5, 1.5)
    }

    val t: Double =
      if (x <= 0.5) x
      else (x - 0.5) - 0.5

    if(t < 0.0d) {
      val a: Double = InvGamma1pM1A0 + t * InvGamma1pM1A1

      var b: Double = InvGamma1pM1B8
      b = InvGamma1pM1B7 + t * b
      b = InvGamma1pM1B6 + t * b
      b = InvGamma1pM1B5 + t * b
      b = InvGamma1pM1B4 + t * b
      b = InvGamma1pM1B3 + t * b
      b = InvGamma1pM1B2 + t * b
      b = InvGamma1pM1B1 + t * b

      var c: Double = InvGamma1pM1C13 + t * (a / b)
      c = InvGamma1pM1C12 + t * c
      c = InvGamma1pM1C11 + t * c
      c = InvGamma1pM1C10 + t * c
      c = InvGamma1pM1C9 + t * c
      c = InvGamma1pM1C8 + t * c
      c = InvGamma1pM1C7 + t * c
      c = InvGamma1pM1C6 + t * c
      c = InvGamma1pM1C5 + t * c
      c = InvGamma1pM1C4 + t * c
      c = InvGamma1pM1C3 + t * c
      c = InvGamma1pM1C2 + t * c
      c = InvGamma1pM1C1 + t * c
      c = InvGamma1pM1C + t * c

      if (x > 0.5) {
        t * c / x
      } else {
        x * ((c + 0.5) + 0.5)
      }
    } else {
      var p: Double = InvGamma1pM1P6
      p = InvGamma1pM1P5 + t * p
      p = InvGamma1pM1P4 + t * p
      p = InvGamma1pM1P3 + t * p
      p = InvGamma1pM1P2 + t * p
      p = InvGamma1pM1P1 + t * p
      p = InvGamma1pM1P0 + t * p

      var q: Double = InvGamma1pM1Q4
      q = InvGamma1pM1Q3 + t * q
      q = InvGamma1pM1Q2 + t * q
      q = InvGamma1pM1Q1 + t * q
      q = 1.0 + t * q

      var c: Double = InvGamma1pM1C13 + (p / q) * t
      c = InvGamma1pM1C12 + t * c
      c = InvGamma1pM1C11 + t * c
      c = InvGamma1pM1C10 + t * c
      c = InvGamma1pM1C9 + t * c
      c = InvGamma1pM1C8 + t * c
      c = InvGamma1pM1C7 + t * c
      c = InvGamma1pM1C6 + t * c
      c = InvGamma1pM1C5 + t * c
      c = InvGamma1pM1C4 + t * c
      c = InvGamma1pM1C3 + t * c
      c = InvGamma1pM1C2 + t * c
      c = InvGamma1pM1C1 + t * c
      c = InvGamma1pM1C0 + t * c

      if(x > 0.5) {
        (t / x) * ((c - 0.5) - 0.5)
      } else {
        x * c
      }
    }
  }

}
