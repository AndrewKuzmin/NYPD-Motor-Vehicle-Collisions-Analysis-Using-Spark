import sbt._

object Dependencies {
  lazy val scalaTest = "org.scalatest" %% "scalatest" % "3.0.5"
  lazy val scalaCheck = "org.scalacheck" %% "scalacheck" % "1.13.4"

  lazy val sparkVersion = "2.3.0"
  lazy val sparkCore = "org.apache.spark" %% "spark-core" % sparkVersion
  lazy val sparkSql  = "org.apache.spark" %% "spark-sql" % sparkVersion
  lazy val sparkHive = "org.apache.spark" %% "spark-hive" % sparkVersion

  lazy val cassandra = "com.datastax.spark" %% "spark-cassandra-connector" % "2.0.7"


  lazy val typeSafeConfig = "com.typesafe" % "config" % "1.3.2"

  lazy val scoptConfig = "com.github.scopt" %% "scopt" % "3.3.0"


  lazy val logging = "com.typesafe.scala-logging" %% "scala-logging" % "3.7.2"

}

