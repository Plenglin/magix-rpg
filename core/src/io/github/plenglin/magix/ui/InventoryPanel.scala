package io.github.plenglin.magix.ui

import com.badlogic.gdx.scenes.scene2d.{Event, EventListener}
import com.badlogic.gdx.scenes.scene2d.ui.{Label, Skin, Table, Window}
import io.github.plenglin.magix.game.event.InventoryChangeEvent
import io.github.plenglin.magix.game.inventory.Inventory

class InventoryPanel(inventory: Inventory, skin: Skin, styleName: String) extends Window("inventory", skin, styleName) {

  private val itemHeading = new Label("Item", skin)
  private val countHeading = new Label("Count", skin)
  private val weightHeading = new Label("Weight", skin)

  val table = new Table()
  table.defaults().height(20).pad(5)
  table.setFillParent(true)

  this.add(table).width(400).height(500)
  table.center().top()

  this.addListener(new EventListener {
    override def handle(event: Event): Boolean = {
      event match {
        case _: InventoryChangeEvent =>
          updateTable()
          true
        case _ =>
          false
      }
    }
  })

  def updateTable(): Unit = {
    table.clearChildren()
    table.add(itemHeading).width(250)
    table.add(countHeading).width(50)
    table.add(weightHeading).width(50)
    for (i <- inventory) {
      table.row()
      table.add(new Label(i.item.name, skin)).width(250)
      table.add(new Label(i.size.toString, skin)).width(50)
      table.add(new Label(i.weight.toString, skin)).width(50)
    }
  }

}
