package io.github.plenglin.magix.ability

import io.github.plenglin.magix.Damageable
import io.github.plenglin.magix.entity.Entity
import io.github.plenglin.magix.entity.projectile.Projectile

abstract class ProjectileAttack(source: Entity) extends Ability(source) {

  def generateProjectile(target: Option[Any]): Projectile

  override def onTriggered(target: Option[Any]): Unit = {
    val projectile = generateProjectile(target)
  }

}
