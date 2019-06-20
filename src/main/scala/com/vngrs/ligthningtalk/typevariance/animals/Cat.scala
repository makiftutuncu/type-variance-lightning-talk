package com.vngrs.ligthningtalk.typevariance.animals

import com.vngrs.ligthningtalk.typevariance._
import com.vngrs.ligthningtalk.typevariance.typeclasses.{Read, Show, Write}

case class Cat(override val name: String, fur: String) extends Animal

object Cat {
  val read: Read[Cat] = { json =>
    val map  = json.as[Map[String, Json]]
    val name = map.getOrElse("name", throw new Exception(s"No 'name' in $json")).as[String]
    val fur  = map.getOrElse("fur", throw new Exception(s"No 'fur' in $json")).as[String]

    Cat(name, fur)
  }

  val write: Write[Cat] = (c: Cat) => JObj(Map("name" -> JStr(c.name), "fur" -> JStr(c.fur)))

  val show: Show[Cat] = (c: Cat) => s"Cat named ${c.name} with ${c.fur} fur"
}
