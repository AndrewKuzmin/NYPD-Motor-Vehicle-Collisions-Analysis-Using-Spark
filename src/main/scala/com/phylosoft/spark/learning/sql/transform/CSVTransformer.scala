package com.phylosoft.spark.learning.sql.transform

/**
  * Created by Andrew Kuzmin on 3/9/2019.
  */
import java.sql.Date

import com.phylosoft.spark.learning.sql.domain.{FormattedCollisionRecord, RawCollisionRecord}
import com.typesafe.config.Config
import org.apache.spark.sql.{DataFrame, Dataset, SparkSession}
import org.joda.time.format.DateTimeFormat

import scala.collection.JavaConverters

class CSVTransformer(private val spark: SparkSession, private val appConf: Config) {

  def checkAndFormatFromFile(df: DataFrame): Dataset[FormattedCollisionRecord] = {
    import spark.implicits._

    val inputDateFormat = appConf.getString("csv.input_date_format")

    // rename DF column names to exclude spaces
    val stringList = appConf.getStringList("csv.input_column_names")
    val inputColumnNames = JavaConverters.asScalaIteratorConverter(stringList.iterator()).asScala.toSeq
    val renamedDF = df.toDF(inputColumnNames: _*)

    import CSVTransformer._
    import MySimpleHelper._

    // filter out empty zip, date and unique key columns; incorrect date and unique key formats
    val filteredDS = renamedDF.as[RawCollisionRecord].filter(x =>
      checkDate(x.date, inputDateFormat) && checkInt(x.uniqueKey) &&
        !x.zipCode.isNullOrEmpty && !x.date.isEmpty && !x.uniqueKey.isEmpty)

    //transform filteredDS to FormattedCollisionRecord DS (Cassandra data types)
    filteredDS.map(x => {
      val formatter = DateTimeFormat.forPattern(inputDateFormat)
      FormattedCollisionRecord(x.zipCode, new Date(formatter.parseDateTime(x.date).getMillis),
        x.uniqueKey.toInt, x.time,
        x.borough, x.onStreetName, x.crossStreetName,
        x.offStreetName, x.location)
    })
  }

}

object CSVTransformer {

  def checkDate(date: String, format: String): Boolean = {
    try {
      val formatter = DateTimeFormat.forPattern(format)
      formatter.parseDateTime(date)
      true
    }
    catch {
      case _: Throwable => false
    }
  }

  def checkInt(int: String): Boolean = {
    try {
      int.toInt
      true
    }
    catch {
      case _: Throwable => false
    }
  }

}

object MySimpleHelper {

  implicit class StringExtended(val input: String) {
    def isNullOrEmpty: Boolean = input == null || input.trim.isEmpty
  }

}

