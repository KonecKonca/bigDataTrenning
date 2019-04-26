package com.kozitski.spark.service

import org.apache.spark.SparkContext

trait SearchService[T] extends Serializable {

  def search(): Array[T]
  def search(path: String): Array[T]
  def search(sc: SparkContext, path: String): Array[T]

}
