package io.github.plenglin.magix.ability.attacks

import io.github.plenglin.magix.ability.TargetedAbility
import io.github.plenglin.magix.ability.exception.InvalidTargetException
import io.github.plenglin.magix.event.health.ChangeHealthEvent
import io.github.plenglin.magix.types.{Damageable, Targetable}

abstract class InstantAttack extends TargetedAbility {

  def damage: Double

  override def canTarget(target: Targetable): Boolean = {
    target.isInstanceOf[Damageable]
  }

  override def activate(target: Targetable): Unit = {
    target match {
      case d: Damageable => d.damageQueue += new ChangeHealthEvent(damage, this)
      case _ => throw new InvalidTargetException(target)
    }
  }
}
