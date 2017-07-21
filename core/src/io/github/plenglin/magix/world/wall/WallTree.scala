package io.github.plenglin.magix.world.wall
import com.badlogic.gdx.assets.AssetDescriptor
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import io.github.plenglin.magix.{Assets, Constants}

class WallTree extends Wall {
  override val durability: Double = 200
  override val name: String = "Tree Trunk"

  val randomXOffset: Float = (math.random * Constants.randomVisualOffset).toFloat
  val randomYOffset: Float = (math.random * Constants.randomVisualOffset).toFloat

  /**
    * Draw this wall onto a `SpriteBatch`.
    *
    * @param batch the batch to draw onto
    * @param cellX where the wall is
    * @param cellY where the wall is
    */
  override def draw(batch: SpriteBatch, cellX: Int, cellY: Int): Unit = {
    batch.draw(Assets.tTree, cellX + randomXOffset, cellY + randomYOffset, 1, 2)
  }
}
