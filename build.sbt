

name := "minesweeper"

version := "1.0"

scalaVersion := "2.11.7"

scalacOptions ++= Seq("-deprecation")

libraryDependencies += "org.scalatest" % "scalatest_2.11" % "2.2.4" % "test"

libraryDependencies ++= Seq("mysql" % "mysql-connector-java" % "5.1.27",
  "org.yaml" % "snakeyaml" % "1.8", "org.scala-lang" % "scala-reflect" % "2.10.3")

