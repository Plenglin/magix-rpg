package io.github.plenglin.magix.render

import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.SpriteBatch

/**
  * Something that is to be rendered on the screen.
  */
trait Drawable {

  /**
    * Used to determine layering.
    *
    * @return which layer it is on
    */
  def layer: Float

  /**
    * Called before cull and draw. Use to update.
    */
  def preDraw(): Unit = {}

  /**
    * Should we not draw this loop?
    *
    * @param cam the camera for reference
    * @return are we culling this loop?
    */
  def cull(cam: OrthographicCamera): Boolean

  /**
    * Do the actual drawing
    *
    * @param batch the batch to draw on
    */
  def draw(batch: SpriteBatch)

}
