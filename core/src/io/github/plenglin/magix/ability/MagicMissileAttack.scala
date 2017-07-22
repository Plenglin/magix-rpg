package io.github.plenglin.magix.ability
import io.github.plenglin.magix.{Damageable, Targetable}
import io.github.plenglin.magix.entity.Entity
import io.github.plenglin.magix.entity.projectile.{MagicMissileProjectile, HomingProjectile}

class MagicMissileAttack(source: Entity) extends TargetedProjectileAttack(source) {
  override val cooldown: Float = 10

  override def generateProjectile(target: Targetable): HomingProjectile = {
    new MagicMissileProjectile(source, target)
  }

  override def canTarget(target: Option[Targetable]): Boolean = {
    true
  }

}
