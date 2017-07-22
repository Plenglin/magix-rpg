package io.github.plenglin.magix.entity.projectile

import io.github.plenglin.magix.Targetable
import io.github.plenglin.magix.entity.{Entity, TexturedEntity}
import io.github.plenglin.magix.event.entity.hp.HealthEvent

abstract class HomingProjectile(val source: Entity, val projectileTarget: Targetable) extends Entity(source.pos.cpy) {

  var damage: Double

  override def onUpdate(dt: Float): Unit = {
    moveTowardsTarget(dt)
  }

  override def onDestroy(): Unit = projectileTarget match {
    case e: Entity => e.eventQueue += new HealthEvent(damage, this)
  }

}
