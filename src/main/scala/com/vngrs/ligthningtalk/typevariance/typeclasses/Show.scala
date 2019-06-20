package com.vngrs.ligthningtalk.typevariance.typeclasses

trait Show[A] {
  // Works in consuming position because `A` is invariant
  def show(a: A): String

  // Works in producing position because `A` is invariant
  def test: A = ???
}
