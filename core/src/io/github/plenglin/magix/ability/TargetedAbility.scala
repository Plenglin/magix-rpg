package io.github.plenglin.magix.ability

import java.util.logging.Logger

import com.badlogic.gdx.math.Vector2
import io.github.plenglin.magix.GameData
import io.github.plenglin.magix.ability.exception.{InvalidTargetException, NoTargetsException}
import io.github.plenglin.magix.entity.Entity
import io.github.plenglin.magix.types.Targetable


/**
  * An ability constrained to a particular target.
  */
abstract class TargetedAbility extends PositionalAbility {

  private val logger = Logger.getLogger(getClass.getName)

  def canTarget(target: Targetable): Boolean

  def activate(target: Targetable)

  override def activate(pos: Vector2): Unit = {
    val possibleTargets = GameData.targetables.filter(target => target.pos.dst2(pos) < target.targetRadius2 && canTarget(target))
    if (possibleTargets.nonEmpty) {
      activate(possibleTargets.head)
    } else {
      throw new NoTargetsException(this, pos)
    }
  }

}
