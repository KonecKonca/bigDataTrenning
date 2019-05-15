package com.kozitski.spark.runner.impl

import com.kozitski.spark.domain.Hotel
import com.kozitski.spark.runner.{SparkTaskRunner, ApplicationConfig}
import com.kozitski.spark.service.impl.{HotelWithChildrenService, MostPopularCountryService, PopularHotelSearchService}

/*
  Runner for (Find top 3 hotels where people with children are interested but not booked in the end)
 */
class Task3Runner extends SparkTaskRunner{

  override def run(path: String): Unit = {

    val service = new HotelWithChildrenService
    val hotels: Array[(Hotel, Integer)] = service.search(path)

    hotels.foreach(println)
  }

}
