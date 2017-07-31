package io.github.plenglin.magix.game.entity.projectile

import java.util.logging.Logger

import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.math.Vector2
import io.github.plenglin.magix.Assets
import io.github.plenglin.magix.game.entity.Entity
import io.github.plenglin.magix.game.types.Damageable
import io.github.plenglin.magix.render.TexturedDrawable


/**
  * Because what magic-based RPG is incomplete without it.
  */
class MagicMissileProjectile(source: Entity, target: Damageable) extends HomingProjectile(source, target) with TexturedDrawable {

  override val center: Boolean = true
  override val dimensions: Vector2 = new Vector2(0.5f, 0.5f)
  private val logger = Logger.getLogger(getClass.getName)
  override var damage: Double = 10
  override val baseSpeed: Double = 30
  override var baseHP: Double = 30
  override def name: String = "Magic Missile Projectile"
  override var hitRadius2: Float = 1

  override def drawPos: Vector2 = pos

  override def textureRegion: TextureRegion = Assets.tMagicMissile

}
