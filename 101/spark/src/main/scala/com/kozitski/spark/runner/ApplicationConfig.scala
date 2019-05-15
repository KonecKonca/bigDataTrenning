package com.kozitski.spark.runner

import org.apache.spark.{SparkConf, SparkContext}

/*
  Common runners constants with
  initialized SparkConfig and SparkContext
 */
object ApplicationConfig extends Serializable {
  val MASTER_NAME = "local"
  val APP_NAME = "SparkApplication"

  val conf: SparkConf = new SparkConf().setMaster(ApplicationConfig.MASTER_NAME).setAppName(ApplicationConfig.APP_NAME)
  val sc: SparkContext = new SparkContext(conf)
}
