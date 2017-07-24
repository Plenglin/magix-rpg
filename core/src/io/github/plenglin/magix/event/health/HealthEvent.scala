package io.github.plenglin.magix.event.health

import io.github.plenglin.magix.event.entity.HealthChangeSource
import io.github.plenglin.magix.types.Damageable

/**
  * Any event that involves a change of health.
  * @param source where the change came from
  */
abstract class HealthEvent(val source: HealthChangeSource) {

  def onTrigger(target: Damageable)

}
