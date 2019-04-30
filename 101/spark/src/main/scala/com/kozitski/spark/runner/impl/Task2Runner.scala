package com.kozitski.spark.runner.impl

import com.kozitski.spark.domain.Country
import com.kozitski.spark.runner.{SparkTaskRunner, TaskRunner}
import com.kozitski.spark.service.impl.MostPopularCountryService

/*
  Runner for (Find the most popular country where hotels are booked and searched from the same country)
 */
class Task2Runner extends SparkTaskRunner{

  override def run(): Unit = {
    run(TaskRunner.WINDOWS_PATH)
  }

  override def run(path: String): Unit = {

    val service = new MostPopularCountryService
    val hotels: Array[(Country, Integer)] = service.search(path)

    hotels.foreach(println)
  }

}
