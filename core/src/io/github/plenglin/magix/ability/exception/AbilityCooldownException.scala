package io.github.plenglin.magix.ability.exception

import io.github.plenglin.magix.ability.Ability

/**
  * Thrown when an ability is activated, but is still on cooldown.
  * @param ability the ability
  */
class AbilityCooldownException(val ability: Ability) extends AbilityFailureException {

}
