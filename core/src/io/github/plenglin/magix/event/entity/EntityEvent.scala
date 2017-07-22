package io.github.plenglin.magix.event.entity

import io.github.plenglin.magix.entity.Entity

abstract class EntityEvent {

  def onTrigger(target: Entity)

}
