package com.vngrs.ligthningtalk.typevariance

trait Writer[-A] {
  def write(a: A): Json
}

object Writer {
  implicit val booleanWriter: Writer[Boolean]       = (b: Boolean)           => JBool(b)
  implicit val bigDecimalWriter: Writer[BigDecimal] = (bd: BigDecimal)       => JNum(bd)
  implicit val stringWriter: Writer[String]         = (s: String)            => JStr(s)
  implicit val mapWriter: Writer[Map[String, Json]] = (m: Map[String, Json]) => JObj(m)
  implicit val listWriter: Writer[List[Json]]       = (l: List[Json])        => JArr(l)
}
