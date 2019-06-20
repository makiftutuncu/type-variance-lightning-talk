package com.vngrs.ligthningtalk.typevariance

import com.vngrs.ligthningtalk.typevariance.animals.{Animal, Data, Fish}
import com.vngrs.ligthningtalk.typevariance.typeclasses.Read

object Covariance {
  def read[A <: Animal](json: Json, r: Read[A]): A = r.read(json)

  def main(args: Array[String]): Unit = {
    // All work because `Read[Fish]` is  `Read[Animal]`

    val read1: Animal = read[Animal](Data.fishJson, Animal.read)
    val read2: Animal = read[Animal](Data.fishJson, Fish.read)
    val read3: Fish   = read[Fish](Data.fishJson, Fish.read)

    println(read1) // com.vngrs.ligthningtalk.typevariance.animals.Animal$$anon$1@2f777743
    println(read2) // Fish(Nemo,true)
    println(read3) // Fish(Nemo,true)

    // Does not compile because `read` expects `Read[Fish]` and `Read[Animal]` is not `Read[Fish]`

    // println(read[Fish](Data.fishJson, Animal.read))
  }
}
