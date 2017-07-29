package io.github.plenglin.magix.game.event.health

import io.github.plenglin.magix.game.event.entity.HealthChangeSource
import io.github.plenglin.magix.game.types.Damageable

/**
  * Any event that involves a change of health.
  *
  * @param source where the change came from
  */
abstract class HealthEvent(val source: HealthChangeSource) {

  def onTrigger(target: Damageable)

}
