package com.kozitski.spark.service.impl

import com.kozitski.spark.domain.{Country, Hotel}
import com.kozitski.spark.runner.TaskRunner
import com.kozitski.spark.service.{SearchService, Service}
import org.apache.commons.lang3.math.NumberUtils
import org.apache.spark.SparkContext

class MostPopularCountryService extends SearchService[(Country, Integer)] {

  override def search(): Array[(Country, Integer)] = {
    search(TaskRunner.sc, TaskRunner.WINDOWS_PATH)
  }

  override def search(path: String): Array[(Country, Integer)] = {
    search(TaskRunner.sc, path)
  }

  override def search(sc: SparkContext, path: String): Array[(Country, Integer)] = {

    sc
      .textFile(path)
      .mapPartitionsWithIndex(
        (idx, iter) => if (idx == NumberUtils.INTEGER_ZERO) iter.drop(NumberUtils.INTEGER_ONE) else iter
      )
      .filter(elem => {
        val strings = elem.split(Service.SPLIT_SYMBOL)

        val isBooking = strings(HotelWithChildrenService.IS_BOOKING_INDEX)
        val userLocationCountry = 2
        val hotelCountry = strings(HotelWithChildrenService.HOTEL_COUNTRY)

        isBooking != null && isBooking.equals(String.valueOf(NumberUtils.INTEGER_ONE)) &&
        true
      })

  }

}

object MostPopularCountryService extends Serializable {
  val HOTEL_COUNTRY = 21
}


// hotels are booked and searched from the same country