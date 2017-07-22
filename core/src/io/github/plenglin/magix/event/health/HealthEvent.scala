package io.github.plenglin.magix.event.health

import io.github.plenglin.magix.Damageable

abstract class HealthEvent {

  def onTrigger(target: Damageable)

}
