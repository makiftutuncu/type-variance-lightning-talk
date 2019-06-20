package com.vngrs.ligthningtalk.typevariance.animals

import com.vngrs.ligthningtalk.typevariance.{JBool, JNum, JObj, JStr}

object Data {
  val cat  = Cat("Cookie", "grey")
  val bird = Bird("Tweetie", 3)
  val fish = Fish("Nemo", male = true)

  val catJson  = JObj(Map("name" -> JStr(cat.name),  "fur"  -> JStr(cat.fur)))
  val birdJson = JObj(Map("name" -> JStr(bird.name), "age"  -> JNum(bird.age)))
  val fishJson = JObj(Map("name" -> JStr(fish.name), "male" -> JBool(fish.male)))
}
