package com.kozitski.spark.runner.impl

import com.kozitski.spark.domain.Hotel
import com.kozitski.spark.runner.{SparkTaskRunner, TaskRunner}
import com.kozitski.spark.service.impl.{HotelWithChildrenService, MostPopularCountryService, PopularHotelSearchService}

class Task3Runner extends SparkTaskRunner{

  override def run(): Unit = {
    run(TaskRunner.WINDOWS_PATH)
  }

  override def run(path: String): Unit = {

    val service = new HotelWithChildrenService
    val hotels: Array[(Hotel, Integer)] = service.search()

    hotels.foreach(println)
  }

}
