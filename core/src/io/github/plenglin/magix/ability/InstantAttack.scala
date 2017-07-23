package io.github.plenglin.magix.ability


import java.util.NoSuchElementException

import io.github.plenglin.magix.entity.Entity
import io.github.plenglin.magix.event.health.HealthChangeEvent
import io.github.plenglin.magix.types.{Damageable, Targetable}

abstract class InstantAttack(source: Entity) extends TargetedAbility(source) {

  def damage: Double

  override def canTarget(target: Targetable): Boolean = {
    target.isInstanceOf[Damageable]
  }

  override def onTriggered(target: Targetable): Unit = try {
      target match {
        case d: Damageable => d.damageQueue += new HealthChangeEvent(damage, source)
        case _ => throw new InvalidTargetException(Option(target))
      }
    } catch {
      case _: NoSuchElementException => throw new InvalidTargetException(None)
    }

}
