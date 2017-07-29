package io.github.plenglin.magix.game.event.entity

import io.github.plenglin.magix.game.entity.Entity

abstract class EntityEvent {

  def onTrigger(target: Entity)

}
