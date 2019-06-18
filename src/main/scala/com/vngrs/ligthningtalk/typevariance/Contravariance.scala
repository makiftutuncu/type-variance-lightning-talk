package com.vngrs.ligthningtalk.typevariance

/*

Contravariance

- If `A <: B` then `F[B] <: F[A]`
- Defined with `-` before the type parameter, i.e `Writer[-A]`
- Type parameter must be in consuming position, i.e method parameters `def write(a: A): Json`

*/
object Contravariance {
  def write[A <: Animal](writer: Writer[A], a: A): Json = writer.write(a)

  val cat  = Cat("Cookie", "grey")
  val bird = Bird("Tweetie", 3)
  val fish = Fish("Nemo", male = true) // is it? 😄

  def main(args: Array[String]): Unit = {
    /*

    `write()` works with both `Writer[Animal]` and `Writer[Cat]`

    `Writer[Animal] <: Writer[Cat]` because `Cat <: Animal`

     */
    println(s"Cat as Animal: ${write(Animal.writer, cat)}") // {"name":"Cookie"}
    println(s"Cat as Cat   : ${write(Cat.writer, cat)}")    // {"name":"Cookie","fur":"grey"}

    // Following do not compile because type relations do not match!

    // write(Cat.writer, fish)
    // write(Fish.writer, cat)
  }
}
