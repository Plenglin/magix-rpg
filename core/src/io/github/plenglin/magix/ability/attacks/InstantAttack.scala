package io.github.plenglin.magix.ability.attacks

import java.util.NoSuchElementException

import io.github.plenglin.magix.ability.{InvalidTargetException, TargetedAbility}
import io.github.plenglin.magix.entity.Entity
import io.github.plenglin.magix.event.health.HealthChangeEvent
import io.github.plenglin.magix.types.{Damageable, Targetable}

abstract class InstantAttack extends TargetedAbility {

  def damage: Double

  override def canTarget(target: Targetable): Boolean = {
    target.isInstanceOf[Damageable]
  }

  override def activate(target: Targetable): Unit = try {
      target match {
        case d: Damageable => d.damageQueue += new HealthChangeEvent(damage, this)
        case _ => throw new InvalidTargetException(Option(target))
      }
    } catch {
      case _: NoSuchElementException => throw new InvalidTargetException(None)
    }

}
