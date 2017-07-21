package io.github.plenglin.magix.effect

import io.github.plenglin.magix.entity.LivingEntity

/**
  * An effect that can be applied to a `LivingEntity`.
  * @param target
  */
abstract class LivingEntityEffect(var target: LivingEntity) {

  def onAdded(): Unit

  def onApply(): Unit

  def shouldRemove(): Boolean

  def onRemove(): Unit

  def maxHP(): Double

  def armor(): Double

}
