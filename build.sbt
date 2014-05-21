import play.PlayScala

organization := "ar.edu.unq.ui"

name := """planificador-api"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.0"

resolvers ++= Seq(
  "Local Maven Repository" at Path.userHome.asFile.toURI.toURL + "/.m2/repository",
  "Uqbar repo releases" at "http://uqbar-wiki.org/mvn/releases",
  "Uqbar repo snapshots" at "http://uqbar-wiki.org/mvn/snapshots"
)

libraryDependencies ++= Seq(
  jdbc,
  anorm,
  cache,
  ws,
  "ar.edu.unq.ui" %% "planificador-domain" % "1.0"
)
