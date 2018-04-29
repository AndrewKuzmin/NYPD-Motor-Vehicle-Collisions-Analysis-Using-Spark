package com.phylosoft.spark.learning.sql

import com.typesafe.config.ConfigFactory
import org.apache.spark.sql.{DataFrame, SaveMode, SparkSession}

class Processor(appName: String) {

  private val appConf = ConfigFactory.load

  private[sql] val spark = SparkSession
    .builder()
    .appName(appName)
    //      sparkConf.set("spark.serializer", "org.apache.spark.serializer.KryoSerializer")
    //      sparkConf.set("spark.kryoserializer.buffer", "24")
    .config("spark.sql.shuffle.partitions", "1")
    .config("spark.sql.cbo.enabled", "true")
    .getOrCreate()

  def start(): Unit = {
    val inputDF = spark.read
      .option("delimiter", ",")
      .option("header", true)
      .option("inferSchema", "true")
      .csv("data/collisions/data_sample/NYPD_Motor_Vehicle_Collisions.csv")
      .cache()

    //  println("Count = " + lines.count())

    val outputDF = checkAndFormatFromFile(inputDF)

    outputDF.write
      .format("org.apache.spark.sql.cassandra")
      .options(Map( "table" -> appConf.getString("cassandra.table"), "keyspace" -> appConf.getString("cassandra.keyspace")))
      .mode(SaveMode.Append)
      .save()

  }

  def checkAndFormatFromFile(inputDF: DataFrame): DataFrame = {
    inputDF
  }

}
