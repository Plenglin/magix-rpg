package io.github.plenglin.magix.world.wall
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.{SpriteBatch, TextureRegion}
import com.badlogic.gdx.math.Vector2
import io.github.plenglin.magix.types.TexturedDrawable
import io.github.plenglin.magix.{Assets, Constants}

class WallTree(i: Int, j: Int) extends Wall(i, j) with TexturedDrawable {
  override val maxHP: Double = 200
  override val name: String = "Tree Trunk"
  override val targetRadius2: Float = 1

  val randomXOffset: Float = (math.random * Constants.randomVisualOffset).toFloat
  val randomYOffset: Float = (math.random * Constants.randomVisualOffset).toFloat

  override def layer: Float = j

  override def textureRegion: TextureRegion = Assets.tTree

  override def drawPos: Vector2 = new Vector2(i + randomXOffset, j + randomYOffset)

  override val dimensions: Vector2 = new Vector2(1, 2)
}
