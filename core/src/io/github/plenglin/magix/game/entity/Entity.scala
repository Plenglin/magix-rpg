package io.github.plenglin.magix.game.entity

import java.util.logging.Logger

import com.badlogic.gdx.math.Vector2
import io.github.plenglin.magix.control.ControlLoop
import io.github.plenglin.magix.game.EntityProperty
import io.github.plenglin.magix.game.ability.player.PlayerAbility
import io.github.plenglin.magix.game.effect.EntityEffect
import io.github.plenglin.magix.game.event.entity.{EntityEvent, HealthChangeSource}
import io.github.plenglin.magix.game.event.global.GlobalEvent
import io.github.plenglin.magix.game.inventory.Inventory
import io.github.plenglin.magix.game.types.Damageable
import io.github.plenglin.magix.render.Drawable
import io.github.plenglin.magix.{Constants, GameData}

import scala.collection.mutable
import scala.collection.mutable.ListBuffer

/**
  * A thing that can move around that has health and mana.
  *
  * @param pos where it is
  */
abstract class Entity(var pos: Vector2) extends HealthChangeSource with Damageable with Drawable {

  val target: Vector2 = pos.cpy
  val baseArmor: Double = 0
  private val logger = Logger.getLogger(getClass.getName)
  var abilities: ListBuffer[PlayerAbility] = ListBuffer()
  val controlLoop = new ControlLoop()
  def effects: mutable.Iterable[EntityEffect] = controlLoop.loopables.filter(_.isInstanceOf[EntityEffect]).map(_.asInstanceOf[EntityEffect])
  var eventQueue: mutable.Queue[EntityEvent] = mutable.Queue()

  private val baseProperties: mutable.Map[EntityProperty.Value, Double] = mutable.Map()

  val baseMana: Double = 0
  var mana: Double = _
  val baseManaRegen: Double = 0

  var inventory: Inventory = _

  def manaRegen: Double = baseManaRegen * effects.map(_.coeffManaRegen).product + effects.map(_.addedManaRegen).sum

  /**
    * How stronk is it?
    */
  var baseHP: Double

  /**
    * How quick can it regenerate?
    */
  val baseHPRegen: Double = 0
  var hp: Double = _

  /**
    * How fast can this go?
    */
  protected val baseSpeed: Double

  /**
    * How much can it carry?
    */
  protected val baseCarryWeight: Double = 0

  override def hpRegen: Double = baseManaRegen * effects.map(_.coeffManaRegen).product + effects.map(_.addedManaRegen).sum

  override def armor: Double = baseArmor + effects.map(_.addedArmor).sum

  def init(): Unit = {
    hp = maxHP
    mana = maxMana
    inventory = new Inventory(this)
    baseProperties += (
      EntityProperty.SPEED -> baseSpeed,
      EntityProperty.CARRY_WEIGHT -> baseCarryWeight
    )
    inventory.initInventory()
    onInit()
  }

  def maxMana: Double = baseMana * effects.map(_.coeffMana).product + effects.map(_.addedMana).sum
  override def maxHP: Double = baseHP * effects.map(_.coeffHP).product + effects.map(_.addedHP).sum

  /**
    * Called after the entity is initialized.
    */
  def onInit() {}

  def doManaRegen(dt: Float): Unit = {
    mana = math.min(mana + dt*manaRegen, maxMana)
  }

  /**
    * Called every single loop.
    *
    * @param dt delta time
    */
  def onUpdate(dt: Float) {}

  override def destroy(): Unit = {
    logger.info(s"destroying $this")
    onDestroy()
    GameData.entities -= this
  }

  /**
    * Called immediately before the entity's imminent destruction.
    */
  def onDestroy() {}

  def processEventQueue(): Unit = {
    while (eventQueue.nonEmpty) {
      val event = eventQueue.dequeue()
      logger.info(s"$this triggered event $event")
      val result = this.onEntityEvent(event)
      if (result) {
        event.onTrigger(this)
      }
    }
  }

  def getProperty(property: EntityProperty.Value): Double = {
    val changes = effects.map(_.propertyModifications).toList  // A list of maps of changes
    val coeff: List[Double] = changes.map(e => e.getOrElse(property, (1d, 0d))._1.asInstanceOf[Double])
    val add: List[Double] = changes.map(e => e.getOrElse(property, (1d, 0d))._2.asInstanceOf[Double])
    baseProperties(property) *
      coeff.product +
      add.sum
  }

  /**
    * Called immediately before each event is processed.
    *
    * @param event the event
    * @return whether the event should apply or not.
    */
  def onEntityEvent(event: EntityEvent): Boolean = {
    true
  }

  /**
    * Called immediately before a global event is triggered.
    *
    * @param event the event
    */
  def onGlobalEvent(event: GlobalEvent): Unit = {

  }

  /**
    * Move towards the target in this current frame.
    *
    * @param dt the delta between frames
    * @return whether we moved or not
    */
  def moveTowardsTarget(dt: Float): Boolean = {
    val displacement = pos.cpy.sub(target)
    if (displacement.len2() >= Constants.movementThreshold2) {
      displacement.nor.scl(-getProperty(EntityProperty.SPEED).toFloat * dt)
      pos.add(displacement)
      logger.finest(s"displacement: $displacement; magnitude: ${displacement.len()},new pos: $pos")
      return true
    }
    false
  }

}
