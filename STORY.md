### Types in Scala

A type refers to type of a value in Scala, that is, it defines what data a value can represent. For example, `Int` is a type that represents integer numbers between -2147483648 and 2147483647.

Types can have relations. A type can extend one or more types so that it has all of the abilities the parents have (and more of its own). Because of that, when a type `A` extends a type `B`, we can say "`A` is a `B`". This is commonly known as an "is-a relation" in OOP.

Just like classes, methods and functions, types can also take parameters in Scala.

`Option` is an example of a type that takes a type parameter. It represents values that can exist or not. Think of it as a safer way to deal with null values. Here is a not-so-accurate, simpler definition:

```scala
trait Option[A] {
  def get: A
  def isEmpty: Boolean
}
```

As you can see `Option` defines a type parameter `A`. `Option` on its own is **not a type**, but **a type constructor**. When we provide it with a type, say `Int`, then we have a type called `Option[Int]`. Having type parameters means we can do generic programming.

### Type Variance in Scala

**Question:** Is there a relation between `F[A]` and `F[B]` if there is a relation between `A` and `B`?

**Answer:** There can be, it depends on the definition of the type parameter in `F` type constructor.

Relation between types can have an effect on the types built by type constructors in which the types are parameters. This happens in one of 3 following ways.

**Note:** Scala has operators representing type relationships. `A <: B` means `B` is a supertype of `A` (`A extends B`) and `A >: B` means `A` is a supertype of `B` (`B extends A`).

#### 1. Invariance

Invariance is when relationship of the type of the parameter does not affect the type built by the type constructor. Here is an example:

```scala
class Y
class X extends Y
class Foo[A]

val foo1: Foo[X] = new Foo[X]
val foo2: Foo[Y] = new Foo[Y]
```

There is no relationship between `Foo[X]` and `Foo[Y]` here even though `X <: Y`.  `Foo[X]` and `Foo[Y]` are different and completely unrelated types.

This is because type parameter `A` is in **invariant** position in type constructor `Foo`.

#### 2. Covariance

Covariance incurs a same way relationship between the type parameters to the type built by the type constructor. Here is an example:

```scala
class Y
class X extends Y
class Foo[+A]

val foo1: Foo[X] = new Foo[X]
val foo2: Foo[Y] = new Foo[Y]
```

Here `Foo[X] <: Foo[Y]` because `X <: Y`. The relationship is the same-way.  `Foo[X]` and `Foo[Y]` are different types yet they are not completely unrelated.

For a type parameter `A` to be **covariant**, we append a `+` to it and it needs to be in a **producing position**. For example, it could be used as a return value, but not as a method parameter. Failing this would cause a compile error.

#### 3. Contravariance

Contravariance incurs an opposite-way relationship between the type parameters to the type built by the type constructor. Here is an example:

```scala
class Y
class X extends Y
class Foo[-A]

val foo1: Foo[X] = new Foo[X]
val foo2: Foo[Y] = new Foo[Y]
```

Here `Foo[X] >: Foo[Y]` because `X <: Y`. The relationship is the opposite-way.  `Foo[X]` and `Foo[Y]` are different types yet they are not completely unrelated.

For a type parameter `A` to be **contravariant**, we append a `-` to it and it needs to be in a **consuming position**. For example, it could be used as a method parameter but not as a return type. Failing this would cause a compile error.

