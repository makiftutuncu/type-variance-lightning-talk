package com.vngrs.ligthningtalk.typevariance.animals

import com.vngrs.ligthningtalk.typevariance._
import com.vngrs.ligthningtalk.typevariance.typeclasses.{Read, Show, Write}

case class Bird(override val name: String, age: Int) extends Animal

object Bird {
  val read: Read[Bird] = { json =>
    val map  = json.as[Map[String, Json]]
    val name = map.getOrElse("name", throw new Exception(s"No 'name' in $json")).as[String]
    val age  = map.getOrElse("age", throw new Exception(s"No 'age' in $json")).as[BigDecimal]

    Bird(name, age.intValue)
  }

  val write: Write[Bird] = (b: Bird) => JObj(Map("name" -> JStr(b.name), "age" -> JNum(b.age)))

  val show: Show[Bird] = (b: Bird) => s"${b.age} year(s) old bird named ${b.name}"
}
