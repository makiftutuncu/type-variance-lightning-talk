package com.vngrs.ligthningtalk.typevariance

trait Animal {
  val name: String
}

object Animal {
  val read: Read[Animal] = { json =>
    val map = json.as[Map[String, Json]]
    val n   = map.getOrElse("name", throw new Exception(s"No 'name' in $json")).as[String]

    new Animal { override val name: String = n }
  }

  val write: Write[Animal] = (a: Animal) => JObj(Map("name" -> JStr(a.name)))

  val show: Show[Animal] = (a: Animal) => s"Animal named ${a.name}"
}

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

case class Bird(override val name: String, age: Int) extends Animal

object Bird {
  val read: Read[Bird] = { json =>
    val map  = json.as[Map[String, Json]]
    val name = map.getOrElse("name", throw new Exception(s"No 'name' in $json")).as[String]
    val age  = map.getOrElse("age", throw new Exception(s"No 'age' in $json")).as[BigDecimal]

    Bird(name, age.intValue)
  }

  val write: Write[Bird] = (b: Bird) => JObj(Map("name" -> JStr(b.name), "age" -> JNum(b.age)))

  val show: Show[Bird] = (b: Bird) => s"${b.age} year(s) old bird named ${b.name}"
}

case class Fish(override val name: String, male: Boolean) extends Animal

object Fish {
  val read: Read[Fish] = { json =>
    val map  = json.as[Map[String, Json]]
    val name = map.getOrElse("name", throw new Exception(s"No 'name' in $json")).as[String]
    val male = map.getOrElse("male", throw new Exception(s"No 'male' in $json")).as[Boolean]

    Fish(name, male)
  }

  val write: Write[Fish] = (f: Fish) => JObj(Map("name" -> JStr(f.name), "male" -> JBool(f.male)))

  val show: Show[Fish] = (f: Fish) => s"${if (f.male) "Male" else "Female"} fish named ${f.name}"
}
