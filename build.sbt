import Dependencies._

lazy val root = (project in file(".")).
  settings(
    inThisBuild(List(
      organization := "com.phylosoft.spark.learning.sql",
      scalaVersion := "2.12.7",
      version      := "0.1.0-SNAPSHOT"
    )),
    name := "NYPDMotorVehicleCollisionsAnalysis",
    libraryDependencies ++= Seq(
      sparkCore,
      sparkSql,
      cassandra,
      typeSafeConfig,
      scoptConfig,
      logging,
      scalaTest % Test,
      scalaCheck % Test
    )
)
