package io.github.plenglin.magix.event.entity
import io.github.plenglin.magix.GameData
import io.github.plenglin.magix.entity.Entity

class EntityDestroyedEvent extends EntityEvent {

  override def onTrigger(target: Entity): Unit = {
    target.destroy()
  }

}
