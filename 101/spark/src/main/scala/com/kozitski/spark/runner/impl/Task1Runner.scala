package com.kozitski.spark.runner.impl

import com.kozitski.spark.domain.Hotel
import com.kozitski.spark.runner.{SparkTaskRunner, TaskRunner}
import com.kozitski.spark.service.impl.PopularHotelSearchService

class Task1Runner extends SparkTaskRunner{

  override def run(): Unit = {
    run(TaskRunner.WINDOWS_PATH)
  }

  override def run(path: String): Unit = {
    val service = new PopularHotelSearchService
    val hotels: Array[(Hotel, Integer)] = service.search(path)

    hotels.foreach(println)
  }

}
