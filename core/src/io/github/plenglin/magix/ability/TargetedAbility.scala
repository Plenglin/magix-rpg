package io.github.plenglin.magix.ability

import java.util.logging.Logger

import com.badlogic.gdx.math.Vector2
import io.github.plenglin.magix.{GameData, Targetable}
import io.github.plenglin.magix.entity.Entity


/**
  * An ability constrained to a particular target.
  */
abstract class TargetedAbility(source: Entity) extends Ability {

  private val logger = Logger.getLogger(getClass.getName)

  def canTarget(target: Option[Targetable]): Boolean

  def onTriggered(target: Targetable)

  override def trigger(mousePos: Vector2): Unit = {
    val possibleTargets = GameData.targetable.filter(e =>
      e.pos.cpy().sub(mousePos).len2() < e.targetRadius2 &&  // Mouse within radius
        canTarget(Option(e)))  // Allowed to target
    logger.info(s"${possibleTargets.toList.toString()}")
  }

}
