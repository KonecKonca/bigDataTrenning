package com.kozitski.spark

import com.kozitski.spark.runner.ArgsHandler

object Application extends App {

  (new ArgsHandler).perform(args = args)

}