package com.vngrs.ligthningtalk.typevariance

trait Reader[+A] {
  def read(j: Json): Option[A]
}

object Reader {
  implicit val booleanReader: Reader[Boolean] = {
    case JBool(b) => Some(b)
    case _        => None
  }

  implicit val bigDecimalReader: Reader[BigDecimal] = {
    case JNum(bd) => Some(bd)
    case _        => None
  }

  implicit val stringReader: Reader[String] = {
    case JStr(s) => Some(s)
    case _       => None
  }

  implicit val mapReader: Reader[Map[String, Json]] = {
    case JObj(m) => Some(m)
    case _       => None
  }

  implicit val listReader: Reader[List[Json]] = {
    case JArr(l) => Some(l)
    case _       => None
  }
}
