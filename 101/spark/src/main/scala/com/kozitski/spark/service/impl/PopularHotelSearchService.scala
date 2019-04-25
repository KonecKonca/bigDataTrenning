package com.kozitski.spark.service.impl

import com.kozitski.spark.domain.Hotel
import com.kozitski.spark.service.SearchService
import org.apache.spark.SparkContext

class PopularHotelSearchService extends SearchService[Hotel] with Serializable {
  val textpath = "C:\\Users\\Andrei_Kazitski\\Desktop\\dataData\\unziped\\test.csv"
  val splitSymbol = ","

  override def search(sc: SparkContext): List[Hotel] = {

    sc
      .textFile(textpath)
      .mapPartitionsWithIndex(
        (idx, iter) => if (idx == 0) iter.drop(1) else iter
      )
      .filter(e =>{
        val strings = e.split(splitSymbol)
        val couple = strings(14)

        couple != null && couple.equals("2")
      })
      .map(e => {
        val strings = e.split(splitSymbol)
        ((strings(19), strings(20), strings(21)), 1)
      })
      .reduceByKey(_ + _)
      .sortBy(tuple => 0 - tuple._2)
      .take(3)
      .foreach(println)

    List(Hotel("343", "343", "324"))
  }

}
