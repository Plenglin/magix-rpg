package io.github.plenglin.magix.effect

import io.github.plenglin.magix.entity.Entity

class AttackCooldown(target: Entity) extends EntityEffect(target) {
  override val hidden: Boolean = _

  override def onAdded(): Unit = ???

  override def onApply(): Unit = ???

  override def shouldRemove(): Boolean = ???

  override def onRemove(): Unit = ???

  override def maxHP(): Double = ???

  override def armor(): Double = ???
}
