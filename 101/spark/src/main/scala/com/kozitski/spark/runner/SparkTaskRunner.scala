package com.kozitski.spark.runner

/*
  Common interace for runners
 */
trait SparkTaskRunner {

  def run(): Unit
  def run(path: String): Unit

}
