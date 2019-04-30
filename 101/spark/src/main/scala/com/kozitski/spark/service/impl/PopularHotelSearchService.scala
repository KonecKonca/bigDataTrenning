package com.kozitski.spark.service.impl

import com.kozitski.spark.domain.Hotel
import com.kozitski.spark.runner.TaskRunner
import com.kozitski.spark.service.{SearchService, Service}
import org.apache.commons.lang3.math.NumberUtils
import org.apache.spark.SparkContext

/*
  Service for (Find top 3 most popular hotels between couple)
 */
class PopularHotelSearchService extends SearchService[(Hotel, Integer)] {

  override def search(): Array[(Hotel, Integer)] = {
    search(TaskRunner.sc, TaskRunner.WINDOWS_PATH)
  }

  override def search(path: String): Array[(Hotel, Integer)] = {
    search(TaskRunner.sc, path)
  }

  override def search(sc: SparkContext, path: String): Array[(Hotel, Integer)] = {

    sc
      .textFile(path)
      .mapPartitionsWithIndex(
        (idx, iter) => if (idx == NumberUtils.INTEGER_ZERO) iter.drop(NumberUtils.INTEGER_ONE) else iter
      )
      .filter(e =>{
        val strings = e.split(Service.SPLIT_SYMBOL)
        val couple = strings(PopularHotelSearchService.IS_COUPLE_NUMBER)

        couple != null && couple.equals(String.valueOf(PopularHotelSearchService.COUPLE_IDENTIFIER))
      })
      .map(e => {
        val strings = e.split(Service.SPLIT_SYMBOL)

        (
          Hotel(
            strings(PopularHotelSearchService.HOTEL_CONTINENT_INDEX),
            strings(PopularHotelSearchService.HOTEL_COUNTRY_INDEX),
            strings(PopularHotelSearchService.HOTEL_MARKET_INDEX)
          ),
          NumberUtils.INTEGER_ONE
        )

      })
      .reduceByKey(_ + _)
      .sortBy(tuple => -tuple._2)
      .take(PopularHotelSearchService.COUNT_OF_INPUT_ROWS)

  }

}

/*
  PopularHotelSearchService constants
 */
object PopularHotelSearchService extends Serializable {
  val IS_COUPLE_NUMBER = 13
  val HOTEL_CONTINENT_INDEX = 18
  val HOTEL_COUNTRY_INDEX = 19
  val HOTEL_MARKET_INDEX = 20
  val COUNT_OF_INPUT_ROWS = 3
  val COUPLE_IDENTIFIER = 2
}