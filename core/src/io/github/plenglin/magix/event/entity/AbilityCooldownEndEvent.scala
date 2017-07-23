package io.github.plenglin.magix.event.entity
import io.github.plenglin.magix.ability.Ability
import io.github.plenglin.magix.entity.Entity

class AbilityCooldownEndEvent(ability: Ability) extends EntityEvent {

  override def onTrigger(target: Entity): Unit = {

  }

}
