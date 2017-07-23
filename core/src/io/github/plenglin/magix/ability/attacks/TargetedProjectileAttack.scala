package io.github.plenglin.magix.ability.attacks

import java.util.NoSuchElementException

import io.github.plenglin.magix.GameData
import io.github.plenglin.magix.ability.{InvalidTargetException, TargetedAbility}
import io.github.plenglin.magix.entity.Entity
import io.github.plenglin.magix.entity.projectile.HomingProjectile
import io.github.plenglin.magix.types.{Damageable, Targetable}

abstract class TargetedProjectileAttack extends TargetedAbility {

  def generateProjectile(target: Targetable): HomingProjectile

  override def activate(target: Targetable): Unit = try {
    target match {
      case d: Damageable =>
        val proj = generateProjectile(d)
        GameData.addEntity(proj)
      case _ => throw new InvalidTargetException(Option(target))
    }
  } catch {
    case _: NoSuchElementException => throw new InvalidTargetException(None)
  }

}
