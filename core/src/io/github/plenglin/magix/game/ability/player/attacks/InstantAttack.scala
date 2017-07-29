package io.github.plenglin.magix.game.ability.player.attacks

import io.github.plenglin.magix.game.ability.exception.{InvalidTargetException, TargetRangeException}
import io.github.plenglin.magix.game.ability.player.TargetedPlayerAbility
import io.github.plenglin.magix.game.event.health.ChangeHealthEvent
import io.github.plenglin.magix.game.types.{Damageable, Targetable}

abstract class InstantAttack extends TargetedPlayerAbility {

  def damage: Double

  override def canTarget(target: Targetable): Boolean = {
    target.isInstanceOf[Damageable]
  }

  override def activate(target: Targetable): Unit = {
    if (source.pos.dst2(target.pos) > range2) {
      throw new TargetRangeException()
    }
    target match {
      case d: Damageable => d.damageQueue += new ChangeHealthEvent(-damage, this)
      case _ => throw new InvalidTargetException(target)
    }
  }

  def range2: Float = Float.MaxValue
}
