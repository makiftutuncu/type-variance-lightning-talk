package com.vngrs.ligthningtalk.typevariance

import com.vngrs.ligthningtalk.typevariance.animals.{Animal, Cat, Data}
import com.vngrs.ligthningtalk.typevariance.typeclasses.Show

object Invariance {
  def show[A <: Animal](a: A, s: Show[A]): String = s.show(a)

  def main(args: Array[String]): Unit = {
    val show1: String = show[Animal](Data.cat, Animal.show)
    val show2: String = show[Cat](Data.cat, Cat.show)

    println(show1) // Animal named Cookie
    println(show2) // Cat named Cookie with grey fur

    // Does not compile because `Show[Cat]` is not `Show[Animal]`

    // println(show[Animal](Data.cat, Cat.show))
  }
}
