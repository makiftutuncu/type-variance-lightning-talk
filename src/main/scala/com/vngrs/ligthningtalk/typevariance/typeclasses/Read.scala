package com.vngrs.ligthningtalk.typevariance.typeclasses

import com.vngrs.ligthningtalk.typevariance._

trait Read[+A] {
  def read(j: Json): A

  // Does not compile because `A` is covariant and here it is in contravariant position
  // def test(a: A): Unit
}

object Read {
  implicit val booleanReader: Read[Boolean] = {
    case JBool(b) => b
    case j        => throw new Exception(s"$j is not a Boolean")
  }

  implicit val bigDecimalReader: Read[BigDecimal] = {
    case JNum(bd) => bd
    case j        => throw new Exception(s"$j is not a BigDecimal")
  }

  implicit val stringReader: Read[String] = {
    case JStr(s) => s
    case j        => throw new Exception(s"$j is not a String")
  }

  implicit val mapReader: Read[Map[String, Json]] = {
    case JObj(m) => m
    case j        => throw new Exception(s"$j is not a Map[String, Json]")
  }

  implicit val listReader: Read[List[Json]] = {
    case JArr(l) => l
    case j        => throw new Exception(s"$j is not a List[Json]")
  }
}
