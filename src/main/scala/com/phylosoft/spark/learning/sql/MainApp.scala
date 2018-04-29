package com.phylosoft.spark.learning.sql

object MainApp {

  def main(args: Array[String]): Unit = {

    val processor = new Processor("MainApp")

    processor.start()

  }

}
