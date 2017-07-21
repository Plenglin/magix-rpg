package io.github.plenglin.magix.world.wall
import com.badlogic.gdx.assets.AssetDescriptor
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import io.github.plenglin.magix.{Assets, Constants}

class WallTree extends Wall {
  override val durability: Double = 200
  override val name: String = "Tree Trunk"

  val randomXOffset: Int = (math.random * Constants.randomVisualOffset).toInt
  val randomYOffset: Int = (math.random * Constants.randomVisualOffset).toInt

  /**
    * Draw this wall onto a `SpriteBatch`.
    *
    * @param batch the batch to draw onto
    * @param cellX where the wall is
    * @param cellY where the wall is
    */
  override def draw(batch: SpriteBatch, cellX: Int, cellY: Int): Unit = {
    batch.draw(Assets.tTree, cellX * Constants.cellPixelSize + randomXOffset, cellY * Constants.cellPixelSize + randomYOffset, 1, 2)
  }
}
