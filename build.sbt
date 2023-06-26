ThisBuild / tlBaseVersion := "0.4"

ThisBuild / startYear := Some(2023)
ThisBuild / licenses := Seq(License.Apache2)

val Scala3 = "3.3.0"
ThisBuild / crossScalaVersions := Seq(Scala3)
ThisBuild / scalaVersion := Scala3
ThisBuild / scalacOptions ++= Seq("-no-indent", "-old-syntax")

ThisBuild / tlJdkRelease := Some(17)

lazy val root = tlCrossRootProject.aggregate(
  core,
  complex,
  primes,
  quaternion,
  fraction,
  angle,
  gamma,
  combinatorics,
  arrays,
  field,
  rootfinder
)

lazy val core = crossProject(JVMPlatform, JSPlatform, NativePlatform)
  .crossType(CrossType.Pure)
  .in(file("core"))
  .settings(
    name := "apache-commons-numbers-core",
    description := "Scala port of Apache Numbers Commons Core"
  )

lazy val complex = crossProject(JVMPlatform, JSPlatform, NativePlatform)
  .crossType(CrossType.Pure)
  .in(file("complex"))
  .settings(
    name := "apache-commons-numbers-complex",
    description := "Scala port of Apache Numbers Commons Complex"
  )

lazy val primes = crossProject(JVMPlatform, JSPlatform, NativePlatform)
  .crossType(CrossType.Pure)
  .in(file("primes"))
  .settings(
    name := "apache-commons-numbers-primes",
    description := "Scala port of Apache Numbers Commons Primes"
  )

lazy val quaternion = crossProject(JVMPlatform, JSPlatform, NativePlatform)
  .crossType(CrossType.Pure)
  .in(file("quaternion"))
  .settings(
    name := "apache-commons-numbers-quaternion",
    description := "Scala port of Apache Numbers Commons Quaternion"
  )

lazy val fraction = crossProject(JVMPlatform, JSPlatform, NativePlatform)
  .crossType(CrossType.Pure)
  .in(file("fraction"))
  .settings(
    name := "apache-commons-numbers-fraction",
    description := "Scala port of Apache Numbers Commons Fraction"
  )

lazy val angle = crossProject(JVMPlatform, JSPlatform, NativePlatform)
  .crossType(CrossType.Pure)
  .in(file("angle"))
  .settings(
    name := "apache-commons-numbers-angle",
    description := "Scala port of Apache Numbers Commons Angle"
  )

lazy val gamma = crossProject(JVMPlatform, JSPlatform, NativePlatform)
  .crossType(CrossType.Full)
  .in(file("gamma"))
  .settings(
    name := "apache-commons-numbers-gamma",
    description := "Scala port of Apache Numbers Commons Gamma"
  )
  .jvmSettings(
    libraryDependencies ++= Seq(
      "junit" % "junit" % "4.13.2" % Test,
      "com.github.sbt" % "junit-interface" % "0.13.3" % Test,
    )
  )
  .jsEnablePlugins(ScalaJSJUnitPlugin)
  .nativeEnablePlugins(ScalaNativeJUnitPlugin)
  .dependsOn(core, fraction)

lazy val combinatorics = crossProject(JVMPlatform, JSPlatform, NativePlatform)
  .crossType(CrossType.Pure)
  .in(file("combinatorics"))
  .settings(
    name := "apache-commons-numbers-combinatorics",
    description := "Scala port of Apache Numbers Commons Combinatorics"
  )

lazy val arrays = crossProject(JVMPlatform, JSPlatform, NativePlatform)
  .crossType(CrossType.Pure)
  .in(file("arrays"))
  .settings(
    name := "apache-commons-numbers-arrays",
    description := "Scala port of Apache Numbers Commons Arrays"
  )

lazy val field = crossProject(JVMPlatform, JSPlatform, NativePlatform)
  .crossType(CrossType.Pure)
  .in(file("field"))
  .settings(
    name := "apache-commons-numbers-field",
    description := "Scala port of Apache Numbers Commons Field"
  )

lazy val rootfinder = crossProject(JVMPlatform, JSPlatform, NativePlatform)
  .crossType(CrossType.Pure)
  .in(file("rootfinder"))
  .settings(
    name := "apache-commons-numbers-rootfinder",
    description := "Scala port of Apache Numbers Commons Rootfinder"
  )