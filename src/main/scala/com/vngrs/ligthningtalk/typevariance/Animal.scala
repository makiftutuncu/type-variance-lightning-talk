package com.vngrs.ligthningtalk.typevariance

trait Animal {
  val name: String

  override def toString: String = s"Animal named $name"
}

object Animal {
  val reader: Reader[Animal] = { json =>
    for {
      map <- json.as[Map[String, Json]]
      n   <- map.get("name").flatMap(_.as[String])
    } yield {
      new Animal { override val name: String = n }
    }
  }

  val writer: Writer[Animal] = (a: Animal) => JObj(Map("name" -> JStr(a.name)))
}

case class Cat(override val name: String, fur: String) extends Animal {
  override def toString: String = s"Cat named $name with $fur fur"
}

object Cat {
  val reader: Reader[Cat] = { json =>
    for {
      map  <- json.as[Map[String, Json]]
      name <- map.get("name").flatMap(_.as[String])
      fur  <- map.get("fur").flatMap(_.as[String])
    } yield {
      Cat(name, fur)
    }
  }

  val writer: Writer[Cat] = (c: Cat) => JObj(Map("name" -> JStr(c.name), "fur" -> JStr(c.fur)))
}

case class Bird(override val name: String, age: Int) extends Animal {
  override def toString: String = s"$age year(s) old bird named $name"
}

object Bird {
  val reader: Reader[Bird] = { json =>
    for {
      map  <- json.as[Map[String, Json]]
      name <- map.get("name").flatMap(_.as[String])
      age  <- map.get("age").flatMap(_.as[BigDecimal])
    } yield {
      Bird(name, age.intValue)
    }
  }

  val writer: Writer[Bird] = (b: Bird) => JObj(Map("name" -> JStr(b.name), "age" -> JNum(b.age)))
}

case class Fish(override val name: String, male: Boolean) extends Animal {
  override def toString: String = s"${if (male) "Male" else "Female"} fish named $name"
}

object Fish {
  val reader: Reader[Fish] = { json =>
    for {
      map  <- json.as[Map[String, Json]]
      name <- map.get("name").flatMap(_.as[String])
      male <- map.get("male").flatMap(_.as[Boolean])
    } yield {
      Fish(name, male)
    }
  }

    val writer: Writer[Fish] = (f: Fish) => JObj(Map("name" -> JStr(f.name), "male" -> JBool(f.male)))
}
