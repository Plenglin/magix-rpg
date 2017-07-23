package io.github.plenglin.magix.world.wall
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import io.github.plenglin.magix.{Assets, Constants}

class WallTree(i: Int, j: Int) extends Wall(i, j) {
  override val maxHP: Double = 200
  override val name: String = "Tree Trunk"
  override val targetRadius2: Float = 1

  val randomXOffset: Float = (math.random * Constants.randomVisualOffset).toFloat
  val randomYOffset: Float = (math.random * Constants.randomVisualOffset).toFloat

  /**
    * Draw this wall onto a `SpriteBatch`.
    *
    * @param batch the batch to draw onto
    */
  override def draw(batch: SpriteBatch): Unit = {
    batch.draw(Assets.tTree, i + randomXOffset, j + randomYOffset, 1, 2)
  }

  override def layer: Float = j
}
