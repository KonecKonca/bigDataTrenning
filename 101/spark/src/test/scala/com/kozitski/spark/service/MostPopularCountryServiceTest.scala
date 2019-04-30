package com.kozitski.spark.service

import com.kozitski.spark.domain.{Country, Hotel}
import com.kozitski.spark.service.impl.{HotelWithChildrenService, MostPopularCountryService}
import org.junit._

@Test
class MostPopularCountryServiceTest {

  val countryService = new MostPopularCountryService
  val TEST_PATH = "C:\\Users\\Andrei_Kazitski\\Desktop\\dataData\\unziped\\train.csv"
  val result: Array[(Country, Integer)] = countryService.search(TEST_PATH)

  @Test
  def numberOfOutputTest(): Unit = {
    val actual = 1

    val expected = result.length

    Assert.assertEquals(actual, expected)
  }

  @Test
  def numberCompareOutputTest(): Unit = {
    val actual = Country("68")

    val expected = result(0)._1

    Assert.assertEquals(actual, expected)

  }

}
