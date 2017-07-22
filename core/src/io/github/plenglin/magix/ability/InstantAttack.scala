package io.github.plenglin.magix.ability


import java.util.NoSuchElementException

import com.badlogic.gdx.math.Vector2
import io.github.plenglin.magix.entity.Entity
import io.github.plenglin.magix.event.entity.hp.HealthEvent
import io.github.plenglin.magix.{Damageable, Targetable}

abstract class InstantAttack(source: Entity) extends TargetedAbility(source) {

  def damage: Double

  override def canTarget(target: Option[Targetable]): Boolean = {
    target.isInstanceOf[Damageable]
  }

  override def onTriggered(target: Targetable): Unit = try {
      target match {
        case e: Entity => e.eventQueue += new HealthEvent(damage, source)
        case d: Damageable => d.applyHealthChange(damage)
        case _ => throw new InvalidTargetException(Option(target))
      }
    } catch {
      case _: NoSuchElementException => throw new InvalidTargetException(None)
    }

}
