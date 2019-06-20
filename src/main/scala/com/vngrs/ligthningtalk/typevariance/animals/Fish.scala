package com.vngrs.ligthningtalk.typevariance.animals

import com.vngrs.ligthningtalk.typevariance._
import com.vngrs.ligthningtalk.typevariance.typeclasses.{Read, Show, Write}

case class Fish(override val name: String, male: Boolean) extends Animal

object Fish {
  val read: Read[Fish] = { json =>
    val map  = json.as[Map[String, Json]]
    val name = map.getOrElse("name", throw new Exception(s"No 'name' in $json")).as[String]
    val male = map.getOrElse("male", throw new Exception(s"No 'male' in $json")).as[Boolean]

    Fish(name, male)
  }

  val write: Write[Fish] = (f: Fish) => JObj(Map("name" -> JStr(f.name), "male" -> JBool(f.male)))

  val show: Show[Fish] = (f: Fish) => s"${if (f.male) "Male" else "Female"} fish named ${f.name}"
}
