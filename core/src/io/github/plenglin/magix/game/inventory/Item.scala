package io.github.plenglin.magix.game.inventory

import io.github.plenglin.magix.render.Drawable

abstract class Item(var name: String, val volume: Double, val weight: Double, var stack: Int = 32) extends Drawable {

  def desc: String = ""

}
