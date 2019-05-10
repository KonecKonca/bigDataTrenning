package com.kozitski.spark

/*
Application runner
 */
object Runner extends App {

  new KafkaStreamingWriter().start()

}
