package io.github.plenglin.magix.entity.projectile

import java.util.logging.Logger

import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.{SpriteBatch, TextureRegion}
import com.badlogic.gdx.math.{Vector2, Vector3}
import io.github.plenglin.magix.Assets
import io.github.plenglin.magix.entity.Entity
import io.github.plenglin.magix.types.{Damageable, TexturedDrawable}


/**
  * Because what magic-based RPG is incomplete without it.
  */
class MagicMissileProjectile(source: Entity, target: Damageable) extends HomingProjectile(source, target) with TexturedDrawable {

  private val logger = Logger.getLogger(getClass.getName)

  override val center: Boolean = true
  override var damage: Double = 10
  override var speed: Float = 30
  override var baseHP: Double = 30

  override var name: String = "Magic Missile Projectile"
  override var hitRadius2: Float = 1

  override def drawPos: Vector2 = pos

  override def textureRegion: TextureRegion = Assets.tMagicMissile

  override val dimensions: Vector2 = new Vector2(0.5f, 0.5f)

}
