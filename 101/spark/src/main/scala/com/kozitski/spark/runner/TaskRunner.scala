package com.kozitski.spark.runner

import org.apache.spark.{SparkConf, SparkContext}

object TaskRunner extends Serializable {
  val WINDOWS_PATH = "C:\\Users\\Andrei_Kazitski\\Desktop\\dataData\\unziped\\train.csv"
  val HDFS_PATH = "hdfs:///user/maria_dev/data/train.csv"

  val MASTER_NAME = "local"
  val APP_NAME = "SparkApplication"

  val conf: SparkConf = new SparkConf().setMaster(TaskRunner.MASTER_NAME).setAppName(TaskRunner.APP_NAME)
  val sc: SparkContext = new SparkContext(conf)
}
