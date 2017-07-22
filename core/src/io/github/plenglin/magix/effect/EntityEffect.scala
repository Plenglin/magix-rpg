package io.github.plenglin.magix.effect

import io.github.plenglin.magix.entity.Entity
import io.github.plenglin.magix.event.entity.EntityEvent

/**
  * An effect that can be applied to a `LivingEntity`.
  * @param target
  */
abstract class EntityEffect(var target: Entity) {

  val hidden: Boolean  // Whether this effect is to be displayed to the user

  def addedHP: Double = 0

  def addedArmor: Double = 0

  def coeffHP: Double = 1

  def onAdded(): Unit

  def onApply(): Unit

  def shouldRemove(): Boolean

  def onRemove(): Unit

  def onEvent(event: EntityEvent): Unit = {

  }

}
