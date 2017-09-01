package io.github.plenglin.magix.game.items

import scala.collection.mutable
import scala.xml.Node

object ItemRegistry {

  private val items: mutable.Map[String, Item] = mutable.Map()

  def getItemById(id: String): Option[Item] = items.get(id)

  def retrieveItems(node: Node): Unit = {
    for (c <- node.child) {
      val item = itemFromNode(c)
      items += item.id -> item
    }
  }

  def itemFromNode(node: Node): Item = {
    val item = new Item(
      (node \ "@itemId").toString(),
      (node \ "@name").toString(),
      (node \ "@weight").toString().toDouble,
      (node \ "@cost").toString().toInt,
      (node \ "@category").toString()
    )
    val desc = Option((node \ "desc").toString())
    item.desc = (_) => desc
    item
  }
}
