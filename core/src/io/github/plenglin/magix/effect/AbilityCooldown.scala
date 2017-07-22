package io.github.plenglin.magix.effect

import io.github.plenglin.magix.entity.Entity

class AbilityCooldown(target: Entity) extends EntityEffect(target) {
  override val hidden: Boolean = true

  override def onAdded(): Unit = {

  }

  override def onApply(): Unit = ???

  override def shouldRemove(): Boolean = ???

  override def onRemove(): Unit = ???

  override def addedHP(): Double = ???

  override def addedArmor(): Double = ???
}
