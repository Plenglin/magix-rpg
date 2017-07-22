package io.github.plenglin.magix.entity

import java.util.logging.Logger

import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Vector2
import io.github.plenglin.magix.Constants
import io.github.plenglin.magix.effect.EntityEffect
import io.github.plenglin.magix.event.EntityEvent
import io.github.plenglin.magix.event.entity.{DamageSource, EntityEvent}

import scala.collection.mutable.ListBuffer

/**
  * Something that can move
  * @param pos where it is
  */
abstract class Entity(var pos: Vector2) extends DamageSource {

  private val logger = Logger.getLogger(getClass.getName)

  var target: Vector2 = pos.cpy
  var speed: Float

  def onInit()

  def onUpdate(dt: Float)

  def onDestroy()

  def onEvent(event: EntityEvent)

  def draw(batch: SpriteBatch)

  /**
    * Move towards the target in this current frame.
    * @param dt the delta between frames
    * @return whether we moved or not
    */
  def moveTowardsTarget(dt: Float): Boolean = {
    val displacement = pos.cpy.sub(target)
    if (displacement.len2() >= Constants.movementThreshold2) {
      displacement.nor.scl(-speed * dt)
      pos.add(displacement)
      logger.info(s"displacement: $displacement; magnitude: ${displacement.len()},new pos: $pos")
      return true
    }
    false
  }

}
