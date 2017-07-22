package io.github.plenglin.magix

import java.util.logging.Logger

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
      damageQueue.dequeue().onTrigger(this)
      logger.info(s"$this.hp: ${this.hp}")
      if (isDead) {
        destroy()
        return
      }
    }
  }

  def isDead: Boolean = {
    hp < 0
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

  def destroy(): Unit = {
    hp = 0
  }

}
