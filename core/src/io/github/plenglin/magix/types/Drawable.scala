package io.github.plenglin.magix.types

import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.SpriteBatch

trait Drawable {

  def cull(cam: OrthographicCamera): Boolean

  def layer: Float

  def draw(batch: SpriteBatch)

}
