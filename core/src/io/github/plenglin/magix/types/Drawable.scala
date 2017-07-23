package io.github.plenglin.magix.types

import com.badlogic.gdx.graphics.g2d.SpriteBatch

trait Drawable {

  def layer: Float

  def draw(batch: SpriteBatch)

}
