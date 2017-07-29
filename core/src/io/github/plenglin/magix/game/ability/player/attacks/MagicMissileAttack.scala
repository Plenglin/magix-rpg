package io.github.plenglin.magix.game.ability.player.attacks

import io.github.plenglin.magix.game.entity.humanoid.Player
import io.github.plenglin.magix.game.entity.projectile.{HomingProjectile, MagicMissileProjectile}
import io.github.plenglin.magix.game.types.{Damageable, Targetable}

class MagicMissileAttack extends TargetedProjectileAttack {
  override val cooldown: Long = 500

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

  /**
    * Mana subtracted from player when activated
    */
  override val manaCost: Double = 15
}
