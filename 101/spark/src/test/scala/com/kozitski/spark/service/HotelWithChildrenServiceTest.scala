package com.kozitski.spark.service

import com.kozitski.spark.domain.Hotel
import org.junit._
import com.kozitski.spark.service.impl.HotelWithChildrenService

@Test
class HotelWithChildrenServiceTest {

  var hotelSearchService = new HotelWithChildrenService
  var TEST_PATH = "C:\\Users\\Andrei_Kazitski\\Desktop\\dataData\\unziped\\train.csv"
  val result: Array[(Hotel, Integer)] = hotelSearchService.search(TEST_PATH)

  @Test
  def numberOfOutputTest(): Unit = {
    val actual = 3

    val expected = result.length

    Assert.assertEquals(actual, expected)
  }

  @Test
  def numberCompareOutputTest(): Unit = {
    val actualHotels = Array[Hotel](Hotel("2", "50", "682"), Hotel("4", "8", "110"), Hotel("2", "50", "675"))

    var expectedHotels = Array[Hotel]()
    result.foreach(elem => expectedHotels :+ elem._1)

    val actual =  actualHotels sameElements expectedHotels

    Assert.assertTrue(actual)

  }

}
