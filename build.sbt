import com.typesafe.sbt.SbtNativePackager._

import NativePackagerKeys._

name := "jrv"

version := "1.0"

libraryDependencies ++= Seq(
  "com.github.scopt" %% "scopt" % "3.2.0",
  "org.scalatest" %% "scalatest" % "2.2.1" % "test"
)

mainClass := Some("validator")

resolvers += Resolver.sonatypeRepo("public")

resolvers ++= Seq("snapshots", "releases").map(Resolver.sonatypeRepo)

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