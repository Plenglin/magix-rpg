package io.github.plenglin.magix.event.health

import io.github.plenglin.magix.event.entity.DamageSource
import io.github.plenglin.magix.types.Damageable

class HealthChangeEvent(var change: Double, var damageSource: DamageSource) extends HealthEvent {
  override def onTrigger(target: Damageable): Unit = {
    target.applyHealthChange(change)
    if (target.isDead) {
      target.damageQueue += new DestroyEvent()
    }
  }
}
