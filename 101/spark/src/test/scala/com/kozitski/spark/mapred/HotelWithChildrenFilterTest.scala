package com.kozitski.spark.mapred

import com.kozitski.spark.runner.ApplicationConfig
import com.kozitski.spark.service.impl.HotelWithChildrenService
import org.junit._

@Test
class HotelWithChildrenFilterTest {

  val sc = ApplicationConfig.sc
  val hotelSearchService = new HotelWithChildrenService

  @Test(expected = classOf[Exception])
  def arrayOfBoundTest(): Unit = {
    val rdd = sc.parallelize(Array("1", "2", "3" ,"4" ))

    hotelSearchService.perform(rdd)
  }

  @Test
  def numberOfOutputTest(): Unit = {
    val rdd = sc.parallelize(
      Array[String](
        "7,2015-08-01 20:13:15,2,3,66,348,24811,216.5785,51,0,0,10,2015-08-03,2015-08-04,2,0,1,8291,1,2,50,191",
        "8,2015-11-07 12:29:09,2,3,66,311,48189,2337.6754,51,0,0,0,2015-12-30,2015-12-31,2,0,1,8250,1,2,50,628",
        "9,2015-11-08 16:21:37,2,3,66,311,48189,2539.7995,51,0,0,10,2016-01-02,2016-01-03,2,0,1,9145,1,2,50,364",
        "10,2015-11-08 17:43:50,2,3,66,311,48189,2660.9654,51,0,0,10,2016-01-05,2016-01-06,2,0,1,12267,6,2,50,1230"
      )
    )

    Assert.assertEquals(hotelSearchService.perform(rdd).length, 0)
  }

}
