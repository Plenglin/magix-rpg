package io.github.plenglin.magix.ability

import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.utils.TimeUtils


/**
  * An ability that that must specify a position.
  */
abstract class PositionalAbility extends Ability {

  /**
    * Called when the ability is triggered.
    * @param pos Where to activate the ability at.
    */
  def activate(pos: Vector2)

}
