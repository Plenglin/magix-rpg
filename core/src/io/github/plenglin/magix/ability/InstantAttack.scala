package io.github.plenglin.magix.ability


import io.github.plenglin.magix.Targetable
import io.github.plenglin.magix.entity.Entity

abstract class InstantAttack(source: Entity) extends Ability(source) {

  override def canTarget(target: Option[Targetable]): Boolean = {

  }

  override def onTriggered(target: Option[Targetable]): Unit = {

  }

}
