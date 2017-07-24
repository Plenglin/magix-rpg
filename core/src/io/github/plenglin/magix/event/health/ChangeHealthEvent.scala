package io.github.plenglin.magix.event.health

import java.util.logging.Logger

import io.github.plenglin.magix.event.entity.HealthChangeSource
import io.github.plenglin.magix.types.Damageable

/**
  * Change health to be a certain amount
  * @param change amount to change (positive for heal, negative for damage)
  * @param source where the change came from
  */
class ChangeHealthEvent(var change: Double, source: HealthChangeSource) extends HealthEvent(source) {

  private val logger = Logger.getLogger(getClass.getName)

  override def onTrigger(target: Damageable): Unit = {
    logger.fine(f"$target received ${change}hp from $source")
    target.applyHealthChange(change)
  }
}
