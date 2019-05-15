package com.kozitski.spark.runner

import com.kozitski.spark.runner.impl.{Task1Runner, Task2Runner, Task3Runner}
import org.apache.commons.lang3.math.NumberUtils

/*
  Command line args
    first: number of running task
    second: input file path
*/
class ArgsHandler {

  def perform(args : Array[String]): Unit = {

    if(args != null && args.length <= NumberUtils.INTEGER_ONE){
      System.err.println("You necessary must define [number of task] and [input file path]")
      System.exit(NumberUtils.INTEGER_ONE)
    }
    else if(args != null && args.length >= ArgsHandler.WITH_PATH_ARGS_SIZE && args(NumberUtils.INTEGER_ONE) != null){

      args(NumberUtils.INTEGER_ZERO) match {
        case ArgsHandler.FIRST_TASK_ARG => (new Task1Runner).run(args(NumberUtils.INTEGER_ONE))
        case ArgsHandler.SECOND_TASK_ARG => (new Task2Runner).run(args(NumberUtils.INTEGER_ONE))
        case ArgsHandler.THIRD_TASK_ARG => (new Task3Runner).run(args(NumberUtils.INTEGER_ONE))
      }

    }
    else {
      System.err.println("Incorrect args: " + args)
      System.exit(NumberUtils.INTEGER_ONE)
    }

  }

}
object ArgsHandler{
  val WITH_PATH_ARGS_SIZE = 2
  val FIRST_TASK_ARG = "1"
  val SECOND_TASK_ARG = "2"
  val THIRD_TASK_ARG = "3"
}
