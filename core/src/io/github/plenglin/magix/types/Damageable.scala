package io.github.plenglin.magix.types

import java.util.logging.Logger

import io.github.plenglin.magix.Constants
import io.github.plenglin.magix.event.health.HealthEvent

import scala.collection.mutable

trait Damageable extends Targetable {

  private val logger = Logger.getLogger(getClass.getName)

  val damageQueue: mutable.Queue[HealthEvent] = new mutable.Queue()
  var hp: Double          // How much health it has
  def maxHP: Double       // How much health it can have
  def armor: Double = 0   // Percent of incoming damage negated. Armor cannot heal.

  def processDamageQueue(): Unit = {
    while (damageQueue.nonEmpty) {
      val event = damageQueue.dequeue()
      if (onHealthEvent(event)) {
        event.onTrigger(this)
      } else {
        logger.info(f"$this invalidated HealthEvent $event")
      }
    }
    if (isDead) {
      logger.info(f"$this died, destroying")
      destroy()
    }
  }

  def isDead: Boolean = {
    hp <= Constants.deathThreshold
  }

  /**
    * Apply a change in health to the object. This accounts for the armor, as well.
    * @param change how much to change. If positive, heals. If negative, damages.
    */
  def applyHealthChange(change: Double): Unit = {
    if (change < 0) {
      var dmg = -change
      hp = hp - dmg * math.max(0, 1 - armor)
    } else {
      hp += math.min(change, maxHP)
    }
  }

  def destroy()

  /**
    * Called before a `HealthEvent` gets to trigger.
    * @param event the event
    * @return whether it should trigger or not
    */
  def onHealthEvent(event: HealthEvent): Boolean = {
    true
  }

}
