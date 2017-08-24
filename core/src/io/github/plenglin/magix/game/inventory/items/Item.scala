package io.github.plenglin.magix.game.inventory.items

/**
  * A generic thing that you can put in an inventory.
  * @param name the name
  * @param weight how much it weighs, in kg
  */
class Item(var name: String, val weight: Double) {

  def desc: String = ""

}
