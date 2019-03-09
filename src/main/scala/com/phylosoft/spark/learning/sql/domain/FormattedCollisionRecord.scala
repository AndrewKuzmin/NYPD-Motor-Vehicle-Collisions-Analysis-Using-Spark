package com.phylosoft.spark.learning.sql.domain

import java.sql.Date

/**
  * Created by Andrew Kuzmin on 3/9/2019.
  */
case class FormattedCollisionRecord(
                                     zip: String,
                                     col_date: Date,
                                     key: Int,
                                     col_time: String,
                                     borough: String,
                                     on_street: String,
                                     cross_street: String,
                                     off_street: String,
                                     location: String)

