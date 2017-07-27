package io.github.plenglin.magix.ability.attacks

import io.github.plenglin.magix.GameData
import io.github.plenglin.magix.ability.TargetedPlayerAbility
import io.github.plenglin.magix.ability.exception.TargetRangeException
import io.github.plenglin.magix.entity.projectile.HomingProjectile
import io.github.plenglin.magix.types.Targetable

abstract class TargetedProjectileAttack extends TargetedPlayerAbility {

  def generateProjectile(target: Targetable): HomingProjectile

  override def activate(target: Targetable): Unit = {
    if (source.pos.dst2(target.pos) > range2) {
      throw new TargetRangeException()
    }
    GameData.addEntity(generateProjectile(target))
  }

  def range2: Float = Float.MaxValue

}
