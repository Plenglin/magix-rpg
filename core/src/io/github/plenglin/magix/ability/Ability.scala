package io.github.plenglin.magix.ability

import io.github.plenglin.magix.Targetable
import io.github.plenglin.magix.entity.Entity


/**
  * Something that an entity can activate.
  */
abstract class Ability(source: Entity) {

  /**
    * Period, in seconds, between attacks
    */
  val cooldown: Float

  def canTarget(target: Option[Targetable]): Boolean

  def onTriggered(target: Option[Targetable])

}
