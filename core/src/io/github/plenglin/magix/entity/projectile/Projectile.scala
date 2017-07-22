package io.github.plenglin.magix.entity.projectile

import io.github.plenglin.magix.entity.{Entity, TexturedEntity}
import io.github.plenglin.magix.event.entity.hp.HealthEvent

abstract class Projectile(val source: Entity, val projectileTarget: AbilityTarget) extends Entity(source.pos.cpy) {

  var damage: Double

  override def onInit(): Unit = {
    this.hp = maxHP()
  }

  override def onUpdate(dt: Float): Unit = {
    moveTowardsTarget(dt)
  }

  override def onDestroy(): Unit = projectileTarget match {
    case e: Entity => e.eventQueue += new HealthEvent(damage, this)
  }

}
