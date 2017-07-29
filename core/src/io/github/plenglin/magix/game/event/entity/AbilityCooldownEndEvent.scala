package io.github.plenglin.magix.game.event.entity

import java.util.logging.Logger

import io.github.plenglin.magix.game.ability.player.PlayerAbility
import io.github.plenglin.magix.game.entity.Entity

class AbilityCooldownEndEvent(ability: PlayerAbility) extends EntityEvent {

  private val logger = Logger.getLogger(getClass.getName)

  override def onTrigger(target: Entity): Unit = {
    logger.info(f"${ability.source.name}'s ${ability.name} cooldown ended")
  }

}
