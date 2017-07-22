package io.github.plenglin.magix.event.entity.hp

import io.github.plenglin.magix.Damageable
import io.github.plenglin.magix.entity.Entity
import io.github.plenglin.magix.event.entity.{DamageSource, EntityDestroyedEvent, EntityEvent}

class HealthEvent(var change: Double, var damageSource: DamageSource) extends EntityEvent {
  override def onTrigger(target: Entity): Unit = target match {
    case e: Entity => {
      e.applyHealthChange(change)
      if (e.isDead) {
        e.eventQueue += new EntityDestroyedEvent()
      }
    }
    case t => throw new IllegalArgumentException(s"HealthEvent only works on LivingEntities, but $t is not one")
  }
}
