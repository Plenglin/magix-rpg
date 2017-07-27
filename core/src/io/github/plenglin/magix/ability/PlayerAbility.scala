package io.github.plenglin.magix.ability

import com.badlogic.gdx.math.Vector2
import io.github.plenglin.magix.effect.AbilityCooldown
import io.github.plenglin.magix.entity.humanoid.Player
import io.github.plenglin.magix.event.entity.HealthChangeSource

/**
  * Something that an entity can activate.
  */
abstract class PlayerAbility extends HealthChangeSource {

  var source: Player = _

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
  def onInit(player: Player): Unit = {
    source = player
    nextActivation = 0
  }

  def cooldownComplete: Boolean = {
    System.currentTimeMillis() >= nextActivation
  }

  def activate(mouse: Vector2)

  /**
    * You must call this after the ability is successfully activated.
    */
  def finishActivation(): Unit = {
    source.effects += new AbilityCooldown(this)
    nextActivation = System.currentTimeMillis() + cooldown
  }

}
