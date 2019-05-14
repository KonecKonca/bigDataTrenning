package com.kozitski.spark

import org.scalatest.{BeforeAndAfterAll, FunSuite}

class MainTest extends FunSuite with BeforeAndAfterAll{

  test("Main test") {
    new KafkaStreamingWriter().start()
  }

}
