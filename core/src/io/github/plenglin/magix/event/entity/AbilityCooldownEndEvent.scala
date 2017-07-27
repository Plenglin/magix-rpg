package io.github.plenglin.magix.event.entity
import io.github.plenglin.magix.ability.PlayerAbility
import io.github.plenglin.magix.entity.Entity

class AbilityCooldownEndEvent(ability: PlayerAbility) extends EntityEvent {

  override def onTrigger(target: Entity): Unit = {

  }

}
