package io.github.plenglin.magix.world.wall

import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Vector2
import io.github.plenglin.magix.{Damageable, Targetable}

/**
  * A wall. There is an instance for every wall.
  * @param i horizontal cell coordinate
  * @param j vertial cell coordinate
  */
abstract class Wall(val i: Int, val j: Int) extends Damageable {

  val pos: Vector2 = new Vector2(i, j)
  val targetRadius2: Float = 100

  val name: String

  override var hp: Double = maxHP

  /**
    * Draw this wall onto a `SpriteBatch`.
    * @param batch the batch to draw onto
    */
  def draw(batch: SpriteBatch): Unit

}
