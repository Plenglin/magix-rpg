package io.github.plenglin.magix.ability.exception

import io.github.plenglin.magix.ability.Ability
import io.github.plenglin.magix.types.Targetable

/**
  * Created by max on 7/24/17.
  */
class TargetRangeException(ability: Ability, val target: Targetable)
  extends AbilityFailureException(f"$ability could not be used on $target because it was out of range") {

}
