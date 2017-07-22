package io.github.plenglin.magix.world.wall

import com.badlogic.gdx.graphics.g2d.{SpriteBatch, TextureRegion}
import io.github.plenglin.magix.Constants

trait TexturedWall extends Wall {

  val texture: TextureRegion

  override def draw(batch: SpriteBatch): Unit = {
    batch.draw(texture, i, j, 1, 1)
  }

}
