package io.github.plenglin.magix.game.ability.player

import com.badlogic.gdx.math.Vector2
import io.github.plenglin.magix.game.effect.AbilityCooldown
import io.github.plenglin.magix.game.entity.humanoid.Player
import io.github.plenglin.magix.game.event.entity.HealthChangeSource

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
    * Mana subtracted from player when activated
    */
  def manaCost: Double

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

  def enoughMana: Boolean = {
    source.mana >= this.manaCost
  }

  def activate(mouse: Vector2)

  /**
    * You must call this after the ability is successfully activated.
    */
  def finishActivation(): Unit = {
    nextActivation = System.currentTimeMillis() + cooldown
    source.mana -= this.manaCost
    source.controlLoop += new AbilityCooldown(this)
  }

}
