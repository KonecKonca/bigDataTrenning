package com.kozitski.spark.service.impl

import com.kozitski.spark.domain.Hotel
import com.kozitski.spark.runner.ApplicationConfig
import com.kozitski.spark.service.{SearchService, Service}
import org.apache.commons.lang3.math.NumberUtils
import org.apache.spark.SparkContext
import org.apache.spark.rdd.RDD

/*
  Service for (Find top 3 hotels where people with children are interested but not booked in the end)
 */
class HotelWithChildrenService extends SearchService[(Hotel, Integer)] {

  override def search(path: String): Array[(Hotel, Integer)] = {
    search(ApplicationConfig.sc, path)
  }

  override def search(sc: SparkContext, path: String): Array[(Hotel, Integer)] = {

    val rdd = sc
      .textFile(path)
      .mapPartitionsWithIndex(
        (idx, iter) => if (idx == NumberUtils.INTEGER_ZERO) iter.drop(NumberUtils.INTEGER_ONE) else iter
      )

    perform(rdd)
  }

  def perform(any: RDD[String]): Array[(Hotel, Integer)] = {

    any.filter(elem =>
    {
      val strings = elem.split(Service.SPLIT_SYMBOL)

      val isBooking = strings(HotelWithChildrenService.IS_BOOKING_INDEX)
      val srchAdultsCnt = strings(HotelWithChildrenService.SRCH_ADULT_CNT)
      val srchChildrenCnt = strings(HotelWithChildrenService.SRCH_CHILDREN_CNT)

      isBooking != null && srchAdultsCnt != null && srchChildrenCnt != null && isBooking.equals(String.valueOf(NumberUtils.INTEGER_ZERO)) &&
        Integer.parseInt(srchAdultsCnt) > NumberUtils.INTEGER_ONE && Integer.parseInt(srchChildrenCnt) > NumberUtils.INTEGER_ONE
    })
      .map(elem => {
        val strings = elem.split(Service.SPLIT_SYMBOL)

        (
          Hotel(
            strings(HotelWithChildrenService.HOTEL_CONTINENT),
            strings(HotelWithChildrenService.HOTEL_COUNTRY),
            strings(HotelWithChildrenService.HOTEL_MARKET)
          ),
          NumberUtils.INTEGER_ONE
        )
      })
      .reduceByKey(_ + _)
      .sortBy(tuple => -tuple._2)
      .take(HotelWithChildrenService.TAKEN_NUMBER)

  }

/*
  HotelWithChildrenService constants
*/
}
object HotelWithChildrenService extends Serializable {
  val IS_BOOKING_INDEX = 18
  val SRCH_ADULT_CNT = 13
  val SRCH_CHILDREN_CNT = 14
  val HOTEL_CONTINENT = 20
  val HOTEL_COUNTRY = 21
  val HOTEL_MARKET = 22
  val TAKEN_NUMBER = 3
}
