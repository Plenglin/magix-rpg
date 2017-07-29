package io.github.plenglin.magix.game.world.terrain

import com.badlogic.gdx.graphics.g2d.SpriteBatch

/**
  * A floor. There is one instance for all terrains of the same type.
  */
abstract class Terrain {

  val speed: Double // How fast an entity moves over it

  val name: String

  def draw(batch: SpriteBatch, cellX: Int, cellY: Int): Unit

}
