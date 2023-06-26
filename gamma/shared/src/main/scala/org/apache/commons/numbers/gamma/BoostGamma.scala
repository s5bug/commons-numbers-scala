package org.apache.commons.numbers.gamma

import org.apache.commons.numbers.core.Sum
import org.apache.commons.numbers.fraction.GeneralizedContinuedFraction
import org.apache.commons.numbers.fraction.GeneralizedContinuedFraction.Coefficient
import scala.annotation.{static, switch}

/**
 * @since 1.1
 */
object BoostGamma {

  final val Epsilon = 0.0000000000000002220446049250313080847263336181640625D

  final val RootEpsilon = 0.1490116119384765625E-7D

  final val LogMaxValue = 709

  final val LogMinValue = -708

  final val MaxFactorial = 170

  final val MaxGammaZ = MaxFactorial + 1

  final val LogRootTwoPi = 0.9189385332046727417803297D

  final val LogPi = 1.144729885849400174143427D

  final val Euler = 0.5772156649015328606065120900824024310D

  final val LanczosThreshold = 20

  final val TwoPow53 = 9007199254740992.0D

  final val Factorial: IArray[Double] = IArray(
    1.0D,
    1.0D,
    2.0D,
    6.0D,
    24.0D,
    120.0D,
    720.0D,
    5040.0D,
    40320.0D,
    362880.0D,
    3628800.0D,
    39916800.0D,
    479001600.0D,
    6227020800.0D,
    87178291200.0D,
    1307674368000.0D,
    20922789888000.0D,
    355687428096000.0D,
    6402373705728000.0D,
    121645100408832000.0D,
    0.243290200817664e19D,
    0.5109094217170944e20D,
    0.112400072777760768e22D,
    0.2585201673888497664e23D,
    0.62044840173323943936e24D,
    0.15511210043330985984e26D,
    0.403291461126605635584e27D,
    0.10888869450418352160768e29D,
    0.304888344611713860501504e30D,
    0.8841761993739701954543616e31D,
    0.26525285981219105863630848e33D,
    0.822283865417792281772556288e34D,
    0.26313083693369353016721801216e36D,
    0.868331761881188649551819440128e37D,
    0.29523279903960414084761860964352e39D,
    0.103331479663861449296666513375232e41D,
    0.3719933267899012174679994481508352e42D,
    0.137637530912263450463159795815809024e44D,
    0.5230226174666011117600072241000742912e45D,
    0.203978820811974433586402817399028973568e47D,
    0.815915283247897734345611269596115894272e48D,
    0.3345252661316380710817006205344075166515e50D,
    0.1405006117752879898543142606244511569936e52D,
    0.6041526306337383563735513206851399750726e53D,
    0.265827157478844876804362581101461589032e55D,
    0.1196222208654801945619631614956577150644e57D,
    0.5502622159812088949850305428800254892962e58D,
    0.2586232415111681806429643551536119799692e60D,
    0.1241391559253607267086228904737337503852e62D,
    0.6082818640342675608722521633212953768876e63D,
    0.3041409320171337804361260816606476884438e65D,
    0.1551118753287382280224243016469303211063e67D,
    0.8065817517094387857166063685640376697529e68D,
    0.427488328406002556429801375338939964969e70D,
    0.2308436973392413804720927426830275810833e72D,
    0.1269640335365827592596510084756651695958e74D,
    0.7109985878048634518540456474637249497365e75D,
    0.4052691950487721675568060190543232213498e77D,
    0.2350561331282878571829474910515074683829e79D,
    0.1386831185456898357379390197203894063459e81D,
    0.8320987112741390144276341183223364380754e82D,
    0.507580213877224798800856812176625227226e84D,
    0.3146997326038793752565312235495076408801e86D,
    0.1982608315404440064116146708361898137545e88D,
    0.1268869321858841641034333893351614808029e90D,
    0.8247650592082470666723170306785496252186e91D,
    0.5443449390774430640037292402478427526443e93D,
    0.3647111091818868528824985909660546442717e95D,
    0.2480035542436830599600990418569171581047e97D,
    0.1711224524281413113724683388812728390923e99D,
    0.1197857166996989179607278372168909873646e101D,
    0.8504785885678623175211676442399260102886e102D,
    0.6123445837688608686152407038527467274078e104D,
    0.4470115461512684340891257138125051110077e106D,
    0.3307885441519386412259530282212537821457e108D,
    0.2480914081139539809194647711659403366093e110D,
    0.188549470166605025498793226086114655823e112D,
    0.1451830920282858696340707840863082849837e114D,
    0.1132428117820629783145752115873204622873e116D,
    0.8946182130782975286851441715398316520698e117D,
    0.7156945704626380229481153372318653216558e119D,
    0.5797126020747367985879734231578109105412e121D,
    0.4753643337012841748421382069894049466438e123D,
    0.3945523969720658651189747118012061057144e125D,
    0.3314240134565353266999387579130131288001e127D,
    0.2817104114380550276949479442260611594801e129D,
    0.2422709538367273238176552320344125971528e131D,
    0.210775729837952771721360051869938959523e133D,
    0.1854826422573984391147968456455462843802e135D,
    0.1650795516090846108121691926245361930984e137D,
    0.1485715964481761497309522733620825737886e139D,
    0.1352001527678402962551665687594951421476e141D,
    0.1243841405464130725547532432587355307758e143D,
    0.1156772507081641574759205162306240436215e145D,
    0.1087366156656743080273652852567866010042e147D,
    0.103299784882390592625997020993947270954e149D,
    0.9916779348709496892095714015418938011582e150D,
    0.9619275968248211985332842594956369871234e152D,
    0.942689044888324774562618574305724247381e154D,
    0.9332621544394415268169923885626670049072e156D,
    0.9332621544394415268169923885626670049072e158D,
    0.9425947759838359420851623124482936749562e160D,
    0.9614466715035126609268655586972595484554e162D,
    0.990290071648618040754671525458177334909e164D,
    0.1029901674514562762384858386476504428305e167D,
    0.1081396758240290900504101305800329649721e169D,
    0.1146280563734708354534347384148349428704e171D,
    0.1226520203196137939351751701038733888713e173D,
    0.132464181945182897449989183712183259981e175D,
    0.1443859583202493582204882102462797533793e177D,
    0.1588245541522742940425370312709077287172e179D,
    0.1762952551090244663872161047107075788761e181D,
    0.1974506857221074023536820372759924883413e183D,
    0.2231192748659813646596607021218715118256e185D,
    0.2543559733472187557120132004189335234812e187D,
    0.2925093693493015690688151804817735520034e189D,
    0.339310868445189820119825609358857320324e191D,
    0.396993716080872089540195962949863064779e193D,
    0.4684525849754290656574312362808384164393e195D,
    0.5574585761207605881323431711741977155627e197D,
    0.6689502913449127057588118054090372586753e199D,
    0.8094298525273443739681622845449350829971e201D,
    0.9875044200833601362411579871448208012564e203D,
    0.1214630436702532967576624324188129585545e206D,
    0.1506141741511140879795014161993280686076e208D,
    0.1882677176888926099743767702491600857595e210D,
    0.237217324288004688567714730513941708057e212D,
    0.3012660018457659544809977077527059692324e214D,
    0.3856204823625804217356770659234636406175e216D,
    0.4974504222477287440390234150412680963966e218D,
    0.6466855489220473672507304395536485253155e220D,
    0.8471580690878820510984568758152795681634e222D,
    0.1118248651196004307449963076076169029976e225D,
    0.1487270706090685728908450891181304809868e227D,
    0.1992942746161518876737324194182948445223e229D,
    0.269047270731805048359538766214698040105e231D,
    0.3659042881952548657689727220519893345429e233D,
    0.5012888748274991661034926292112253883237e235D,
    0.6917786472619488492228198283114910358867e237D,
    0.9615723196941089004197195613529725398826e239D,
    0.1346201247571752460587607385894161555836e242D,
    0.1898143759076170969428526414110767793728e244D,
    0.2695364137888162776588507508037290267094e246D,
    0.3854370717180072770521565736493325081944e248D,
    0.5550293832739304789551054660550388118e250D,
    0.80479260574719919448490292577980627711e252D,
    0.1174997204390910823947958271638517164581e255D,
    0.1727245890454638911203498659308620231933e257D,
    0.2556323917872865588581178015776757943262e259D,
    0.380892263763056972698595524350736933546e261D,
    0.571338395644585459047893286526105400319e263D,
    0.8627209774233240431623188626544191544816e265D,
    0.1311335885683452545606724671234717114812e268D,
    0.2006343905095682394778288746989117185662e270D,
    0.308976961384735088795856467036324046592e272D,
    0.4789142901463393876335775239063022722176e274D,
    0.7471062926282894447083809372938315446595e276D,
    0.1172956879426414428192158071551315525115e279D,
    0.1853271869493734796543609753051078529682e281D,
    0.2946702272495038326504339507351214862195e283D,
    0.4714723635992061322406943211761943779512e285D,
    0.7590705053947218729075178570936729485014e287D,
    0.1229694218739449434110178928491750176572e290D,
    0.2004401576545302577599591653441552787813e292D,
    0.3287218585534296227263330311644146572013e294D,
    0.5423910666131588774984495014212841843822e296D,
    0.9003691705778437366474261723593317460744e298D,
    0.1503616514864999040201201707840084015944e301D,
    0.2526075744973198387538018869171341146786e303D,
    0.4269068009004705274939251888899566538069e305D,
    0.7257415615307998967396728211129263114717e307D,
  )

  object Lanczos {

    final val G = 6.024680040776729583740234375D

    final val GMH = 5.524680040776729583740234375D

    final val Denom: IArray[Int] = IArray[Int](
      0,
      39916800,
      120543840,
      150917976,
      105258076,
      45995730,
      13339535,
      2637558,
      357423,
      32670,
      1925,
      66,
      1
    )

    def lanczosSum(z: Double): Double = {
      val num: IArray[Double] = IArray(
        23531376880.41075968857200767445163675473D,
        42919803642.64909876895789904700198885093D,
        35711959237.35566804944018545154716670596D,
        17921034426.03720969991975575445893111267D,
        6039542586.35202800506429164430729792107D,
        1439720407.311721673663223072794912393972D,
        248874557.8620541565114603864132294232163D,
        31426415.58540019438061423162831820536287D,
        2876370.628935372441225409051620849613599D,
        186056.2653952234950402949897160456992822D,
        8071.672002365816210638002902272250613822D,
        210.8242777515793458725097339207133627117D,
        2.506628274631000270164908177133837338626D
      )
      evaluateRational(num, Denom, z)
    }

    def lanczosSumExpGScaled(z: Double): Double = {
      val num: IArray[Double] = IArray(
        56906521.91347156388090791033559122686859D,
        103794043.1163445451906271053616070238554D,
        86363131.28813859145546927288977868422342D,
        43338889.32467613834773723740590533316085D,
        14605578.08768506808414169982791359218571D,
        3481712.15498064590882071018964774556468D,
        601859.6171681098786670226533699352302507D,
        75999.29304014542649875303443598909137092D,
        6955.999602515376140356310115515198987526D,
        449.9445569063168119446858607650988409623D,
        19.51992788247617482847860966235652136208D,
        0.5098416655656676188125178644804694509993D,
        0.006061842346248906525783753964555936883222D
      )
      evaluateRational(num, Denom, z)
    }

    private def evaluateRational(a: IArray[Double], b: IArray[Int], x: Double): Double = {
      if (x <= 1) {
        val x2 = x * x
        var t0 = a(12) * x2 + a(10)
        var t1 = a(11) * x2 + a(9)
        var t2 = b(12) * x2 + b(10)
        var t3 = b(11) * x2 + b(9)
        t0 *= x2
        t1 *= x2
        t2 *= x2
        t3 *= x2
        t0 += a(8)
        t1 += a(7)
        t2 += b(8)
        t3 += b(7)
        t0 *= x2
        t1 *= x2
        t2 *= x2
        t3 *= x2
        t0 += a(6)
        t1 += a(5)
        t2 += b(6)
        t3 += b(5)
        t0 *= x2
        t1 *= x2
        t2 *= x2
        t3 *= x2
        t0 += a(4)
        t1 += a(3)
        t2 += b(4)
        t3 += b(3)
        t0 *= x2
        t1 *= x2
        t2 *= x2
        t3 *= x2
        t0 += a(2)
        t1 += a(1)
        t2 += b(2)
        t3 += b(1)
        t0 *= x2
        t2 *= x2
        t0 += a(0)
        t2 += b(0)
        t1 *= x
        t3 *= x
        (t0 + t1) / (t2 + t3)
      } else {
        val z = 1 / x
        val z2 = 1 / (x * x)
        var t0 = a(0) * z2 + a(2)
        var t1 = a(1) * z2 + a(3)
        var t2 = b(0) * z2 + b(2)
        var t3 = b(1) * z2 + b(3)
        t0 *= z2
        t1 *= z2
        t2 *= z2
        t3 *= z2
        t0 += a(4)
        t1 += a(5)
        t2 += b(4)
        t3 += b(5)
        t0 *= z2
        t1 *= z2
        t2 *= z2
        t3 *= z2
        t0 += a(6)
        t1 += a(7)
        t2 += b(6)
        t3 += b(7)
        t0 *= z2
        t1 *= z2
        t2 *= z2
        t3 *= z2
        t0 += a(8)
        t1 += a(9)
        t2 += b(8)
        t3 += b(9)
        t0 *= z2
        t1 *= z2
        t2 *= z2
        t3 *= z2
        t0 += a(10)
        t1 += a(11)
        t2 += b(10)
        t3 += b(11)
        t0 *= z2
        t2 *= z2
        t0 += a(12)
        t2 += b(12)
        t1 *= z
        t3 *= z
        (t0 + t1) / (t2 + t3)
      }
    }

  }

  def uncheckedFactorial(n: Int): Double =
    Factorial(n)

  def tgamma(z: Double): Double = {
    if(Math.rint(z) == z) {
      if(z <= 0.0D) {
        Double.NaN
      } else if(z <= MaxGammaZ) {
        Factorial(z.toInt - 1)
      } else {
        Double.PositiveInfinity
      }
    } else if(Math.abs(z) <= LanczosThreshold) {
      if(z >= 1) {
        var prod: Double = 1.0D
        var t: Double = z
        while(t > 2.5D) {
          t -= 1.0D
          prod *= t
        }
        prod / (1 + InvGamma1pm1.value(t - 1))
      } else {
        var prod: Double = z
        var t: Double = z
        while (t < -0.5D) {
          t += 1.0D
          prod *= t
        }
        1 / (prod * (1 + InvGamma1pm1.value(t)))
      }
    } else {
      if(z < 0) {
        (-Math.PI) / (sinpx(z) * tgamma(-z))
      } else if(z > MaxGammaZ + 1) {
        Double.PositiveInfinity
      } else {
        var result: Double = Lanczos.lanczosSum(z)
        val zgh: Double = z + Lanczos.GMH
        val lzgh: Double = Math.log(zgh)
        if(z * lzgh > LogMaxValue) {
          val hp: Double = Math.pow(zgh, (z / 2.0D) - 0.25D)
          result *= hp / Math.exp(zgh)
          result *= hp
        } else {
          result *= Math.pow(zgh, z - 0.5) / Math.exp(zgh)
        }
        result
      }
    }
  }

  /**
   * @param px assumed to be negative
   */
  def sinpx(px: Double): Double = {
    var sign: Int = 1
    val x: Double = -px
    var fl: Double = Math.floor(x)
    var dist: Double = if(isOdd(fl)) {
      fl += 1
      sign = -sign
      fl - x
    } else {
      x - fl
    }
    if(dist > 0.5D) {
      dist = 1.0D - dist
    }
    val result: Double = Math.sin(dist * Math.PI)
    sign * x * result
  }

  /**
   * @param v assumed to be positive and an integer
   */
  def isOdd(v: Double): Boolean = {
    ((-v).toLong & 0x1) == 1
  }

  def lgamma(z: Double): Double =
    lgamma(z, null)

  def lgamma(z: Double, sign: Array[Int]): Double = {
    var result: Double = 0.0D
    var sresult: Int = 1
    if(z <= -RootEpsilon) {
      if(Math.rint(z) == z) return Double.NaN

      var t: Double = sinpx(z)
      val nz: Double = -z
      if(t < 0.0D) {
        t = -t
      } else {
        sresult = -sresult
      }

      result = Sum.of(-lgamma(nz)).add(-Math.log(t)).add(LogPi).getAsDouble
    } else if(z < RootEpsilon) {
      if(z == 0.0D) return Double.NaN

      if((4.0D * Math.abs(z)) < Epsilon) {
        result = -Math.log(Math.abs(z))
      } else {
        result = Math.log(Math.abs((1.0 / z) - Euler))
      }

      if(z < 0.0D) {
        sresult = -1
      }
    } else if(z < 15.0D) {
      result = lgammaSmall(z, z - 1, z - 2)
    } else if(z < 100.0D) {
      result = Math.log(tgamma(z))
    } else {
      val zgh = z + Lanczos.GMH
      result = Math.log(zgh) - 1
      result *= z - 0.5f
      if((result * Epsilon) < 20.0D) {
        result += Math.log(Lanczos.lanczosSumExpGScaled(z))
      }
    }

    if(nonZeroLength(sign)) {
      sign(0) = sresult
    }
    result
  }

  def lgammaSmall(pz: Double, pzm1: Double, pzm2: Double): Double = {
    val result = Sum.create()

    if ((pzm1 == 0.0D) || (pzm2 == 0.0D)) {
      return 0.0D
    } else if (pz > 2.0D) {
      var z: Double = pz
      var zm2: Double = pzm2
      while (z >= 3) {
        z -= 1.0D
        result.add(Math.log(z))
      }
      zm2 = z - 2.0D

      val Y: Float = 0.158963680267333984375F
      var P: Double = 0.0D
      P = -0.324588649825948492091e-4D
      P = -0.541009869215204396339e-3D + P * zm2
      P = -0.259453563205438108893e-3D + P * zm2
      P = 0.172491608709613993966e-1D + P * zm2
      P = 0.494103151567532234274e-1D + P * zm2
      P = 0.25126649619989678683e-1D + P * zm2
      P = -0.180355685678449379109e-1D + P * zm2
      var Q: Double = 0.0D
      Q = -0.223352763208617092964e-6D
      Q = 0.224936291922115757597e-3D + Q * zm2
      Q = 0.82130967464889339326e-2D + Q * zm2
      Q = 0.988504251128010129477e-1D + Q * zm2
      Q = 0.541391432071720958364e0D + Q * zm2
      Q = 0.148019669424231326694e1D + Q * zm2
      Q = 0.196202987197795200688e1D + Q * zm2
      Q = 0.1e1D + Q * zm2
      val r: Double = zm2 * (z + 1)
      val R: Double = P / Q
      result.addProduct(r, Y).addProduct(r, R)
    }
    else {
      var z: Double = pz
      var zm1: Double = pzm1
      var zm2: Double = pzm2
      if (z < 1.0D) {
        result.add(-Math.log(z))
        zm2 = zm1
        zm1 = z
        z += 1
      }
      if (z <= 1.5D) {
        val Y: Float = 0.52815341949462890625F
        var P: Double = 0.0D
        P = -0.100346687696279557415e-2D
        P = -0.240149820648571559892e-1D + P * zm1
        P = -0.158413586390692192217e0D + P * zm1
        P = -0.406567124211938417342e0D + P * zm1
        P = -0.414983358359495381969e0D + P * zm1
        P = -0.969117530159521214579e-1D + P * zm1
        P = 0.490622454069039543534e-1D + P * zm1
        var Q: Double = 0.0D
        Q = 0.195768102601107189171e-2D
        Q = 0.577039722690451849648e-1D + Q * zm1
        Q = 0.507137738614363510846e0D + Q * zm1
        Q = 0.191415588274426679201e1D + Q * zm1
        Q = 0.348739585360723852576e1D + Q * zm1
        Q = 0.302349829846463038743e1D + Q * zm1
        Q = 0.1e1D + Q * zm1
        val r: Double = P / Q
        val prefix: Double = zm1 * zm2
        result.addProduct(prefix, Y).addProduct(prefix, r)
      }
      else {
        val Y: Float = 0.452017307281494140625F
        val mzm2: Double = -zm2
        var P: Double = 0.0D
        P = 0.431171342679297331241e-3D
        P = -0.850535976868336437746e-2D + P * mzm2
        P = 0.542809694055053558157e-1D + P * mzm2
        P = -0.142440390738631274135e0D + P * mzm2
        P = 0.144216267757192309184e0D + P * mzm2
        P = -0.292329721830270012337e-1D + P * mzm2
        var Q: Double = 0.0D
        Q = -0.827193521891290553639e-6D
        Q = -0.100666795539143372762e-2D + Q * mzm2
        Q = 0.25582797155975869989e-1D + Q * mzm2
        Q = -0.220095151814995745555e0D + Q * mzm2
        Q = 0.846973248876495016101e0D + Q * mzm2
        Q = -0.150169356054485044494e1D + Q * mzm2
        Q = 0.1e1 + Q * mzm2
        val r: Double = zm2 * zm1
        val R: Double = P / Q
        result.addProduct(r, Y).addProduct(r, R)
      }
    }
    result.getAsDouble
  }

  def tgamma1pm1(dz: Double): Double = {
    if(dz < 0.0D) {
      if (dz < -0.5D) {
        tgamma(1 + dz) - 1
      } else {
        Math.expm1(-Math.log1p(dz) + lgammaSmall(dz + 2, dz + 1, dz))
      }
    } else {
      if(dz < 2) {
        Math.expm1(lgammaSmall(dz + 1, dz, dz - 1))
      } else {
        tgamma(1 + dz) - 1
      }
    }
  }

  def tgamma(a: Double, x: Double): Double =
    gammaIncompleteImp(a, x, false, true, Policy.Default)

  def tgamma(a: Double, x: Double, policy: Policy): Double =
    gammaIncompleteImp(a, x, false, true, policy)

  def tgammaLower(a: Double, x: Double): Double =
    gammaIncompleteImp(a, x, false, false, Policy.Default)

  def tgammaLower(a: Double, x: Double, policy: Policy): Double =
    gammaIncompleteImp(a, x, false, false, policy)

  def gammaQ(a: Double, x: Double): Double =
    gammaIncompleteImp(a, x, true, true, Policy.Default)

  def gammaQ(a: Double, x: Double, policy: Policy): Double =
    gammaIncompleteImp(a, x, true, true, policy)

  def gammaP(a: Double, x: Double): Double =
    gammaIncompleteImp(a, x, true, false, Policy.Default)

  def gammaP(a: Double, x: Double, policy: Policy): Double =
    gammaIncompleteImp(a, x, true, false, policy)

  def gammaPDerivative(a: Double, x: Double): Double = {
    if (a.isNaN || x.isNaN || a <= 0 || x < 0) return Double.NaN

    if (x == 0.0D) {
      if (a > 1.0D) return 0.0D
      if (a == 1.0D) return 1.0D
      else return Double.PositiveInfinity
    }

    var f1: Double = regularisedGammaPrefix(a, x)
    if (f1 == 0) {
      f1 = a * Math.log(x) - x - lgamma(a) - Math.log(x)
      f1 = Math.exp(f1)
    } else {
      f1 /= x
    }
    f1
  }

  def gammaIncompleteImp(a: Double, x: Double, normalised: Boolean, pinvert: Boolean, pol: Policy): Double = {
    if (a.isNaN || x.isNaN || a <= 0 || x < 0) return Double.NaN

    var invert = pinvert
    var result: Double = 0.0D

    if (a >= MaxFactorial && !normalised) {
      if (invert && (a * 4 < x)) {
        result = a * Math.log(x) - x
        result += Math.log(upperGammaFraction(a, x, pol))
      } else if (!invert && (a > 4 * x)) {
        result = a * Math.log(x) - x
        result += Math.log(lowerGammaSeries(a, x, 0, pol) / a)
      } else {
        result = gammaIncompleteImp(a, x, true, invert, pol)
        if (result == 0) {
          if (invert) {
            result = 1 + 1 / (12 * a) + 1 / (288 * a * a)
            result = Math.log(result) - a + (a - 0.5f) * Math.log(a) + LogRootTwoPi
          } else {
            result = a * Math.log(x) - x
            result += Math.log(lowerGammaSeries(a, x, 0, pol) / a)
          }
        } else {
          result = Math.log(result) + lgamma(a)
        }
      }
      Math.exp(result)
    } else {
      var isInt: Boolean = false
      var isHalfInt: Boolean = false
      val isSmallA = (a < 30) && (a <= x + 1) && (-x > LogMinValue)
      if (isSmallA) {
        val fa = Math.floor(a)
        isInt = fa == a
        isHalfInt = !isInt && (Math.abs(fa - a) eq 0.5f)
      } else {
        isInt = false
        isHalfInt = false
      }

      val evalMethod: Int = if (isInt && (x > 0.6)) {
        invert = !invert
        0
      } else if (isHalfInt && (x > 0.2)) {
        invert = !invert
        1
      } else if ((x < RootEpsilon) && (a > 1)) {
        6
      } else if ((x > 1000) && (a < x * 0.75f)) {
        invert = !invert
        7
      } else if (x < 0.5) {
        if (-0.4 / Math.log(x) < a) {
          2
        } else {
          3
        }
      } else if (x < 1.1) {
        if (x * 0.75f < a) {
          2
        } else {
          3
        }
      } else {
        var useTemme = false
        if (normalised && (a > 20)) {
          val sigma = Math.abs((x - a) / a)
          if (a > 200) {
            if (20 / a > sigma * sigma) useTemme = true
          } else {
            if (sigma < 0.4) useTemme = true
          }
        }
        if (useTemme) {
          5
        } else {
          if (x - (1 / (3 * x)) < a) {
            2
          } else {
            invert = !invert
            4
          }
        }
      }

      (evalMethod: @switch) match {
        case 0 =>
          result = finiteGammaQ(a, x)
          if (!normalised) result *= tgamma(a)

        case 1 =>
          result = finiteHalfGammaQ(a, x)
          if (!normalised) result *= tgamma(a)

        case 2 =>
          result =
            if (normalised) regularisedGammaPrefix(a, x)
            else fullIgammaPrefix(a, x)

          if (result != 0) {
            var initValue: Double = 0.0
            var optimisedInvert: Boolean = false
            if (invert) {
              initValue =
                if (normalised) 1
                else tgamma(a)

              if (normalised || (result >= 1) || (Double.MaxValue * result > initValue)) {
                initValue /= result
                if (normalised || (a < 1) || (Double.MaxValue / a > initValue)) {
                  initValue *= -a
                  optimisedInvert = true
                } else {
                  initValue = 0
                }
              } else {
                initValue = 0
              }
            }
            result *= lowerGammaSeries(a, x, initValue, pol) / a
            if (optimisedInvert) {
              invert = false
              result = -result
            }
          }

        case 3 =>
          invert = !invert
          val g: Array[Double] = Array(0.0D)
          result = tgammaSmallUpperPart(a, x, pol, g, invert)
          invert = false
          if (normalised) {
            if (g(0) == Double.PositiveInfinity) {
              result = Math.exp(Math.log(result) - lgamma(a))
            }
            else result /= g(0)
          }

        case 4 =>
          result = if (normalised) regularisedGammaPrefix(a, x)
          else fullIgammaPrefix(a, x)
          if (result != 0) result *= upperGammaFraction(a, x, pol)

        case 5 =>
          result = igammaTemmeLarge(a, x)
          if (x >= a) invert = !invert

        case 6 =>
          if (normalised) {
            result = Math.pow(x, a) / tgamma(a + 1)
          } else {
            result = Math.pow(x, a) / a
          }
          result *= 1 - a * x / (a + 1)

        case 7 =>
        case _ =>
          result =
            if (normalised) regularisedGammaPrefix(a, x)
            else fullIgammaPrefix(a, x)

          result /= x
          if (result != 0)
            result *= incompleteTgammaLargeX(a, x, pol)
      }

      if (normalised && (result > 1)) result = 1
      if (invert) {
        val gam = if (normalised) 1
        else tgamma(a)
        result = gam - result
      }

      result
    }
  }

  def upperGammaFraction(a: Double, z: Double, pol: Policy): Double = {
    val eps: Double = pol.eps
    val maxIterations: Int = pol.maxIterations

    val zma1: Double = z - a + 1.0D

    val gen = new java.util.function.Supplier[Coefficient] {
      private var k: Int = 0

      override def get(): Coefficient = {
        k += 1
        Coefficient(k * (a - k), zma1 + 2.0 * k)
      }
    }

    1 / GeneralizedContinuedFraction.value(zma1, gen, eps, maxIterations)
  }

  def finiteGammaQ(a: Double, x: Double): Double = {
    var sum: Double = Math.exp(-x)
    var term: Double = sum

    var n = 1
    while (n < a) {
      term /= n
      term *= x
      sum += term

      n += 1
    }

    sum
  }

  def finiteHalfGammaQ(a: Double, x: Double): Double = {
    var e: Double = BoostErf.erfc(Math.sqrt(x))

    if (a > 1) {
      var term = Math.exp(-x) / Math.sqrt(Math.PI * x)
      term *= x
      term /= 0.5
      var sum = term

      var n = 2
      while (n < a) {
        term /= n - 0.5
        term *= x
        sum += term

        n += 1
      }

      e += sum
    }

    e
  }

  def lowerGammaSeries(a: Double, z: Double, initValue: Double, pol: Policy): Double = {
    val eps = pol.eps
    val maxIterations = pol.maxIterations

    val gen = new java.util.function.DoubleSupplier {
      private var result: Double = 1.0D
      private var n: Int = 0

      def getAsDouble: Double = {
        val r = result
        n += 1
        result *= z / (a + n)
        r
      }
    }

    BoostTools.kahanSumSeries(gen, eps, maxIterations, initValue)
  }

  def tgammaSmallUpperPart(a: Double, x: Double, pol: Policy, pgam: Array[Double], invert: Boolean): Double = {
    var result: Double = tgamma1pm1(a)

    pgam(0) = (result + 1) / a

    var p: Double = BoostMath.powm1(x, a)
    result -= p
    result /= a
    val maxIter = pol.maxIterations
    p += 1
    val initValue =
      if (invert) pgam(0)
      else 0

    // Series representation for upper fraction when z is small.
    val gen = new java.util.function.DoubleSupplier {
      private var result: Double = -x
      final private val z: Double = -x
      private var n: Int = 1

      def getAsDouble: Double = {
        val r = result / (a + n)
        n += 1
        result = result * z / n
        r
      }
    }

    result = -p * BoostTools.kahanSumSeries(gen, pol.eps, maxIter, (initValue - result) / p)
    if (invert) result = -result

    result
  }

  def fullIgammaPrefix(a: Double, z: Double): Double = {
    if (z > Double.MaxValue) return 0.0D
    val alz: Double = a * Math.log(z)

    if(z >= 1.0D) {
      if((alz < LogMaxValue) && (-z > LogMinValue)) {
        Math.pow(z, a) * Math.exp(-z)
      } else if(a >= 1) {
        Math.pow(z / Math.exp(z / a), a)
      } else {
        Math.exp(alz - z)
      }
    } else {
      if(alz > LogMinValue) {
        Math.pow(z, a) * Math.exp(-z)
      } else {
        Math.pow(z / Math.exp(z / a), a)
      }
    }
  }

  def regularisedGammaPrefix(a: Double, z: Double): Double = {
    if(z >= Double.MaxValue) 0
    else if(a <= 1) {
      if (-z <= LogMinValue) {
        Math.exp(a * Math.log(z) - z - lgamma(a))
      } else {
        Math.pow(z, a) * Math.exp(-z) / tgamma(a)
      }
    } else {
      val alz1: Double = a * Math.log(z)
      if(a <= MaxGammaZ && z >= 1 && (alz1 < LogMaxValue) && (-z > LogMinValue)) {
        Math.pow(z, a) * Math.exp(-z) / tgamma(a)
      } else if(a <= MaxGammaZ && (alz1 > LogMinValue)) {
        Math.pow(z, a) * Math.exp(-z) / tgamma(a)
      } else {
        val agh: Double = a + Lanczos.GMH
        val factor: Double = Math.sqrt(agh / Math.E) / Lanczos.lanczosSumExpGScaled(a)
        val d = ((z - a) - Lanczos.GMH) / agh
        if(a > 128 && Math.abs(d * d * a) <= 100) {
          Math.exp(a * SpecialMath.log1pmx(d) + z * -Lanczos.GMH / agh) * factor
        } else {
          val alz = a * Math.log(z / agh)
          val amz = a - z
          if((Math.min(alz, amz) <= LogMinValue) || (Math.max(alz, amz) >= LogMaxValue)) {
            val amza: Double = amz / a
            if ((Math.min(alz, amz) / 2 > LogMinValue) && (Math.max(alz, amz) / 2 < LogMaxValue)) {
              val sq = Math.pow(z / agh, a / 2) * Math.exp(amz / 2)
              sq * sq * factor
            } else if ((Math.min(alz, amz) / 4 > LogMinValue) && (Math.max(alz, amz) / 4 < LogMaxValue) && (z > a)) {
              val sq = Math.pow(z / agh, a / 4) * Math.exp(amz / 4)
              val tq = sq * sq
              tq * tq * factor
            } else if ((amza > LogMinValue) && (amza < LogMaxValue)) {
                Math.pow((z * Math.exp(amza)) / agh, a) * factor
            } else {
              Math.exp(alz + amz) * factor
            }
          } else {
            Math.pow(z / agh, a) * Math.exp(amz) * factor
          }
        }
      }
    }
  }

  def igammaTemmeLarge(a: Double, x: Double): Double = {
    val sigma: Double = (x - a) / a
    val phi: Double = -SpecialMath.log1pmx(sigma)
    val y: Double = a * phi
    var z: Double = Math.sqrt(2 * phi)
    if (x < a) z = -z

    val workspace: Array[Double] = new Array[Double](10)

    val C0: Array[Double] = Array[Double](
      -0.33333333333333333D,
      0.083333333333333333D,
      -0.014814814814814815D,
      0.0011574074074074074D,
      0.0003527336860670194D,
      -0.00017875514403292181D,
      0.39192631785224378e-4D,
      -0.21854485106799922e-5D,
      -0.185406221071516e-5D,
      0.8296711340953086e-6D,
      -0.17665952736826079e-6D,
      0.67078535434014986e-8D,
      0.10261809784240308e-7D,
      -0.43820360184533532e-8D,
      0.91476995822367902e-9D
    )
    workspace(0) = BoostTools.evaluatePolynomial(C0, z)

    val C1: Array[Double] = Array[Double](
      -0.0018518518518518519D,
      -0.0034722222222222222D,
      0.0026455026455026455D,
      -0.00099022633744855967D,
      0.00020576131687242798D,
      -0.40187757201646091e-6D,
      -0.18098550334489978e-4D,
      0.76491609160811101e-5D,
      -0.16120900894563446e-5D,
      0.46471278028074343e-8D,
      0.1378633446915721e-6D,
      -0.5752545603517705e-7D,
      0.11951628599778147e-7D
    )
    workspace(1) = BoostTools.evaluatePolynomial(C1, z)

    val C2: Array[Double] = Array[Double](
      0.0041335978835978836D,
      -0.0026813271604938272D,
      0.00077160493827160494D,
      0.20093878600823045e-5D,
      -0.00010736653226365161D,
      0.52923448829120125e-4D,
      -0.12760635188618728e-4D,
      0.34235787340961381e-7D,
      0.13721957309062933e-5D,
      -0.6298992138380055e-6D,
      0.14280614206064242e-6D
    )
    workspace(2) = BoostTools.evaluatePolynomial(C2, z)

    val C3: Array[Double] = Array[Double](
      0.00064943415637860082D,
      0.00022947209362139918D,
      -0.00046918949439525571D,
      0.00026772063206283885D,
      -0.75618016718839764e-4D,
      -0.23965051138672967e-6D,
      0.11082654115347302e-4D,
      -0.56749528269915966e-5D,
      0.14230900732435884e-5D
    )
    workspace(3) = BoostTools.evaluatePolynomial(C3, z)

    val C4: Array[Double] = Array[Double](
      -0.0008618882909167117D,
      0.00078403922172006663D,
      -0.00029907248030319018D,
      -0.14638452578843418e-5D,
      0.66414982154651222e-4D,
      -0.39683650471794347e-4D,
      0.11375726970678419e-4D
    )
    workspace(4) = BoostTools.evaluatePolynomial(C4, z)

    val C5: Array[Double] = Array[Double](
      -0.00033679855336635815D,
      -0.69728137583658578e-4D,
      0.00027727532449593921D,
      -0.00019932570516188848D,
      0.67977804779372078e-4D,
      0.1419062920643967e-6D,
      -0.13594048189768693e-4D,
      0.80184702563342015e-5D,
      -0.22914811765080952e-5D
    )
    workspace(5) = BoostTools.evaluatePolynomial(C5, z)

    val C6: Array[Double] = Array[Double](
      0.00053130793646399222D,
      -0.00059216643735369388D,
      0.00027087820967180448D,
      0.79023532326603279e-6D,
      -0.81539693675619688e-4D,
      0.56116827531062497e-4D,
      -0.18329116582843376e-4D
    )
    workspace(6) = BoostTools.evaluatePolynomial(C6, z)

    val C7: Array[Double] = Array[Double](
      0.00034436760689237767,
      0.51717909082605922e-4,
      -0.00033493161081142236,
      0.0002812695154763237,
      -0.00010976582244684731
    )
    workspace(7) = BoostTools.evaluatePolynomial(C7, z)

    val C8: Array[Double] = Array[Double](
      -0.00065262391859530942D,
      0.00083949872067208728D,
      -0.00043829709854172101D
    )
    workspace(8) = BoostTools.evaluatePolynomial(C8, z)
    workspace(9) = -0.00059676129019274625D

    var result: Double = BoostTools.evaluatePolynomial(workspace, 1 / a)
    result *= Math.exp(-y) / Math.sqrt(2 * Math.PI * a)
    if (x < a) result = -result

    result += BoostErf.erfc(Math.sqrt(y)) / 2

    result
  }

  def incompleteTgammaLargeX(a: Double, x: Double, pol: Policy): Double = {
    val eps = pol.eps
    val maxIterations = pol.maxIterations

    val gen = new java.util.function.DoubleSupplier {
      private var term: Double = 1.0D
      private var n: Int = 0

      def getAsDouble: Double = {
        val result = term
        n += 1
        term *= (a - n) / x
        result
      }
    }

    BoostTools.kahanSumSeries(gen, eps, maxIterations)
  }

  def nonZeroLength(array: Array[Int]): Boolean =
    array != null && array.length != 0

  def tgammaDeltaRatio(z: Double, delta: Double): Double = {
    val zDelta: Double = z + delta
    if(zDelta.isNaN) {
      Double.NaN
    } else if(z <= 0 || zDelta <= 0) {
      tgamma(z) / tgamma(zDelta)
    } else if(Math.rint(delta) == delta) {
      if(delta == 0) {
        1
      } else if(Math.rint(z) == z && z <= MaxGammaZ && zDelta <= MaxGammaZ) {
        Factorial(z.toInt - 1) / Factorial(zDelta.toInt - 1)
      } else if(Math.abs(delta) < 20) {
        if(delta < 0) {
          var mz: Double = z - 1
          var result: Double = mz

          var d: Int = (delta + 1).toInt
          while(d != 0) {
            mz -= 1
            result *= mz

            d += 1
          }

          result
        } else {
          var mz: Double = z
          var result: Double = 1.0D / mz

          var d: Int = (delta - 1).toInt
          while (d != 0) {
            mz += 1
            result /= mz

            d -= 1
          }

          result
        }
      } else {
        tgammaDeltaRatioImpLanczos(z, delta)
      }
    } else {
      tgammaDeltaRatioImpLanczos(z, delta)
    }
  }

  def tgammaDeltaRatioImpLanczos(z: Double, delta: Double): Double = {
    if(z < Epsilon) {
      if (MaxGammaZ < delta) {
        var ratio: Double = tgammaDeltaRatioImpLanczos(delta, MaxGammaZ - delta)
        ratio *= z
        ratio *= Factorial(MaxFactorial)
        1 / ratio
      } else {
        1 / (z * tgamma(z + delta))
      }
    } else {
      val zgh: Double = z + Lanczos.GMH
      var result: Double = 0.0D
      if(z + delta == z) {
        result = -Math.exp(delta)
      } else {
        if(Math.abs(delta) < 10) {
          result = Math.exp((0.5 - z) * Math.log1p(delta / zgh))
        } else {
          result = Math.pow(zgh / (zgh + delta), z - 0.5)
        }

        result *= Lanczos.lanczosSum(z) / Lanczos.lanczosSum(z + delta)
      }
      result *= Math.pow(Math.E / (zgh + delta), delta)
      result
    }
  }

  def tgammaRatio(x: Double, y: Double): Double = {
    if(x <= 0 || !x.isFinite || y <= 0 || !y.isFinite) {
      Double.NaN
    } else if(x <= java.lang.Double.MIN_NORMAL) {
      TwoPow53 * tgammaRatio(x * TwoPow53, y)
    } else if(x <= MaxGammaZ && y <= MaxGammaZ) {
      tgamma(x) / tgamma(y)
    } else if(x < 1) {
      if(y < (2 * MaxGammaZ)) {
        var prefix: Double = 1.0D / x
        var mx = x + 1
        var my = y
        while (my >= MaxGammaZ) {
          my -= 1
          prefix /= my
        }
        return prefix * tgamma(mx) / tgamma(my)
      } else {
        Math.exp(lgamma(x) - lgamma(y))
      }
    } else if(y < 1) {
      if(x < (2 * MaxGammaZ)) {
        var prefix: Double = y
        var mx: Double = x
        var my: Double = y + 1
        while (mx >= MaxGammaZ) {
          mx -= 1
          prefix *= mx
        }
        prefix * tgamma(mx) / tgamma(my)
      } else {
        Math.exp(lgamma(x) - lgamma(y))
      }
    } else {
      tgammaDeltaRatio(x, y - x)
    }
  }

}
