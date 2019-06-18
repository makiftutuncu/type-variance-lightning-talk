package com.vngrs.ligthningtalk.typevariance

sealed trait Json { self =>
  def as[A](implicit reader: Reader[A]): Option[A] = reader.read(self)

  def show: String

  override def toString: String = show
}

final case class JBool(b: Boolean) extends Json {
  override def show: String = b.toString
}

final case class JNum(bd: BigDecimal) extends Json {
  override def show: String = bd.toString
}

final case class JStr(s: String) extends Json {
  override def show: String = s""""${s.replaceAll(""""""", """\"""")}""""
}

final case class JObj(m: Map[String, Json]) extends Json {
  override def show: String = m.toList.map { case (k, v) => s""""$k":${v.show}""" }.mkString("{", ",", "}")
}

final case class JArr(l: List[Json]) extends Json {
  override def show: String = l.map(j => j.show).mkString("[", ",", "]")
}
