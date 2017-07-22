package io.github.plenglin.magix.entity.projectile

import com.badlogic.gdx.graphics.g2d.TextureRegion
import io.github.plenglin.magix.Damageable
import io.github.plenglin.magix.entity.{Entity, TexturedEntity}


/**
  * Because what magic-based RPG is incomplete without it.
  */
class MagicMissileProjectile(source: Entity, target: Damageable) extends Projectile(source, target) with TexturedEntity {

  override var damage: Double = 10
  override var speed: Float = 50
  override var baseHP: Double = 30

  override val name: String = "Magic Missile Projectile"
  override var texture: TextureRegion = _
}
