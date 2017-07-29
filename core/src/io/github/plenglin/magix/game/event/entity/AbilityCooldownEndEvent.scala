package io.github.plenglin.magix.game.event.entity

import io.github.plenglin.magix.game.ability.player.PlayerAbility
import io.github.plenglin.magix.game.entity.Entity

class AbilityCooldownEndEvent(ability: PlayerAbility) extends EntityEvent {

  override def onTrigger(target: Entity): Unit = {

  }

}
