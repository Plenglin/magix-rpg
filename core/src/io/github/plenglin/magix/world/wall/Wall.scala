package io.github.plenglin.magix.world.wall

import com.badlogic.gdx.graphics.g2d.SpriteBatch

abstract class Wall {

  val durability: Double

  val name: String

  var hp: Double = durability

  /**
    * Draw this wall onto a `SpriteBatch`.
    * @param batch the batch to draw onto
    * @param cellX where the wall is
    * @param cellY where the wall is
    */
  def draw(batch: SpriteBatch, cellX: Int, cellY: Int): Unit

}
