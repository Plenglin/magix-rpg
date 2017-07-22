package io.github.plenglin.magix.entity

import com.badlogic.gdx.math.Vector2
import io.github.plenglin.magix.effect.EntityEffect

import scala.collection.mutable.ListBuffer


/**
  * An entity with a health bar.
  * @param pos where it is
  */
abstract class LivingEntity(pos: Vector2) extends Entity(pos) {

  var effects: ListBuffer[EntityEffect] = ListBuffer()

  var baseHP: Double
  var baseArmor: Double = 0
  var hp: Double

  def maxHP(): Double = {
    baseHP + effects.map{_.maxHP()}.sum
  }

  def armor(): Double = {
    baseArmor + effects.map{_.armor()}.sum
  }

}
