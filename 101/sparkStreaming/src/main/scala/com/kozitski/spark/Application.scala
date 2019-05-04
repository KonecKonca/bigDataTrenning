package com.kozitski.spark

import org.apache.spark.streaming.{Seconds, StreamingContext}


/*
  Command line args
  first: number of running task
  second: input file path [optional] or [write hdfs to execution under hadoop file system]
*/
object Application extends App {


   val ssc = new StreamingContext("local[2]", "KafkaExample", Seconds(1))

}