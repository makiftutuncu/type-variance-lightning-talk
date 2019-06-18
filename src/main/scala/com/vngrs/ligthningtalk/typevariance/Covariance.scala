package com.vngrs.ligthningtalk.typevariance

/*

Covariance

- If `A <: B` then `F[A] <: F[B]`
- Defined with `+` before the type parameter, i.e `Reader[+A]`
- Type parameter must be in producing position, i.e return type `def read(j: Json): Option[A]`

*/
object Covariance {
  val catJson  = JObj(Map("name" -> JStr("Cookie"),  "fur"  -> JStr("grey")))
  val birdJson = JObj(Map("name" -> JStr("Tweetie"), "age"  -> JNum(3)))
  val fishJson = JObj(Map("name" -> JStr("Nemo"),    "male" -> JBool(true))) // is it? 😄

  def main(args: Array[String]): Unit = {
    /*

    `read()` works with both `Reader[Animal]` and `Reader[Bird]`

    `Reader[Bird] <: Reader[Animal]` because `Bird <: Animal`

     */

    // Works because reading an Animal with and Animal reader
    birdJson.as[Animal](Animal.reader).foreach { animal => println(s"Bird as Animal: $animal") } // Animal named Tweetie

    // Does not work because trying to read a Bird with an Animal reader
    // birdJson.as[Bird](Animal.reader)

    // Works because reading a Bird with a Bird reader
    birdJson.as[Bird](Bird.reader).foreach { bird   => println(s"Bird as Bird  : $bird") }   // 3 year(s) old bird named Tweetie
    birdJson.as[Bird](Animal.reader).foreach { bird   => println(s"Bird as Bird  : $bird") }   // 3 year(s) old bird named Tweetie


    // Following does compile because type relations are sound, bird reader is an animal reader and cat is an animal
    // However since `Bird.reader` returns `None` for `catJson`, it cannot read a Bird value.
    read(Bird.reader, catJson).foreach { animal => println(s"Cat as Bird?: $animal") }
  }
}
