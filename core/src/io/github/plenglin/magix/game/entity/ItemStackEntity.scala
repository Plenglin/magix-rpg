package io.github.plenglin.magix.game.entity

import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Vector2
import io.github.plenglin.magix.game.inventory.ItemStack
import io.github.plenglin.magix.game.inventory.items.Item

class ItemStackEntity(pos: Vector2, val stack: ItemStack) extends Entity(pos) {
  val item: Item = stack.item
  override var baseHP: Double = _

  override def name: String = s"${stack.size}x${item.name}"

  /**
    * Used to determine layering.
    *
    * @return which layer it is on
    */
  override def layer: Float = {
    0
  }

  /**
    * Should we not draw this loop?
    *
    * @param cam the camera for reference
    * @return are we culling this loop?
    */
  override def cull(cam: OrthographicCamera): Boolean = {
    true
  }

  /**
    * Do the actual drawing
    *
    * @param batch the batch to draw on
    */
  override def draw(batch: SpriteBatch): Unit = {

  }

  override protected val baseSpeed: Double = 0
}
