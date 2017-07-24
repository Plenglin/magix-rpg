package io.github.plenglin.magix.ability

import com.badlogic.gdx.utils.TimeUtils
import io.github.plenglin.magix.ability.exception.AbilityCooldownException
import io.github.plenglin.magix.effect.AbilityCooldown
import io.github.plenglin.magix.entity.Entity
import io.github.plenglin.magix.event.entity.DamageSource

/**
  * Something that an entity can activate.
  */
abstract class Ability extends DamageSource {

  var source: Entity = _

  /**
    * The next time this ability can be activated, in milliseconds
    */
  var nextActivation: Long = 0

  /**
    * Period, in milliseconds, between attacks
    */
  def cooldown: Long

  /**
    * Called when added to the entity.
    */
  def onInit(entity: Entity): Unit = {
    source = entity
    nextActivation = 0
  }

  def cooldownComplete: Boolean = {
    System.currentTimeMillis() >= nextActivation
  }

  /**
    * You should call this before activation. It will throw exceptions if the ability can't activate, so catch them.
    */
  def preActivation(): Unit = {
    if (!cooldownComplete) {
      throw new AbilityCooldownException(this)
    }
  }

  /**
    * You must call this after the ability is successfully activated.
    */
  def finishActivation(): Unit = {
    source.effects += new AbilityCooldown(this)
    nextActivation = System.currentTimeMillis() + cooldown
  }

}
