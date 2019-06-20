package com.vngrs.ligthningtalk.typevariance.animals

import com.vngrs.ligthningtalk.typevariance._
import com.vngrs.ligthningtalk.typevariance.typeclasses.{Read, Show, Write}

trait Animal {
  val name: String
}

object Animal {
  val read: Read[Animal] = { json =>
    val map = json.as[Map[String, Json]]
    val n   = map.getOrElse("name", throw new Exception(s"No 'name' in $json")).as[String]

    new Animal { override val name: String = n }
  }

  val write: Write[Animal] = (a: Animal) => JObj(Map("name" -> JStr(a.name)))

  val show: Show[Animal] = (a: Animal) => s"Animal named ${a.name}"
}
