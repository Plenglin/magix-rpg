package io.github.plenglin.magix.entity

import com.badlogic.gdx.graphics.g2d.{SpriteBatch, TextureRegion}

trait TexturedEntity extends Entity {

  var texture: TextureRegion

  override def draw(batch: SpriteBatch): Unit = {
    batch.draw(texture, pos.x, pos.y, 1, 1)
  }

}
