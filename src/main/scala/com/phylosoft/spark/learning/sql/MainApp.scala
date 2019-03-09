package com.phylosoft.spark.learning.sql

object MainApp {

  def main(args: Array[String]): Unit = {

    if (args.length < 1) {
      System.err.println("Usage: MainApp <src-data-dir>")
      System.exit(1)
    }

    val processor = new Processor("MainApp", args(0).trim)

    processor.start()

  }

}
