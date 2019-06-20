package com.vngrs.ligthningtalk.typevariance.typeclasses

trait Show[A] {
  def show(a: A): String
}
