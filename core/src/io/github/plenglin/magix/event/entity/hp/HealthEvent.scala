package io.github.plenglin.magix.event.entity.hp

import io.github.plenglin.magix.entity.{Entity, LivingEntity}
import io.github.plenglin.magix.event.entity.EntityEvent

class HealthEvent(var change: Double) extends EntityEvent {
  override def onTrigger(target: Entity): Unit = target match {
    case e: LivingEntity => e.hp += change
    case t => throw new IllegalArgumentException(s"HealthEvent only works on LivingEntities, but $t is not one")
  }
}
