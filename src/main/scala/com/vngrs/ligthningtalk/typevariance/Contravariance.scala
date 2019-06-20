package com.vngrs.ligthningtalk.typevariance

import com.vngrs.ligthningtalk.typevariance.animals.{Animal, Bird, Data}
import com.vngrs.ligthningtalk.typevariance.typeclasses.Write

object Contravariance {
  def write[A <: Animal](a: A, w: Write[A]): Json = w.write(a)

  def main(args: Array[String]): Unit = {
    // All work because `Write[Animal]` is `Write[Bird]`

    val write1: Json = write[Animal](Data.bird, Animal.write)
    val write2: Json = write[Bird](Data.bird, Bird.write)
    val write3: Json = write[Bird](Data.bird, Animal.write)

    println(write1) // {"name":"Tweetie"}
    println(write2) // {"name":"Tweetie","age":3}
    println(write3) // {"name":"Tweetie"}

    // Does not compile because `write` expects `Write[Animal]` and `Write[Bird]` is not `Write[Animal]`

    // println(write[Animal](Data.bird, Bird.write))
  }
}
