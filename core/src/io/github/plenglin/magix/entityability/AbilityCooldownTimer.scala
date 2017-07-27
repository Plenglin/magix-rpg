package io.github.plenglin.magix.entityability

import com.badlogic.gdx.utils.TimeUtils
import io.github.plenglin.magix.entity.Entity
import io.github.plenglin.magix.event.entity.HealthChangeSource

/**
  * A class for entity ability timing
  */
class AbilityCooldownTimer(var source: Entity, override var name: String, var cooldown: Long,
                           var onActivate: (Entity, AbilityCooldownTimer) => Unit) extends HealthChangeSource {

  /**
    * The next time this ability can be activated, in milliseconds
    */
  var nextActivation: Long = 0

  def this(source: Entity, name: String, cooldown: Long) = this(source, name, cooldown, (_, _) => {})

  def activateIfReady(): Unit = {
    if (TimeUtils.millis() >= nextActivation) {
      onActivate.apply(source, this)
      nextActivation = TimeUtils.millis() + cooldown
    }
  }

}
