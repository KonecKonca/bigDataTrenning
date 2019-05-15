package com.kozitski.spark.runner.impl

import com.kozitski.spark.domain.Hotel
import com.kozitski.spark.runner.{SparkTaskRunner, ApplicationConfig}
import com.kozitski.spark.service.impl.PopularHotelSearchService

/*
  Runner for (Find top 3 most popular hotels between couples)
 */
class Task1Runner extends SparkTaskRunner{

  override def run(path: String): Unit = {
    val service = new PopularHotelSearchService
    val hotels: Array[(Hotel, Integer)] = service.search(path)

    hotels.foreach(println)
  }

}
