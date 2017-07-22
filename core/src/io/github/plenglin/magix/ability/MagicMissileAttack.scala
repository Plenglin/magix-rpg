package io.github.plenglin.magix.ability
import io.github.plenglin.magix.Damageable
import io.github.plenglin.magix.entity.Entity
import io.github.plenglin.magix.entity.projectile.{MagicMissileProjectile, Projectile}

class MagicMissileAttack(source: Entity) extends ProjectileAttack(source) {
  override val cooldown: Float = 10

  override def generateProjectile(target: Option[Any]): Projectile = {
    new MagicMissileProjectile(source, target)
  }
}
