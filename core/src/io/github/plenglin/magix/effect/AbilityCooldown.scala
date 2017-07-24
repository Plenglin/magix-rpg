package io.github.plenglin.magix.effect

import io.github.plenglin.magix.ability.Ability
import io.github.plenglin.magix.event.entity.AbilityCooldownEndEvent

class AbilityCooldown(val ability: Ability) extends EntityEffect(ability.source) {
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
