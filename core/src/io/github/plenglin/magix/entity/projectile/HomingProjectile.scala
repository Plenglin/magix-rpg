package io.github.plenglin.magix.entity.projectile

import java.util.logging.Logger

import io.github.plenglin.magix.Damageable
import io.github.plenglin.magix.entity.Entity
import io.github.plenglin.magix.event.health.{DestroyEvent, HealthChangeEvent}

abstract class HomingProjectile(val source: Entity, val projectileTarget: Damageable) extends Entity(source.pos.cpy) {

  private val logger = Logger.getLogger(getClass.getName)

  var damage: Double
  var hitRadius2: Float

  override def onUpdate(dt: Float): Unit = {
    target.set(projectileTarget.pos)
    moveTowardsTarget(dt)
    if (pos.dst2(projectileTarget.pos) < hitRadius2) {
      logger.info(s"$this hit, destroying target")
      this.damageQueue += new DestroyEvent()
      projectileTarget.damageQueue += new HealthChangeEvent(-damage, this)
    }
  }

}
