package com.kozitski.spark.runner

trait SparkTaskRunner {

  def run(): Unit
  def run(path: String): Unit

}
