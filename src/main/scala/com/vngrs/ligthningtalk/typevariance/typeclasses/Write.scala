package com.vngrs.ligthningtalk.typevariance.typeclasses

import com.vngrs.ligthningtalk.typevariance._

trait Write[-A] {
  def write(a: A): Json
}

object Write {
  implicit val booleanWriter: Write[Boolean]       = (b: Boolean)           => JBool(b)
  implicit val bigDecimalWriter: Write[BigDecimal] = (bd: BigDecimal)       => JNum(bd)
  implicit val stringWriter: Write[String]         = (s: String)            => JStr(s)
  implicit val mapWriter: Write[Map[String, Json]] = (m: Map[String, Json]) => JObj(m)
  implicit val listWriter: Write[List[Json]]       = (l: List[Json])        => JArr(l)
}
