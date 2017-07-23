package io.github.plenglin.magix.types

import com.badlogic.gdx.math.Vector2

trait Targetable {

  /**
    * Where the object is in game coordinates
    * @return
    */
  def pos: Vector2

  /**
    * How far from the mouse this object can be and still be flagged as targeted, in screen distance squared. 0 for non-
    * targetable.
    * @return
    */
  val targetRadius2: Float = 0

}
