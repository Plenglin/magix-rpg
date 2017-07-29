package io.github.plenglin.magix.game.entity

import io.github.plenglin.magix.game.inventory.ItemStack

import scala.collection.mutable.ListBuffer

class Inventory(maxVolume: Double, maxWeight: Double) extends Iterable[ItemStack] {

  private val items = ListBuffer[ItemStack]()

  val totalVolume: Double = items.map(_.volume).sum
  val totalWeight: Double = items.map(_.weight).sum

  def +=(itemStack: ItemStack): Unit = {
    items += itemStack
  }

  def -=(itemStack: ItemStack): Unit = {
    items -= itemStack
  }

  override def iterator: Iterator[ItemStack] = items.iterator

}
