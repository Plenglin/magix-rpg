package io.github.plenglin.magix.ability

import java.util.logging.Logger

import com.badlogic.gdx.math.Vector2
import io.github.plenglin.magix.GameData
import io.github.plenglin.magix.entity.Entity
import io.github.plenglin.magix.types.Targetable


/**
  * An ability constrained to a particular target.
  */
abstract class TargetedAbility(source: Entity) extends Ability {

  private val logger = Logger.getLogger(getClass.getName)

  def canTarget(target: Targetable): Boolean

  def onTriggered(target: Targetable)

  override def trigger(mousePos: Vector2): Unit = {
    val possibleTargets = GameData.targetables.filter(target =>
      target.pos.dst2(mousePos) < target.targetRadius2 &&  // Mouse within radius
        canTarget(target))  // Allowed to target
    if (possibleTargets.nonEmpty) {
      onTriggered(possibleTargets.head)
    }
  }

}
