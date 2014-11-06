import com.typesafe.sbt.SbtNativePackager._

import NativePackagerKeys._

name := "jrv"

version := "1.1"

libraryDependencies ++= Seq(
  "com.github.scopt" %% "scopt" % "3.2.0",
  "org.scalatest" %% "scalatest" % "2.2.1" % "test",
  "org.hamcrest" % "hamcrest-core" % "1.1",
  "org.mockito" % "mockito-core" % "1.10.8"
)

mainClass := Some("validator")

resolvers += Resolver.sonatypeRepo("public")

scalacOptions in Test ++= Seq("-Yrangepos")

packageArchetype.java_application

packageDescription in Debian := "Java regex validator"

maintainer in Debian := "Piotr Limanowski"

name in Rpm := "sbt"

version in Rpm <<= version

rpmRelease := "1"

rpmVendor := "peel"

rpmUrl := Some("http://github.com/peel/jrv")

rpmLicense := Some("BSD")

rpmChangelogFile := Some("changelog")
