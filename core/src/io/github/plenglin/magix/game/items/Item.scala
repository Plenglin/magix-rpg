package io.github.plenglin.magix.game.items

import com.badlogic.gdx.graphics.g2d.TextureRegion
import io.github.plenglin.magix.game.inventory.ItemStack

/**
  * A generic thing that you can put in an inventory.
 *
  * @param name the name
  * @param weight how much it weighs, in kg
  */
class Item(val id: String, var name: String, val weight: Double, val cost: Int, val category: String) {

  var desc: ItemStack => Option[String] = (_) => None

  def tooltip: Array[String] = Array()

  def texture: TextureRegion = _

}
