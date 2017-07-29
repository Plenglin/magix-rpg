package io.github.plenglin.magix.game.inventory

class ItemStack(val item: Item, private var _size: Int) {

  def volume: Double = item.volume * size

  def weight: Double = item.weight * size

  def size: Int = _size

  def size_=(value: Int): Unit = {
    if (value > item.stack || value < 1) {
      throw new IllegalArgumentException
    }
    _size = value
  }

}
