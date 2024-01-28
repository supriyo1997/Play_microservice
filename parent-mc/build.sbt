name := """parent-mc"""
organization := "com.parent"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava,PlayEbean)

scalaVersion := "2.12.8"

libraryDependencies += guice

libraryDependencies += "com.itextpdf" % "itextpdf" % "5.5.13"


libraryDependencies += guice
//libraryDependencies += "mysql" % "mysql-connector-java" % "5.1.49"
libraryDependencies += "mysql" % "mysql-connector-java" % "8.0.28"
libraryDependencies ++= Seq(
  javaWs
)

libraryDependencies += jdbc
