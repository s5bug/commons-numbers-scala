package org.apache.commons.numbers.gamma

import org.junit.{Assert, Ignore, Test}
import org.junit.experimental.runners.Enclosed
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import org.junit.runners.Parameterized.Parameters
import scala.annotation.static
import scala.jdk.javaapi.CollectionConverters

object LogGammaTest {

  @Ignore("Not a test class")
  object TestLogGammaEdgeCases {
    @Parameters
    @static final def parameters(): java.util.Collection[Array[Any]] = CollectionConverters.asJava(List[Array[Any]](
      Array(0.0d),
      Array(-1.0d),
      Array(-2.0d),
      Array(Double.NaN)
    ))
  }

  @RunWith(classOf[Parameterized])
  @static class TestLogGammaEdgeCases(x: Double) {

    @Test def testLogGammaEdgeCases(): Unit = {
      // Our target is NaN so 0.0d delta is OK
      Assert.assertEquals(Double.NaN, LogGamma.value(x), 0.0d)
      Assert.assertEquals(Double.NaN, LogGamma.value(x, null), 0.0d)
    }

  }

  @Ignore("Not a test class")
  object TestLogGamma {
    @Parameters
    @static final def parameters(): java.util.Collection[Array[Any]] = CollectionConverters.asJava(List[Array[Any]](
      Array(0.125d, 2.019418357553796d),
      Array(0.25d, 1.288022524698077d),
      Array(0.375d, .8630739822706475d),
      Array(0.5d, .5723649429247001d),
      Array(0.625d, .3608294954889402d),
      Array(0.75d, .2032809514312954d),
      Array(0.875d, .08585870722533433d),
      Array(0.890625d, .07353860936979656d),
      Array(0.90625d, .06169536624059108d),
      Array(0.921875d, .05031670080005688d),
      Array(0.9375d, 0.0393909017345823d),
      Array(0.953125d, .02890678734595923d),
      Array(0.96875d, .01885367233441289d),
      Array(0.984375d, .009221337197578781d),
      Array(1.0d, 0.0d),
      Array(1.015625d, -0.00881970970573307d),
      Array(1.03125d, -.01724677500176807d),
      Array(1.046875d, -.02528981394675729d),
      Array(1.0625d, -.03295710029357782d),
      Array(1.078125d, -.04025658272400143d),
      Array(1.09375d, -.04719590272716985d),
      Array(1.109375d, -.05378241123619192d),
      Array(1.125d, -.06002318412603958d),
      Array(1.25d, -.09827183642181316d),
      Array(1.375d, -.1177552707410788d),
      Array(1.5d, -.1207822376352452d),
      Array(1.625d, -.1091741337567954d),
      Array(1.75d, -.08440112102048555d),
      Array(1.875d, -0.0476726853991883d),
      Array(1.890625d, -.04229320615532515d),
      Array(1.90625d, -.03674470657266143d),
      Array(1.921875d, -.03102893865389552d),
      Array(1.9375d, -.02514761940298887d),
      Array(1.953125d, -.01910243184040138d),
      Array(1.96875d, -.01289502598016741d),
      Array(1.984375d, -.006527019770560387d),
      Array(2.0d, 0.0d),
      Array(2.015625d, .006684476830232185d),
      Array(2.03125d, .01352488366498562d),
      Array(2.046875d, .02051972208453692d),
      Array(2.0625d, .02766752152285702d),
      Array(2.078125d, 0.0349668385135861d),
      Array(2.09375d, .04241625596251728d),
      Array(2.109375d, .05001438244545164d),
      Array(2.125d, .05775985153034387d),
      Array(2.25d, .1248717148923966d),
      Array(2.375d, .2006984603774558d),
      Array(2.5d, .2846828704729192d),
      Array(2.625d, .3763336820249054d),
      Array(2.75d, .4752146669149371d),
      Array(2.875d, .5809359740231859d),
      Array(2.890625d, .5946142560817441d),
      Array(2.90625d, .6083932548009232d),
      Array(2.921875d, .6222723333588501d),
      Array(2.9375d, .6362508628423761d),
      Array(2.953125d, .6503282221022278d),
      Array(2.96875d, .6645037976116387d),
      Array(2.984375d, 0.678776983328359d),
      Array(3.0d, .6931471805599453d),
      Array(3.015625d, .7076137978322324d),
      Array(3.03125d, .7221762507608962d),
      Array(3.046875d, .7368339619260166d),
      Array(3.0625d, 0.751586360749556d),
      Array(3.078125d, .7664328833756681d),
      Array(3.09375d, .7813729725537568d),
      Array(3.109375d, .7964060775242092d),
      Array(3.125d, 0.811531653906724d),
      Array(3.25d, .9358019311087253d),
      Array(3.375d, 1.06569589786406d),
      Array(3.5d, 1.200973602347074d),
      Array(3.625d, 1.341414578068493d),
      Array(3.75d, 1.486815578593417d),
      Array(3.875d, 1.6369886482725d),
      Array(4.0d, 1.791759469228055d),
      Array(4.125d, 1.950965937095089d),
      Array(4.25d, 2.114456927450371d),
      Array(4.375d, 2.282091222188554d),
      Array(4.5d, 2.453736570842442d),
      Array(4.625d, 2.62926886637513d),
      Array(4.75d, 2.808571418575736d),
      Array(4.875d, 2.99153431107781d),
      Array(5.0d, 3.178053830347946d),
      Array(5.125d, 3.368031956881733d),
      Array(5.25d, 3.561375910386697d),
      Array(5.375d, 3.757997741998131d),
      Array(5.5d, 3.957813967618717d),
      Array(5.625d, 4.160745237339519d),
      Array(5.75d, 4.366716036622286d),
      Array(5.875d, 4.57565441552762d),
      Array(6.0d, 4.787491742782046d),
      Array(6.125d, 5.002162481906205d),
      Array(6.25d, 5.219603986990229d),
      Array(6.375d, 5.439756316011858d),
      Array(6.5d, 5.662562059857142d),
      Array(6.625d, 5.887966185430003d),
      Array(6.75d, 6.115915891431546d),
      Array(6.875d, 6.346360475557843d),
      Array(7.0d, 6.579251212010101d),
      Array(7.125d, 6.814541238336996d),
      Array(7.25d, 7.05218545073854d),
      Array(7.375d, 7.292140407056348d),
      Array(7.5d, 7.534364236758733d),
      Array(7.625d, 7.778816557302289d),
      Array(7.75d, 8.025458396315983d),
      Array(7.875d, 8.274252119110479d),
      Array(8.0d, 8.525161361065415d),
      Array(8.125d, 8.77815096449171d),
      Array(8.25d, 9.033186919605123d),
      Array(8.375d, 9.290236309282232d),
      Array(8.5d, 9.549267257300997d),
      Array(8.625d, 9.810248879795765d),
      Array(8.75d, 10.07315123968124d),
      Array(8.875d, 10.33794530382217d),
      Array(9.0d, 10.60460290274525d),
      Array(9.125d, 10.87309669270751d),
      Array(9.25d, 11.14340011995171d),
      Array(9.375d, 11.41548738699336d),
      Array(9.5d, 11.68933342079727d),
      Array(9.625d, 11.96491384271319d),
      Array(9.75d, 12.24220494005076d),
      Array(9.875d, 12.52118363918365d),
      Array(10.0d, 12.80182748008147d),
      Array(0.8d, .1520596783998376d),
      Array(100.0d, 359.1342053695754d),
      Array(1000.0d, 5905.220423209181d),
      Array(10000.0d, 82099.71749644238d),
      Array(100000.0d, 1051287.708973657d),
      Array(1000000.0d, 1.2815504569147612e+7d),
      Array(10000000.0d, 1.511809493694739e+8d),
      Array(1.0e+8d, 1.7420680661038346e+9d),
      Array(1.0e+9d, 1.972326582750371e+10d),
      Array(1.0e+10d, 2.202585092888106e+11d),
    ))
  }

  @RunWith(classOf[Parameterized])
  @static class TestLogGamma(x: Double, expected: Double) {
    @Test def testLogGamma(): Unit = {
      val actual = LogGamma.value(x)
      Assert.assertEquals(expected, actual, 3 * Math.ulp(expected))
      val sign = Array(0)
      // Specifying a sign array should produce the EXACT same result
      Assert.assertEquals(actual, LogGamma.value(x, sign), 0.0d)
      Assert.assertEquals(1, sign(0))
    }
  }

  @Ignore("Not a test class")
  object TestLogGammaSign {
    @Parameters
    @static final def parameters(): java.util.Collection[Array[Any]] = CollectionConverters.asJava(List[Array[Any]](
      Array[Any](1d, 0.0d, 1: Int),
      Array[Any](1d, 0.0d, 1: Int),
      Array[Any](-0.125d, 2.1653002489051702517540619481440174064962195287626d, -1: Int),
      Array[Any](-3.125d, 0.1543111276840418242676072830970532952413339012367d, 1: Int),
      Array[Any](-52.0009765625d, -149.43323093420259741100038126078721302600128285894d, -1: Int),
      // Overflow close to negative poles creates signed infinity
      Array[Any](-171.99999999999997d, Double.PositiveInfinity, 1: Int),
      Array[Any](-172.00000000000003d, Double.NegativeInfinity, -1: Int),
      Array[Any](-172.99999999999997d, Double.NegativeInfinity, -1: Int),
      Array[Any](-173.00000000000003d, Double.PositiveInfinity, 1: Int),

    ))
  }

  @RunWith(classOf[Parameterized])
  @static class TestLogGammaSign(x: Double, expected: Double, expectedSign: Int) {
    @Test def testLogGammaSign(): Unit = {
      val actual = LogGamma.value(x)
      Assert.assertEquals(expected, actual, 3 * Math.ulp(expected))
      Assert.assertEquals(actual, LogGamma.value(x, null), 0.0d)
      val signEmpty = Array[Int]()
      Assert.assertEquals(actual, LogGamma.value(x, signEmpty), 0.0d)
      val sign = Array(0)
      Assert.assertEquals(actual, LogGamma.value(x, sign), 0.0d)
    }
  }

}

@RunWith(classOf[Enclosed])
class LogGammaTest
