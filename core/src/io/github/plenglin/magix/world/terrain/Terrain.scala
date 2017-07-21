package io.github.plenglin.magix.world.terrain

import com.badlogic.gdx.graphics.g2d.SpriteBatch

abstract class Terrain {

  val speed: Double  // How fast an entity moves over it

  val name: String

  def draw(batch: SpriteBatch, cellX: Int, cellY: Int): Unit

}
