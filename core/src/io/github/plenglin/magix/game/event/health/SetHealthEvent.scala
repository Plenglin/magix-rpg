package io.github.plenglin.magix.game.event.health

import java.util.logging.Logger

import io.github.plenglin.magix.game.event.entity.HealthChangeSource
import io.github.plenglin.magix.game.types.Damageable

/**
  * Set the health to be a specific number. Can be used to destroy a `Damageable` immediately as well.
  *
  * @param health the number
  * @param source where the change came from
  */
class SetHealthEvent(var health: Double, source: HealthChangeSource) extends HealthEvent(source) {

  private val logger = Logger.getLogger(getClass.getName)

  override def onTrigger(target: Damageable): Unit = {
    logger.fine(f"$target set to ${health}hp by $source")
    target.hp = health
  }
}
