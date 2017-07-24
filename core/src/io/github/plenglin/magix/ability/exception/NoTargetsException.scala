package io.github.plenglin.magix.ability.exception

import com.badlogic.gdx.math.Vector2
import io.github.plenglin.magix.ability.TargetedAbility

/**
  * Thrown when a `TargetedAbility` could not find a target.
  * @param ability the ability
  * @param pos where it was searching
  */
class NoTargetsException(val ability: TargetedAbility, val pos: Vector2)
  extends AbilityFailureException(s"$ability could not find valid target given pos $pos") {

}
