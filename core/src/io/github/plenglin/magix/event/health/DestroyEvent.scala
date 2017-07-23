package io.github.plenglin.magix.event.health

import io.github.plenglin.magix.types.Damageable

class DestroyEvent extends HealthEvent {

  override def onTrigger(target: Damageable): Unit = {
    target.destroy()
  }

}
