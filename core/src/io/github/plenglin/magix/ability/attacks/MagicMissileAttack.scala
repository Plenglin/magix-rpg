package io.github.plenglin.magix.ability.attacks

import io.github.plenglin.magix.entity.Entity
import io.github.plenglin.magix.entity.humanoid.Player
import io.github.plenglin.magix.entity.projectile.{HomingProjectile, MagicMissileProjectile}
import io.github.plenglin.magix.types.{Damageable, Targetable}

class MagicMissileAttack extends TargetedProjectileAttack {
  val cooldown: Long = 500

  /**
    * What it's written as to the user
    */
  var name: String = "Magic Missile"

  override def generateProjectile(target: Targetable): HomingProjectile = {
    new MagicMissileProjectile(source, target.asInstanceOf[Damageable])
  }

  override def canTarget(target: Targetable): Boolean = target match {
    case _: Player => false
    case _: Damageable => true
    case _ => false
  }

}
