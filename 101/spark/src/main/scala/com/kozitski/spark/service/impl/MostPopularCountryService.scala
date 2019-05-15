package com.kozitski.spark.service.impl

import com.kozitski.spark.domain.{Country, Hotel}
import com.kozitski.spark.runner.ApplicationConfig
import com.kozitski.spark.service.{SearchService, Service}
import org.apache.commons.lang3.math.NumberUtils
import org.apache.spark.SparkContext
import org.apache.spark.rdd.RDD

/*
  Service for (Find the most popular country where hotels are booked and searched from the same country)
 */
class MostPopularCountryService extends SearchService[(Country, Integer)] {

  override def search(path: String): Array[(Country, Integer)] = {
    search(ApplicationConfig.sc, path)
  }

  override def search(sc: SparkContext, path: String): Array[(Country, Integer)] = {

    val rdd = sc
      .textFile(path)
      .mapPartitionsWithIndex(
        (idx, iter) => if (idx == NumberUtils.INTEGER_ZERO) iter.drop(NumberUtils.INTEGER_ONE) else iter
      )

    perform(rdd)
  }

  def perform(rdd: RDD[String]): Array[(Country, Integer)] = {

    rdd.filter(elem => {
      val strings = elem.split(Service.SPLIT_SYMBOL)

      val isBooking = strings(MostPopularCountryService.IS_BOOKING_INDEX)
      val userLocationCountry = strings(MostPopularCountryService.USER_LOCATION_COUNTRY)
      val hotelCountry = strings(MostPopularCountryService.HOTEL_COUNTRY)

      isBooking != null && isBooking.equals(String.valueOf(NumberUtils.INTEGER_ONE)) &&
        userLocationCountry != null && hotelCountry != null && userLocationCountry.equalsIgnoreCase(hotelCountry)
    })
      .map(elem => {
        val strings = elem.split(Service.SPLIT_SYMBOL)

        (
          Country(strings(MostPopularCountryService.HOTEL_COUNTRY)),
          NumberUtils.INTEGER_ONE
        )
      })
      .reduceByKey(_ + _)
      .sortBy(tuple => -tuple._2)
      .take(NumberUtils.INTEGER_ONE)

  }

}

/*
  MostPopularCountryService constants
 */
object MostPopularCountryService extends Serializable {
  val IS_BOOKING_INDEX = 18
  val HOTEL_COUNTRY = 21
  val USER_LOCATION_COUNTRY = 3
}
