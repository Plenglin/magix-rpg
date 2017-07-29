package io.github.plenglin.magix.effect

import io.github.plenglin.magix.entity.Entity
import io.github.plenglin.magix.event.entity.EntityEvent

/**
  * An effect that can be applied to a `LivingEntity`.
  *
  * @param target the target entity
  */
abstract class EntityEffect(var target: Entity) {

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

  def onAdded(): Unit

  def onApply(): Unit

  def shouldRemove(): Boolean

  def onRemove(): Unit

  def onEvent(event: EntityEvent): Unit = {

  }

}
