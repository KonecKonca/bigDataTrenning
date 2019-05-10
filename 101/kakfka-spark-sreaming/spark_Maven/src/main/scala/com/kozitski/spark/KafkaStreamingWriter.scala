package com.kozitski.spark

import org.apache.kafka.clients.consumer.ConsumerRecord
import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.spark.SparkConf
import org.apache.spark.streaming.dstream.InputDStream
import org.apache.spark.streaming.kafka010.ConsumerStrategies.Subscribe
import org.apache.spark.streaming.kafka010.LocationStrategies.PreferConsistent
import org.apache.spark.streaming.kafka010._
import org.apache.spark.streaming.{Seconds, StreamingContext}

/*
Kafka-topic reader and into hdfs writer
 */
class KafkaStreamingWriter{

  private val kafkaParams = Map[String, Object](
    KafkaStreamingWriter.BOOTSTRAP_SERVER -> KafkaStreamingWriter.BOOTSTRAP_SERVER_VALUE,
    KafkaStreamingWriter.KEY_DESERIALIZER -> classOf[StringDeserializer],
    KafkaStreamingWriter.VALUE_DESERIALIZER -> classOf[StringDeserializer],
    KafkaStreamingWriter.GROUP_ID -> KafkaStreamingWriter.GROUP_VALUE,
    KafkaStreamingWriter.AUTO_RESET -> KafkaStreamingWriter.AUTO_RESET_VALUE,
    KafkaStreamingWriter.ENABLE_AUTO_COMMIT -> (false: java.lang.Boolean)
  )

  /*
  Start kafka stream reading and writing
   */
  def start(): Unit = {
    val conf: SparkConf = new SparkConf().setAppName(KafkaStreamingWriter.APP_NAME)
    val ssc = new StreamingContext(conf, Seconds(KafkaStreamingWriter.BATCH_TIME))
    val topics = Array(KafkaStreamingWriter.TOPIC_NAME)
    val stream = KafkaUtils.createDirectStream[String, String](ssc, PreferConsistent, Subscribe[String, String](topics, kafkaParams))

    write(stream, ssc)
  }

  /*
  Write to from kafka stream into hdfs
   */
  private def write(stream: InputDStream[ConsumerRecord[String, String]], ssc: StreamingContext): Unit = {
    stream.foreachRDD(rdd => rdd.saveAsTextFile(KafkaStreamingWriter.OUTPUT_LOCATION))

    ssc.start()
    ssc.awaitTermination()
  }

}

object KafkaStreamingWriter {
  /*
  Kafka config constants
   */
  val BOOTSTRAP_SERVER = "bootstrap.servers"
  val BOOTSTRAP_SERVER_VALUE = "sandbox-hdp.hortonworks.com:6667"
  val KEY_DESERIALIZER = "key.deserializer"
  val VALUE_DESERIALIZER = "value.deserializer"
  val GROUP_ID = "group.id"
  val GROUP_VALUE = "newGroup"
  val AUTO_RESET = "auto.offset.reset"
  val AUTO_RESET_VALUE = "latest"
  val ENABLE_AUTO_COMMIT = "enable.auto.commit"

  /*
  Application config constants
   */
  val APP_NAME = "KafkaReader"
  val BATCH_TIME = 3
  val TOPIC_NAME = "sparkTopic"
  val OUTPUT_LOCATION = "hdfs://sandbox-hdp.hortonworks.com:8020/user/maria_dev/kafka/test2/kafkaOut"
}