package com.kozitski.spark

import com.kozitski.spark.domain.Hotel
import com.kozitski.spark.service.impl.PopularHotelSearchService
import org.apache.spark.{SparkConf, SparkContext}

object AppScalaTest extends App {

  println( "Hello World!" )

  val conf = new SparkConf().setMaster("local").setAppName("AnyName")
  val sc = new SparkContext(conf)


  val service1 = new PopularHotelSearchService
  val hotels: List[Hotel] = service1.search(sc)
  hotels.foreach(println)





//  sc.parallelize(1 to 100).filter(e => e % 10 == 0).foreach(println)
//  sc
//    .textFile("C:\\Users\\Andrei_Kazitski\\Desktop\\dataData\\unziped\\test.csv")
//    .take(10)
//    .foreach(println)

}
