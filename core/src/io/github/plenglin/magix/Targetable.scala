package io.github.plenglin.magix

import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.math.Vector2

trait Targetable {

  /**
    * Where the object is in game coordinates
    * @return
    */
  def pos: Vector2

  /**
    * How far from the mouse this object can be and still be flagged as targeted, in screen distance squared
    * @return
    */
  def targetRadius2: Float

}
