package io.github.plenglin.magix.types
import java.util.logging.Logger

import com.badlogic.gdx.graphics.g2d.{Sprite, SpriteBatch, TextureRegion}
import com.badlogic.gdx.math.Vector2

trait TexturedDrawable extends Drawable {

  private val logger = Logger.getLogger(getClass.getName)

  def textureRegion: TextureRegion
  def drawPos: Vector2
  def dimensions: Vector2
  val center: Boolean = false

  val sprite = new Sprite()

  override def draw(batch: SpriteBatch): Unit = {
    logger.info(this.toString)
    sprite.setRegion(textureRegion)
    sprite.setSize(dimensions.x, dimensions.y)
    if (center) {
      sprite.setCenter(drawPos.x, drawPos.y)
    } else {
      sprite.setPosition(drawPos.x, drawPos.y)
    }
    sprite.draw(batch)
  }

  override def layer: Float = {
    sprite.getY()
  }

}
