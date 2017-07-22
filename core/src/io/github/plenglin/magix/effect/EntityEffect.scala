package io.github.plenglin.magix.effect

import io.github.plenglin.magix.entity.Entity

/**
  * An effect that can be applied to a `LivingEntity`.
  * @param target
  */
abstract class EntityEffect(var target: Entity) {

  val hidden: Boolean  // Whether this effect is to be displayed to the user

  def onAdded(): Unit

  def onApply(): Unit

  def shouldRemove(): Boolean

  def onRemove(): Unit

  def maxHP(): Double

  def armor(): Double

}
