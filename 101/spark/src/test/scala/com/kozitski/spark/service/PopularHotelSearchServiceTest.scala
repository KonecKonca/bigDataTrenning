package com.kozitski.spark.service.service

import com.kozitski.spark.domain.Hotel
import com.kozitski.spark.service.impl.PopularHotelSearchService
import org.junit._

@Test
class PopularHotelSearchServiceTest {

  val hotelSearchService = new PopularHotelSearchService
  val TEST_PATH = "C:\\Users\\Andrei_Kazitski\\Desktop\\dataData\\unziped\\train.csv"
  val result: Array[(Hotel, Integer)] = hotelSearchService.search(TEST_PATH)

  @Test
  def numberOfOutputTest(): Unit = {
    val actual = 3

    val expected = result.length

    Assert.assertEquals(actual, expected)
  }

  @Test
  def numberCompareOutputTest(): Unit = {
    val actualHotels = Array[Hotel](Hotel("0", "1", "2"), Hotel("0", "1", "6"), Hotel("0", "1", "4"))

    var expectedHotels = Array[Hotel]()
    result.foreach(elem => expectedHotels :+ elem._1)

    val actual =  actualHotels sameElements expectedHotels

    Assert.assertTrue(actual)

  }

}
