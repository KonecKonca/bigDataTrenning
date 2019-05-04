package by.epam.kafkareader

import org.apache.spark.sql.execution.streaming.MemoryStream
import org.apache.spark.sql.types._
import org.apache.spark.sql.{DataFrame, Encoders, SparkSession}
import org.scalatest.{BeforeAndAfterAll, FunSuite}

class MainTest extends FunSuite with BeforeAndAfterAll{

  var spark: SparkSession = _

  override def beforeAll() {
    spark = SparkSession.builder()
        .appName("kafka reader test")
        .master("local[*]")
        .getOrCreate()
  }

  override def afterAll(): Unit = {
    spark.stop()
  }

  test("Main.splitIntoColumns test number of columns") {

    val df = createMockStream()

    val transformedDF = Main.splitIntoColumns(df)

    assertResult(24)(transformedDF.columns.length)

  }

  test("Main.splitIntoColumns test schema") {

    val expectedSchema = new StructType(Array(
      StructField("date_time", TimestampType, nullable = true),
      StructField("site_name", IntegerType, nullable = true),
      StructField("posa_continent", IntegerType, nullable = true),
      StructField("user_location_country", IntegerType, nullable = true),
      StructField("user_location_region", IntegerType, nullable = true),
      StructField("user_location_city", IntegerType, nullable = true),
      StructField("orig_destination_distance", DoubleType, nullable = true),
      StructField("user_id", IntegerType, nullable = true),
      StructField("is_mobile", IntegerType, nullable = true),
      StructField("is_package", IntegerType, nullable = true),
      StructField("channel", IntegerType, nullable = true),
      StructField("srch_ci", DateType, nullable = true),
      StructField("srch_co", DateType, nullable = true),
      StructField("srch_adults_cnt", IntegerType, nullable = true),
      StructField("srch_children_cnt", IntegerType, nullable = true),
      StructField("srch_rm_cnt", IntegerType, nullable = true),
      StructField("srch_destination_id", IntegerType, nullable = true),
      StructField("srch_destination_type_id", IntegerType, nullable = true),
      StructField("is_booking", IntegerType, nullable = true),
      StructField("cnt", LongType, nullable = true),
      StructField("hotel_continent", IntegerType, nullable = true),
      StructField("hotel_country", IntegerType, nullable = true),
      StructField("hotel_market", IntegerType, nullable = true),
      StructField("hotel_cluster", IntegerType, nullable = true)
    ))

    val df = createMockStream()

    val transformedDF = Main.splitIntoColumns(df)

    val actualSchema = transformedDF.schema

    assertResult(expectedSchema)(actualSchema)

  }

  def createMockStream() : DataFrame = {

    val s = spark

    import s.implicits._

    val stream = new MemoryStream[String](42, spark.sqlContext)

    stream.addData(Seq(
      "2014-08-11 07:46:59,2,3,66,348,48862,2234.2641,12,0,1,9,2014-08-27,2014-08-31,2,0,1,8250,1,0,3,2,50,628,1",
      "2014-07-16 10:00:06,2,3,66,189,10067,,501,0,0,2,2014-08-01,2014-08-02,2,0,1,8267,1,0,1,2,50,675,98",
      "2014-01-17 06:24:56,2,3,66,318,22418,420.6642,756,0,1,9,2014-04-17,2014-04-20,2,0,1,8291,1,0,5,2,50,191,18",
      "2014-02-27 17:44:23,2,3,66,318,52078,,756,0,1,4,2014-04-17,2014-04-19,2,0,1,8291,1,0,1,2,50,191,2",
      "2014-11-22 20:55:38,30,4,195,991,47725,,1048,1,0,9,2015-06-26,2015-06-28,2,0,1,8803,1,0,1,3,151,1197,5"
    ))

    stream.toDF()

  }

}
