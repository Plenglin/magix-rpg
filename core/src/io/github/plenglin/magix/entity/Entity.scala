package io.github.plenglin.magix.entity

import java.util.logging.Logger

import com.badlogic.gdx.math.Vector2
import io.github.plenglin.magix.ability.{Ability, TargetedAbility}
import io.github.plenglin.magix.effect.EntityEffect
import io.github.plenglin.magix.event.entity.{HealthChangeSource, EntityEvent}
import io.github.plenglin.magix.event.global.GlobalEvent
import io.github.plenglin.magix.types.{Damageable, Drawable}
import io.github.plenglin.magix.{Constants, GameData}

import scala.collection.mutable
import scala.collection.mutable.ListBuffer

/**
  * An entity with a health bar.
  * @param pos where it is
  */
abstract class Entity(var pos: Vector2) extends HealthChangeSource with Damageable with Drawable {

  private val logger = Logger.getLogger(getClass.getName)

  var abilities: ListBuffer[Ability] = ListBuffer()
  var effects: ListBuffer[EntityEffect] = ListBuffer()

  var eventQueue: mutable.Queue[EntityEvent] = mutable.Queue()

  var target: Vector2 = pos.cpy
  var speed: Float

  var baseHP: Double
  var baseArmor: Double = 0
  var hp: Double = _

  override def maxHP: Double = baseHP * effects.map(_.coeffHP).product + effects.map(_.addedHP).sum

  override def armor: Double = baseArmor + effects.map(_.addedArmor).sum

  def init(): Unit = {
    this.hp = this.maxHP
    onInit()
  }

  /**
    * Called after the entity is initialized.
    */
  def onInit() {}

  /**
    * Called every single loop.
    * @param dt
    */
  def onUpdate(dt: Float) {}

  /**
    * Called immediately before the entity's imminent destruction.
    */
  def onDestroy() {}

  override def destroy(): Unit = {
    logger.info(s"destroying $this")
    onDestroy()
    GameData.entities -= this
  }

  /**
    * Called immediately before each event is processed.
    * @param event the event
    * @return whether the event should apply or not.
    */
  def onEntityEvent(event: EntityEvent): Boolean = {
    true
  }

  def processEventQueue(): Unit = {
    while (eventQueue.nonEmpty) {
      val event = eventQueue.dequeue()
      logger.info(s"$this triggered event $event")
      val result = this.onEntityEvent(event)
      if (result) {
        event.onTrigger(this)
      }
    }
    processDamageQueue()
  }

  /**
    * Called immediately before a global event is triggered.
    * @param event the event
    */
  def onGlobalEvent(event: GlobalEvent): Unit = {

  }

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
      logger.fine(s"displacement: $displacement; magnitude: ${displacement.len()},new pos: $pos")
      return true
    }
    false
  }

}
