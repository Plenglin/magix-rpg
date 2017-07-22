package io.github.plenglin.magix.ability

import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.math.Vector2

/**
  * Something that an entity can activate.
  */
abstract class Ability {

  /**
    * Period, in seconds, between attacks
    */
  def cooldown: Float

  /**
    * Called when the ability is triggered.
    * @param mousePos Where the mouse is pointing when this occurs, in <b>game</b> coordinates.
    */
  def trigger(mousePos: Vector2)

}
