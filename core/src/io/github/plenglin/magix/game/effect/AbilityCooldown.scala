package io.github.plenglin.magix.game.effect

import io.github.plenglin.magix.game.ability.player.PlayerAbility
import io.github.plenglin.magix.game.event.entity.AbilityCooldownEndEvent

class AbilityCooldown(val ability: PlayerAbility) extends EntityEffect(ability.source) {
  override val hidden: Boolean = true

  override val name: String = f"${ability.name} Cooldown"

  override def onAdded(): Unit = {

  }

  override def onApply(): Unit = {

  }

  override def shouldRemove(): Boolean = ability.cooldownComplete

  override def onRemove(): Unit = {
    ability.source.eventQueue += new AbilityCooldownEndEvent(ability)
  }

}
