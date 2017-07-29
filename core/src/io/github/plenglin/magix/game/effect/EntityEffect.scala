package io.github.plenglin.magix.game.effect

import io.github.plenglin.magix.control.Loopable
import io.github.plenglin.magix.game.entity.Entity
import io.github.plenglin.magix.game.event.entity.EntityEvent

/**
  * An effect that can be applied to a `LivingEntity`.
  *
  * @param target the target entity
  */
abstract class EntityEffect() extends Loopable {

  var target: Entity = _

  val hidden: Boolean // Whether this effect is to be displayed to the user

  val name: String

  val desc: String = ""

  def addedArmor: Double = 0

  def addedHP: Double = 0
  def coeffHP: Double = 1

  def addedMana: Double = 0
  def coeffMana: Double = 1

  def addedManaRegen: Double = 0
  def coeffManaRegen: Double = 1

  def addedHPRegen: Double = 0
  def coeffHPRegen: Double = 1

  def init(entity: Entity): Unit = {
    target = entity
    super.init()
  }

  def onEvent(event: EntityEvent): Unit = {

  }

}
