package io.github.plenglin.magix.types

import java.util.logging.Logger

import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.{Sprite, SpriteBatch, TextureRegion}
import com.badlogic.gdx.math.{Vector2, Vector3}

trait TexturedDrawable extends Drawable {

  val center: Boolean = false
  val sprite = new Sprite()
  private val logger = Logger.getLogger(getClass.getName)

  def textureRegion: TextureRegion

  def drawPos: Vector2

  def dimensions: Vector2

  override def preDraw(): Unit = {
    sprite.setRegion(textureRegion)
    sprite.setSize(dimensions.x, dimensions.y)
    if (center) {
      sprite.setCenter(drawPos.x, drawPos.y)
      logger.info(f"$this, ${sprite.getX}, ${sprite.getY}")
    } else {
      sprite.setPosition(drawPos.x, drawPos.y)
    }
  }

  override def cull(cam: OrthographicCamera): Boolean = {
    // Check if any of the sprite's four corners are inside the camera
    val x = sprite.getX
    val y = sprite.getY
    val corners = Array[Vector2](
      new Vector2(x, y),
      new Vector2(x + sprite.getWidth, y),
      new Vector2(x, y + sprite.getHeight),
      new Vector2(x + sprite.getWidth, y + sprite.getHeight)
    )
    !corners.exists(vec => cam.frustum.pointInFrustum(new Vector3(vec, 0)))
  }

  override def draw(batch: SpriteBatch): Unit = {
    sprite.draw(batch)
  }

  override def layer: Float = {
    sprite.getY
  }

}
