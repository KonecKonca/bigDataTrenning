package com.kozitski.spark

import com.kozitski.spark.runner.TaskRunner
import com.kozitski.spark.runner.impl.{Task1Runner, Task2Runner, Task3Runner}
import org.apache.commons.lang3.math.NumberUtils

/*
  Command line args
  first: number of running task
  second: input file path [optional] or [write hdfs to execution under hadoop file system]
*/
object App extends App {
  val FIRST_TASK_ARG = "1"
  val SECOND_TASK_ARG = "2"
  val THIRD_TASK_ARG = "3"

  val ON_HDFS_RUN = "hdfs"

  if(args != null && args.length >= 1){

    args(NumberUtils.INTEGER_ZERO) match {
      case FIRST_TASK_ARG => (new Task1Runner).run()
      case SECOND_TASK_ARG => (new Task2Runner).run()
      case THIRD_TASK_ARG => (new Task3Runner).run()
    }

  }
  else if(args != null && args.length >= 2 && args(NumberUtils.INTEGER_ONE) != null
          && args(NumberUtils.INTEGER_ONE).equals(ON_HDFS_RUN)){

    args(NumberUtils.INTEGER_ZERO) match {
      case FIRST_TASK_ARG => (new Task1Runner).run(TaskRunner.HDFS_PATH)
      case SECOND_TASK_ARG => (new Task2Runner).run(TaskRunner.HDFS_PATH)
      case THIRD_TASK_ARG => (new Task3Runner).run(TaskRunner.HDFS_PATH)
    }

  }
  else if (args != null && args.length >= 2){

    args(NumberUtils.INTEGER_ZERO) match {
      case FIRST_TASK_ARG => (new Task1Runner).run(args(NumberUtils.INTEGER_ONE))
      case SECOND_TASK_ARG => (new Task2Runner).run(args(NumberUtils.INTEGER_ONE))
      case THIRD_TASK_ARG => (new Task3Runner).run(args(NumberUtils.INTEGER_ONE))
    }

  }

}