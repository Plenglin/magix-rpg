package io.github.plenglin.magix.game.ability.exception

import io.github.plenglin.magix.game.types.Targetable

/**
  * Thrown when a `TargetedAbility` is supplied with an invalid target.
  *
  * @param target the attempted target
  */
class InvalidTargetException(val target: Targetable) extends AbilityFailureException(s"Invalid target $target") {

}
