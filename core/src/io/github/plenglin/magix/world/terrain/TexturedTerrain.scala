package io.github.plenglin.magix.world.terrain

import com.badlogic.gdx.graphics.g2d.{SpriteBatch, TextureRegion}
import io.github.plenglin.magix.Constants

trait TexturedTerrain extends Terrain {
  val texture: TextureRegion

  override def draw(batch: SpriteBatch, cellX: Int, cellY: Int): Unit = {
    batch.draw(texture, cellX, cellY, 1, 1)
  }
}
