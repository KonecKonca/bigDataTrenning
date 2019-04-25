package com.kozitski.spark.service

import org.apache.spark.SparkContext

trait SearchService[T] {

  def search(sc: SparkContext): List[T]

}
