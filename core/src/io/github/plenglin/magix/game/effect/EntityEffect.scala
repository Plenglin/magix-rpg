package io.github.plenglin.magix.game.effect

import io.github.plenglin.magix.control.Loopable
import io.github.plenglin.magix.game.EntityProperty
import io.github.plenglin.magix.game.entity.Entity
import io.github.plenglin.magix.game.event.entity.EntityEvent

/**
  * An effect that can be applied to a `LivingEntity`.
  */
abstract class EntityEffect() extends Loopable {

  var target: Entity = _

  val hidden: Boolean // Whether this effect is to be displayed to the user

  val name: String

  val desc: String = ""

  /**
    * A map of modifications for each type of property. The first elements of each effect's map for this value is
    * multiplied by the base value. After all multiplications are done, all second elements are added to the result.
    */
  val propertyModifications: Map[EntityProperty.Value, (Double, Double)] = Map()

  def addedArmor: Double = 0

  def addedHP: Double = 0
  def coeffHP: Double = 1

  def addedMana: Double = 0
  def coeffMana: Double = 1

  def addedManaRegen: Double = 0
  def coeffManaRegen: Double = 1

  def addedHPRegen: Double = 0
  def coeffHPRegen: Double = 1

  def init(entity: Entity): Unit = {
    target = entity
    super.init()
  }

  def onEvent(event: EntityEvent): Unit = {

  }

}
