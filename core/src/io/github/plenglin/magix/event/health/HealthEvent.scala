package io.github.plenglin.magix.event.health

import io.github.plenglin.magix.types.Damageable

abstract class HealthEvent {

  def onTrigger(target: Damageable)

}
