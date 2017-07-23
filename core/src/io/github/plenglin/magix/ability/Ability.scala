package io.github.plenglin.magix.ability

import io.github.plenglin.magix.entity.Entity
import io.github.plenglin.magix.event.entity.DamageSource

/**
  * Something that an entity can activate.
  */
abstract class Ability extends DamageSource {

  var source: Entity = _

  /**
    * Period, in seconds, between attacks
    */
  def cooldown: Float

  /**
    * Called when added to the entity.
    */
  def onInit(entity: Entity): Unit = {
    source = entity
  }

}
