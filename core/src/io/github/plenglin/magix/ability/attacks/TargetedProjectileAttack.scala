package io.github.plenglin.magix.ability.attacks

import java.util.NoSuchElementException

import io.github.plenglin.magix.GameData
import io.github.plenglin.magix.ability.TargetedPlayerAbility
import io.github.plenglin.magix.ability.exception.{InvalidTargetException, TargetRangeException}
import io.github.plenglin.magix.entity.Entity
import io.github.plenglin.magix.entity.projectile.HomingProjectile
import io.github.plenglin.magix.types.{Damageable, Targetable}

abstract class TargetedProjectileAttack extends TargetedPlayerAbility {

  def range2: Float = Float.MaxValue

  def generateProjectile(target: Targetable): HomingProjectile

  override def activate(target: Targetable): Unit = {
    if (source.pos.dst2(target.pos) > range2) {
      throw new TargetRangeException()
    }
    GameData.addEntity(generateProjectile(target))
  }

}
