package io.github.plenglin.magix.game.inventory

import io.github.plenglin.magix.game.EntityProperty
import io.github.plenglin.magix.game.entity.Entity

import scala.collection.mutable.ListBuffer

class Inventory(owner: Entity) extends Iterable[ItemStack] {

  private val items = ListBuffer[ItemStack]()
  private def maxWeight = owner.getProperty(EntityProperty.CARRY_WEIGHT)
  val totalWeight: Double = items.map(_.weight).sum

  def initInventory(): Unit = {
  }

  def +=(itemStack: ItemStack): Unit = {
    items += itemStack
  }

  def -=(itemStack: ItemStack): Unit = {
    items -= itemStack
  }

  override def iterator: Iterator[ItemStack] = items.iterator

}
